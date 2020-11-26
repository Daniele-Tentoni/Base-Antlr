package fool.compiler.east;

import fool.compiler.ast.AbstractSyntaxTree;
import fool.compiler.ast.lib.ASTVisitor;
import fool.compiler.east.lib.SymbolTableEntry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Link statements to their declarations.
 */
public class SymbolTableASTVisitor extends ASTVisitor<Void> {
  private final List<Map<String, SymbolTableEntry>> symbolTable;
  private int errors;
  private int nestingLevel;

  /**
   * Simple
   */
  public SymbolTableASTVisitor() {
    this(false);
  }

  public SymbolTableASTVisitor(boolean debug) {
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
    Map<String, SymbolTableEntry> hashMap = new HashMap<>();
    symbolTable.add(hashMap);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExpression());

    // Internal maps are already discarded.
    symbolTable.remove(0);
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
    var entry = new SymbolTableEntry(nestingLevel);

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
    var entry = new SymbolTableEntry(nestingLevel);
    var index = map.put(n.getId(), entry);
    if (index != null) {
      System.out.println(
          "Fun id " + n.getId() + " at line " + n.getLine()
              + " already declared.");
      errors++;
    }

    // Create a new map for next nesting level.
    nestingLevel++;
    var hashMap = new HashMap<String, SymbolTableEntry>();
    symbolTable.add(hashMap);

    // TODO: Visit here parameters.
    // Now visit all current nesting level + 1 declarations.
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());

    // After visit remove the top nesting level map.
    symbolTable.remove(nestingLevel--);
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

    //Here we are at a certain nesting level.
    // I fetch each nesting level over me for other uses.
    SymbolTableEntry entry = lookUp(n.getId());
    if (entry == null) {
      System.out.printf("Var id %s at line %d not declared.%n", n.getId(),
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
    SymbolTableEntry entry = lookUp(n.getId());
    if (entry == null) {
      System.out.printf("Fun id %s at line %d not declared.%n", n.getId(),
          n.getLine());
      errors++;
    } else {
      n.setEntry(entry);
    }
    return null;
  }

  /**
   * Search for a symbol in the symbol table for his declaration.
   *
   * @param id id to search.
   * @return entry found or null.
   */
  private SymbolTableEntry lookUp(String id) {
    int j = nestingLevel;
    SymbolTableEntry entry = null;
    while (j >= 0 && entry == null) {
      entry = symbolTable.get(j--).get(id);
    }
    return entry;
  }
}
