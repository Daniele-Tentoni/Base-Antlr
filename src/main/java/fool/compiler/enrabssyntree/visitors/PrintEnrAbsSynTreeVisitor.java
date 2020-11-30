package fool.compiler.enrabssyntree.visitors;

import fool.compiler.abssyntree.AbsSynTree;
import fool.compiler.enrabssyntree.lib.SymTabEntry;
import fool.compiler.execptions.VoidException;

/**
 * Print the Enriched Abstract Syntax Tree.
 */
public class PrintEnrAbsSynTreeVisitor
    extends EnrAbsSynTreeVisitor<Void, VoidException> {
  public PrintEnrAbsSynTreeVisitor() {
    super(false, true);
  }

  @Override
  public Void visit(AbsSynTree.ProgNode n) {
    printNode(n);
    visit(n.getExpression());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.ProgLetInNode n) {
    printNode(n);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExpression());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.FunNode n) {
    printNode(n, n.getId());
    visit(n.getReturnType());
    n.getParameterList().forEach(this::visit);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.ParameterNode n) {
    printNode(n, n.getId());
    visit(n.getType());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.VarNode n) {
    printNode(n, n.getId());
    visit(n.getType());
    visit(n.getExp());
    return null;
  }

  /**
   * Visit an Arrow Type Node, applying visitor pattern.
   *
   * @param n node.
   * @return nothing.
   */
  @Override
  public Void visit(AbsSynTree.ArrowTypeNode n) {
    printNode(n);
    n.getParameterTypesList().forEach(this::visit);
    visit(n.getReturnType(), "->");
    return null;
  }

  @Override
  public Void visit(AbsSynTree.BoolTypeNode n) {
    printNode(n);
    return null;
  }

  @Override
  public Void visit(AbsSynTree.IntTypeNode n) {
    printNode(n);
    return null;
  }

  @Override
  public Void visit(AbsSynTree.PrintNode n) {
    printNode(n);
    visit(n.getPrint());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.IfNode n) {
    printNode(n);
    visit(n.getCondition());
    visit(n.getThen());
    visit(n.getElse());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.EqualNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.TimesNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.PlusNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.CallNode n) {
    printNode(n, n.getId());
    visit(n.getEntry());
    n.getArgumentList().forEach(this::visit);
    return null;
  }

  @Override
  public Void visit(AbsSynTree.IdNode n) {
    printNode(n, n.getId());
    visit(n.getEntry());
    return null;
  }

  @Override
  public Void visit(AbsSynTree.IntValueNode n) {
    printNode(n, String.valueOf(n.getVal()));
    return null;
  }

  @Override
  public Void visit(AbsSynTree.BoolValueNode n) {
    printNode(n, String.valueOf(n.getVal()));
    return null;
  }

  @Override
  public Void visitSymTabEntry(SymTabEntry entry) {
    super.printSymTabEntry("nesting level " + entry.getNestingLevel());
    visit(entry.getType(), "entry type");
    return null;
  }
}
