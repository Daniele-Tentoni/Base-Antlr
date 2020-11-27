package fool.compiler.ast;

import fool.compiler.ast.lib.AbsSynTreeVisitor;

/**
 * Calculate the return value of a ABS.
 * This class doesn't be updated anymore.
 */
public class CalcAbsSynTreeVisitor extends AbsSynTreeVisitor<Integer> {

  public CalcAbsSynTreeVisitor(boolean print) {
    super(print);
  }

  public CalcAbsSynTreeVisitor() {
    super();
  }

  /**
   * Visit Prog Node.
   *
   * @param n node to visit.
   */
  @Override
  public Integer visit(AbstractSyntaxTree.ProgNode n) {
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
  public Integer visit(AbstractSyntaxTree.TimesNode n) {
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
  public Integer visit(AbstractSyntaxTree.PlusNode n) {
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
  public Integer visit(AbstractSyntaxTree.IntValueNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return n.getVal();
  }

  @Override
  public Integer visit(AbstractSyntaxTree.EqualNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    boolean equality = visit(n.getLeft()).equals(visit(n.getRight()));
    return equality ? 0 : 1;
  }

  @Override
  public Integer visit(AbstractSyntaxTree.BoolValueNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return n.getVal() ? 0 : 1;
  }

  @Override
  public Integer visit(AbstractSyntaxTree.IfNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    boolean equality = visit(n.getCondition()) == 0;
    return equality ? visit(n.getThen()) : visit(n.getElse());
  }

  @Override
  public Integer visit(AbstractSyntaxTree.PrintNode n) {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return visit(n.getPrint());
  }

  @Override
  public Integer visit(AbstractSyntaxTree.ParameterNode n) {
    if (super.mustPrint()) {
      super.printNode(n, n.getId());
    }
    return visit(n.getType());
  }
}