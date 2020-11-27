package fool.compiler.ast;

import fool.compiler.ast.lib.AbsSynTreeVisitor;

/**
 * Classe che ci permette di stampare l'ast.
 *
 * @author ap nono, è il professore che l'ha fatto.
 */
public class PrintAbsSynTreeVisitor extends AbsSynTreeVisitor<Void> {

  public PrintAbsSynTreeVisitor() {
    super(true);
  }

  /**
   * Visita per il node Prog.
   *
   * @param n il Prog Node da visitare.
   */
  @Override
  public Void visit(AbstractSyntaxTree.ProgNode n) {
    printNode(n);
    visit(n.getExp());
    return null;
  }

  /**
   * Visita per il node Times.
   *
   * @param n il Times Node da visitare.
   */
  @Override
  public Void visit(AbstractSyntaxTree.TimesNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  /**
   * Visita per il node Plus.
   *
   * @param n il Plus node da visitare.
   */
  @Override
  public Void visit(AbstractSyntaxTree.PlusNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  /**
   * Visit Int Node.
   *
   * @param n int node to visit.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.IntValueNode n) {
    printNode(n, String.valueOf(n.getVal()));
    return null;
  }

  /**
   * Visit Equal Node.
   *
   * @param n equal node to visit.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.EqualNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  /**
   * Visit Pars Node.
   *
   * @param n pars node to visit.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.ParameterNode n) {
    printNode(n, n.getId());
    visit(n.getType());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.BoolValueNode n) {
    printNode(n);
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IfNode n) {
    printNode(n);
    visit(n.getCondition());
    visit(n.getThen());
    visit(n.getElse());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.PrintNode n) {
    printNode(n);
    visit(n.getPrint());
    return null;
  }

  /**
   * Visit a variable declaration block.
   *
   * @param n block node.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.ProgLetInNode n) {
    printNode(n);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExpression());
    return null;
  }

  /**
   * Print var node and visit is type and exp.
   *
   * @param n var node to visit.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.VarNode n) {
    printNode(n, n.getId());
    visit(n.getType());
    visit(n.getExp());
    return null;
  }

  /**
   * Print int type node and visit is type and exp.
   *
   * @param n int type node to visit.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.IntTypeNode n) {
    printNode(n);
    return null;
  }

  /**
   * Print bool type node and visit is type and exp.
   *
   * @param n bool type node to visit.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.BoolTypeNode n) {
    printNode(n);
    return null;
  }

  /**
   * Print function declaration node. After that, visit his return type,
   * parameter list, declaration list and expression nodes.
   *
   * @param n function declaration node.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.FunNode n) {
    printNode(n, n.getId());
    visit(n.getRetType());
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());
    return null;
  }

  /**
   * Print function call node. Than visit each argument.
   *
   * @param n function call node.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.CallNode n) {
    printNode(n, n.getId());
    return null;
  }

  /**
   * Print variable use node.
   *
   * @param n variable use node.
   * @return nothing.
   */
  @Override
  public Void visit(AbstractSyntaxTree.IdNode n) {
    printNode(n, n.getId());
    return null;
  }
}