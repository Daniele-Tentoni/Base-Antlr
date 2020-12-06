package fool.compiler.abssyntree.visitors;

import fool.compiler.abssyntree.AbsSynTree;
import fool.compiler.execptions.TypeException;

/**
 * Calculate the return value of a ABS.
 * This class doesn't be updated anymore.
 */
@Deprecated(since = "Use of Enriched Abstract Syntax Tree", forRemoval = true)
public class CalcAbsSynTreeVisitor
    extends AbsSynTreeVisitor<Integer, TypeException> {

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
  public Integer visit(AbsSynTree.ProgNode n) throws TypeException {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return visit(n.getExpression());
  }

  /**
   * Visit Times Node.
   *
   * @param n node to visit.
   */
  @Override
  public Integer visit(AbsSynTree.TimesNode n) throws TypeException {
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
  public Integer visit(AbsSynTree.PlusNode n) throws TypeException {
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
  public Integer visit(AbsSynTree.IntValueNode n) throws TypeException {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return n.getVal();
  }

  @Override
  public Integer visit(AbsSynTree.EqualNode n) throws TypeException {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    boolean equality = visit(n.getLeft()).equals(visit(n.getRight()));
    return equality ? 0 : 1;
  }

  @Override
  public Integer visit(AbsSynTree.BoolValueNode n) throws TypeException {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return n.getVal() ? 0 : 1;
  }

  @Override
  public Integer visit(AbsSynTree.IfNode n) throws TypeException {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    boolean equality = visit(n.getCondition()) == 0;
    return equality ? visit(n.getThen()) : visit(n.getElse());
  }

  @Override
  public Integer visit(AbsSynTree.PrintNode n) throws TypeException {
    if (super.mustPrint()) {
      super.printNode(n);
    }
    return visit(n.getPrint());
  }

  @Override
  public Integer visit(AbsSynTree.ParameterNode n) throws TypeException {
    if (super.mustPrint()) {
      super.printNode(n, n.getId());
    }
    return visit(n.getType());
  }
}