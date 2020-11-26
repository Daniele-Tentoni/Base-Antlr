package fool.compiler.ast;

import fool.compiler.ast.AbstractSyntaxTree.*;
import fool.compiler.ast.lib.ASTVisitor;

/**
 * Classe che ci permette di stampare l'ast.
 *
 * @author ap nono, è il professore che l'ha fatto.
 */
public class PrintASTVisitor extends ASTVisitor<Void> {

  public PrintASTVisitor() {
    super(true);
  }

  /**
   * Visita per il node Prog.
   *
   * @param n il Prog Node da visitare.
   */
  @Override
  public Void visit(ProgNode n) {
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
  public Void visit(TimesNode n) {
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
  public Void visit(PlusNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  /**
   * Visita per il node Int.
   *
   * @param n il Int node da visitare.
   */
  @Override
  public Void visit(IntValueNode n) {
    printNode(n,String.valueOf(n.getVal()));
    return null;
  }

  @Override
  public Void visit(EqualNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(BoolValueNode n) {
    printNode(n);
    return null;
  }

  @Override
  public Void visit(IfNode n) {
    printNode(n);
    visit(n.getCondition());
    visit(n.getThen());
    visit(n.getElse());
    return null;
  }

  @Override
  public Void visit(PrintNode n) {
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
  public Void visit(ProgLetInNode n) {
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
  public Void visit(VarNode n) {
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
  public Void visit(IntTypeNode n) {
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
  public Void visit(BoolTypeNode n) {
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
  public Void visit(FunNode n) {
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
  public Void visit(CallNode n) {
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
  public Void visit(IdNode n) {
    printNode(n, n.getId());
    return null;
  }
}