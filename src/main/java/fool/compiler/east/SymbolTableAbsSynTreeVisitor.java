package fool.compiler.east;

import fool.compiler.ast.AbstractSyntaxTree;
import fool.compiler.ast.lib.AbsSynTreeVisitor;
import fool.compiler.east.lib.SymTabEntry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Link statements to their declarations.
 */
public class SymbolTableAbsSynTreeVisitor extends AbsSynTreeVisitor<Void> {
  private final List<Map<String, SymTabEntry>> symbolTable;
  private int errors;
  private int nestingLevel;

  /**
   * Simple
   */
  public SymbolTableAbsSynTreeVisitor() {
    this(false);
  }

  public SymbolTableAbsSynTreeVisitor(boolean debug) {
    super(debug);
    errors = 0;
    symbolTable = new LinkedList<>();
    nestingLevel = 0;
  }

  /**
   * Get current errors in statements declarations linking.
   *
   * @return number of errors.
   */
  public int getErrors() {
    return errors;
  }

  @Override
  public Void visit(AbstractSyntaxTree.ProgNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.TimesNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.PlusNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IntValueNode n) {
    if (mustPrint()) {
      printNode(n, String.valueOf(n.getVal()));
    }
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.EqualNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.BoolValueNode n) {
    if (mustPrint()) {
      printNode(n, String.valueOf(n.getVal()));
    }
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IfNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getCondition());
    visit(n.getThen());
    visit(n.getElse());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.PrintNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getPrint());
    return null;
  }

  /**
   * Fill a symbol table with prog let in node and his childs.
   *
   * @param n prog let in node.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.ProgLetInNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    Map<String, SymTabEntry> hashMap = new HashMap<>();
    symbolTable.add(hashMap);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExpression());

    // Internal maps are already discarded.
    symbolTable.remove(0);
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.BoolTypeNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IntTypeNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    return null;
  }

  /**
   * Fill a symbol table with a variable initialization.
   *
   * @param n variable declaration.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.VarNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Before insert into table, visit his exp.
    visit(n.getExp());

    // After that get actual map for nesting level and var entry.
    var map = symbolTable.get(nestingLevel);
    var entry = new SymTabEntry(nestingLevel);

    // Insert and verify if var is already present.
    var index = map.put(n.getId(), entry);
    if (index != null) {
      System.out.println("Var id " + n.getId() + " at line " + n.getLine()
          + " already declared.");
      errors++;
    }

    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.FunNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Get the current nesting level map and check correct insert.
    var map = symbolTable.get(nestingLevel);
    var entry = new SymTabEntry(nestingLevel);
    var index = map.put(n.getId(), entry);
    if (index != null) {
      System.out.println(
          "Fun id " + n.getId() + " at line " + n.getLine()
              + " already declared.");
      errors++;
    }

    // Create a new map for next nesting level.
    nestingLevel++;
    var hashMap = new HashMap<String, SymTabEntry>();
    symbolTable.add(hashMap);

    // Visit parameter for existing declarations.
    n.getParameterList().forEach(this::visit);
    /*
     param -> {
      var paramEntry = new SymTabEntry(nestingLevel);
      var paramIndex = map.put(param.getId(), paramEntry);
      if (paramIndex != null) {
        System.out.println(
            "Param id " + param.getId() + " at line " + param.getLine()
                + " already declared.");
        errors++;
      }
    }
     */

    // Now visit all current nesting level + 1 declarations.
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());

    // After visit remove the top nesting level map.
    symbolTable.remove(nestingLevel--);
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.ParameterNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    var map = symbolTable.get(nestingLevel);
    var entry = new SymTabEntry(nestingLevel);
    var index = map.put(n.getId(), entry);
    if (index != null) {
      System.out.printf("Param id %s at line %s already declared.", n.getId(),
          n.getLine());
      errors++;
    }

    visit(n.getType());

    return null;
  }

  /**
   * Fetch symbol table for var id declaration.
   *
   * @param n id node.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.IdNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Here we are at a certain nesting level.
    // I fetch each nesting level over me for other uses.
    SymTabEntry entry = lookUp(n.getId());
    if (entry == null) {
      System.out.printf("Var or Par id %s at line %d not declared.%n",
          n.getId(),
          n.getLine());
      errors++;
    } else {
      n.setEntry(entry);
    }

    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.CallNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Search for function id.
    SymTabEntry entry = lookUp(n.getId());
    if (entry == null) {
      System.out.printf("Fun id %s at line %d not declared.%n", n.getId(),
          n.getLine());
      errors++;
    } else {
      n.setEntry(entry);
    }

    n.getArgumentList().forEach(this::visit);

    return null;
  }

  /**
   * Search for a symbol in the symbol table for his declaration.
   *
   * @param id id to search.
   * @return entry found or null.
   */
  private SymTabEntry lookUp(String id) {
    int j = nestingLevel;
    SymTabEntry entry = null;
    while (j >= 0 && entry == null) {
      entry = symbolTable.get(j--).get(id);
    }
    return entry;
  }
}
