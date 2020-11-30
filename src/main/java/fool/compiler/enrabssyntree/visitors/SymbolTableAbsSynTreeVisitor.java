package fool.compiler.enrabssyntree.visitors;

import fool.compiler.abssyntree.AbsSynTree;
import fool.compiler.abssyntree.lib.nodes.TypeNode;
import fool.compiler.abssyntree.visitors.AbsSynTreeVisitor;
import fool.compiler.enrabssyntree.lib.SymTabEntry;
import fool.compiler.execptions.VoidException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Link statements to their declarations.
 */
public class SymbolTableAbsSynTreeVisitor extends AbsSynTreeVisitor<Void,
    VoidException> {
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

  /**
   * Fill a symbol table with prog let in node and his childs.
   *
   * @param n prog let in node.
   * @return nothing.
   */
  @Override
  public Void visit(AbsSynTree.ProgLetInNode n) {
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
  public Void visit(AbsSynTree.ProgNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getExpression());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.FunNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Get the current nesting level map and check correct insert.
    final var map = symbolTable.get(nestingLevel);
    List<TypeNode> types = n.getParameterList().stream().map(
        AbsSynTree.ParameterNode::getType).collect(
        Collectors.toList());
    final var arrow = new AbsSynTree.ArrowTypeNode(types, n.getReturnType());
    final var entry = new SymTabEntry(nestingLevel, arrow);
    final var index = map.put(n.getId(), entry);
    if (index != null) {
      System.out.printf("Fun id %s at line %s already declared.", n.getId(),
          n.getLine());
      errors++;
    }

    // Create a new map for next nesting level.
    nestingLevel++;
    var hashMap = new HashMap<String, SymTabEntry>();
    symbolTable.add(hashMap);

    // Visit parameter for existing declarations.
    // Another visit may require more complication on code generation.
    n.getParameterList().forEach(this::visit);

    // Now visit all current nesting level + 1 declarations.
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());

    // After visit remove the top nesting level map.
    symbolTable.remove(nestingLevel--);
    return null;
  }

  @Override
  public Void visit(AbsSynTree.ParameterNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    final var map = symbolTable.get(nestingLevel);
    final var entry = new SymTabEntry(nestingLevel, n.getType());
    final var index = map.put(n.getId(), entry);
    if (index != null) {
      System.out.printf("Param id %s at line %s already declared.", n.getId(),
          n.getLine());
      errors++;
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
  public Void visit(AbsSynTree.VarNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Before insert into table, visit his exp.
    visit(n.getExp());

    // After that get actual map for nesting level and var entry.
    final var map = symbolTable.get(nestingLevel);
    final var entry = new SymTabEntry(nestingLevel, n.getType());

    // Insert and verify if var is already present.
    final var index = map.put(n.getId(), entry);
    if (index != null) {
      System.out.printf("Var id %s at line %s already declared.", n.getId(),
          n.getLine());
      errors++;
    }

    return null;
  }

  @Override
  public Void visit(AbsSynTree.PrintNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getPrint());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.IfNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getCondition());
    visit(n.getThen());
    visit(n.getElse());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.EqualNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.TimesNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.PlusNode n) {
    if (mustPrint()) {
      printNode(n);
    }
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.CallNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Search for function id.
    final var entry = lookUp(n.getId());
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
   * Fetch symbol table for var id declaration.
   *
   * @param n id node.
   * @return nothing.
   */
  @Override
  public Void visit(AbsSynTree.IdNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }

    // Here we are at a certain nesting level.
    // I fetch each nesting level over me for other uses.
    final var entry = lookUp(n.getId());
    if (entry == null) {
      System.out.printf("Var or Par id %s at line %d not declared.%n",
          n.getId(), n.getLine());
      errors++;
    } else {
      n.setEntry(entry);
    }

    return null;
  }

  @Override
  public Void visit(AbsSynTree.IntValueNode n) {
    if (mustPrint()) {
      printNode(n, String.valueOf(n.getVal()));
    }
    return null;
  }

  @Override
  public Void visit(AbsSynTree.BoolValueNode n) {
    if (mustPrint()) {
      printNode(n, String.valueOf(n.getVal()));
    }
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
