package fool.compiler.ast;

import fool.compiler.ast.AbstractSyntaxTree.*;
import fool.compiler.ast.lib.ASTVisitor;

/**
 * Calculate the return value of a ABS.
 * This class doesn't be updated anymore.
 */
public class CalcASTVisitor extends ASTVisitor<Integer> {

  public CalcASTVisitor(boolean print) {
    super(print);
  }

  public CalcASTVisitor() {
    super();
  }

  /**
   * Visit Prog Node.
   *
   * @param n node to visit.
   */
  @Override
  public Integer visit(ProgNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return visit(n.getExp());
  }

  /**
   * Visit Times Node.
   *
   * @param n node to visit.
   */
  @Override
  public Integer visit(TimesNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return visit(n.getLeft()) * visit(n.getRight());
  }

  /**
   * Visit Plus Node.
   *
   * @param n node to visit.
   */
  @Override
  public Integer visit(PlusNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return visit(n.getLeft()) + visit(n.getRight());
  }

  /**
   * Visit Int Node.
   *
   * @param n node to visit.
   */
  @Override
  public Integer visit(IntValueNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return n.getVal();
  }

  @Override
  public Integer visit(EqualNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    boolean equality = visit(n.getLeft()).equals(visit(n.getRight()));
    return equality ? 0 : 1;
  }

  @Override
  public Integer visit(BoolValueNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return n.getVal() ? 0 : 1;
  }

  @Override
  public Integer visit(IfNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    boolean equality = visit(n.getCondition()) == 0;
    return equality ? visit(n.getThen()) : visit(n.getElse());
  }

  @Override
  public Integer visit(PrintNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return visit(n.getPrint());
  }
}