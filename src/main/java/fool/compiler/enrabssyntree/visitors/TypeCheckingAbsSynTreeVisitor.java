package fool.compiler.enrabssyntree.visitors;
import fool.compiler.SyntaxTreeUtils;
import fool.compiler.abssyntree.AbsSynTree;
import fool.compiler.abssyntree.lib.nodes.Node;
import fool.compiler.abssyntree.lib.nodes.TypeNode;
import fool.compiler.abssyntree.visitors.AbsSynTreeVisitor;
import fool.compiler.execptions.IncompleteException;
import fool.compiler.execptions.TypeException;
public class TypeCheckingAbsSynTreeVisitor extends AbsSynTreeVisitor<TypeNode
    , TypeException> {
  private int typeErrors;
  public TypeCheckingAbsSynTreeVisitor() {
    super(true);
  }

  public TypeCheckingAbsSynTreeVisitor(boolean debug) {
    super(true, debug);
  }

  /**
   * Check types in ProgLetIn node.
   *
   * @param n node to type check.
   * @return type checked.
   * @throws TypeException exception thrown when type checking fail.
   */
  @Override
  public TypeNode visit(AbsSynTree.ProgLetInNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }

    n.getDeclarationList().forEach(dec -> {
      try {
        visit(dec);
      } catch (TypeException e) {
        System.out.printf("Type checking error in a declaration: %s",
            e.getMessage());
      }
    });

    return visit(n.getExpression());
  }

  @Override
  public TypeNode visit(AbsSynTree.ProgNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }

    return visit(n.getExpression());
  }

  @Override
  public TypeNode visit(AbsSynTree.FunNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }

    n.getDeclarationList().forEach(dec -> {
      try {
        visit(dec);
      } catch (TypeException e) {
        System.out
            .printf("Type checking error in a declaration: %s", e.getMessage());
      } catch (IncompleteException e) {
        System.out.print("Incomplete exception in a declaration.");
      }
    });

    if (!SyntaxTreeUtils.isSubtype(visit(n.getExp()), n.getReturnType())) {
      typeErrors++;
      throw new TypeException("Wrong return type for function " + n.getId(),
          n.getLine());
    }
    return null;
  }

  @Override
  public TypeNode visit(AbsSynTree.VarNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    if (!SyntaxTreeUtils.isSubtype(visit(n.getExp()), n.getType())) {
      typeErrors++;
      throw new TypeException("Incompatible value for variable " + n.getId(),
          n.getLine());
    }
    return null;
  }

  @Override
  public TypeNode visit(AbsSynTree.PrintNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }

    return visit(n.getPrint());
  }

  @Override
  public TypeNode visit(AbsSynTree.IfNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    // Condition must be a boolean.
    if (!(SyntaxTreeUtils.isSubtype(visit(n.getCondition()),
        new AbsSynTree.BoolTypeNode()))) {
      typeErrors++;
      throw new TypeException("Non boolean condition in if", n.getLine());
    }
    // If must return the minimum common subtype between branches types.
    TypeNode t = visit(n.getThen());
    TypeNode e = visit(n.getElse());
    if (SyntaxTreeUtils.isSubtype(t, e)) {
      return e;
    } else if (SyntaxTreeUtils.isSubtype(e, t)) {
      return t;
    }

    typeErrors++;
    throw new TypeException("Incompatible types in then-else branches",
        n.getLine());
  }

  @Override
  public TypeNode visit(AbsSynTree.EqualNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    // Can there is a subtype each other relation type.
    TypeNode l = visit(n.getLeft());
    TypeNode r = visit(n.getRight());
    if (SyntaxTreeUtils.isSubtype(l, r) || SyntaxTreeUtils.isSubtype(r, l)) {
      return new AbsSynTree.BoolTypeNode();
    }

    typeErrors++;
    throw new TypeException("Incompatible types in equal", n.getLine());
  }

  @Override
  public TypeNode visit(AbsSynTree.TimesNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    // One can be subtype of other.
    if (!(SyntaxTreeUtils
        .isSubtype(visit(n.getLeft()), new AbsSynTree.IntTypeNode())
        && SyntaxTreeUtils
        .isSubtype(visit(n.getRight()), new AbsSynTree.IntTypeNode()))) {
      typeErrors++;
      throw new TypeException("Non integers in multiplication", n.getLine());
    }
    return new AbsSynTree.IntTypeNode();
  }

  @Override
  public TypeNode visit(AbsSynTree.PlusNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    // One can be subtype of other.
    if (!(SyntaxTreeUtils
        .isSubtype(visit(n.getLeft()), new AbsSynTree.IntTypeNode())
        && SyntaxTreeUtils
        .isSubtype(visit(n.getRight()), new AbsSynTree.IntTypeNode()))) {
      typeErrors++;
      throw new TypeException("Non integers in multiplication", n.getLine());
    }
    return new AbsSynTree.IntTypeNode();
  }

  @Override
  public TypeNode visit(AbsSynTree.IdNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    final var t = n.getEntry().getType();
    if (t instanceof AbsSynTree.ArrowTypeNode) {
      typeErrors++;
      throw new TypeException("Wrong usage of function identifier " + n.getId(),
          n.getLine());
    }
    return t;
  }

  @Override
  public TypeNode visit(AbsSynTree.CallNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    for (Node node : n.getArgumentList()) {
      visit(node);
    }
    return null;
  }

  @Override
  public TypeNode visit(AbsSynTree.IntValueNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    return new AbsSynTree.IntTypeNode();
  }

  @Override
  public TypeNode visit(AbsSynTree.BoolValueNode n) throws TypeException {
    if (mustPrint()) {
      printNode(n);
    }
    return new AbsSynTree.BoolTypeNode();
  }

  public int getTypeErrors() {
    return typeErrors;
  }
}
