package fool.compiler.east;

import fool.compiler.ast.AbstractSyntaxTree;
import fool.compiler.east.lib.EnrAbsSynTreeVisitor;
import fool.compiler.east.lib.SymTabEntry;

public class PrintEnrAbsSynTreeVisitor extends EnrAbsSynTreeVisitor<Void> {
  public PrintEnrAbsSynTreeVisitor() {
    super(true);
  }

  @Override
  public Void visit(AbstractSyntaxTree.ProgNode n) {
    printNode(n);
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IntValueNode n) {
    printNode(n, String.valueOf(n.getVal()));
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.PlusNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.TimesNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.EqualNode n) {
    printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.BoolValueNode n) {
    printNode(n, String.valueOf(n.getVal()));
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

  //
  @Override
  public Void visit(AbstractSyntaxTree.ProgLetInNode n) {
    printNode(n);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExpression());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.BoolTypeNode n) {
    printNode(n);
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IntTypeNode n) {
    printNode(n);
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.VarNode n) {
    printNode(n, n.getId());
    visit(n.getType());
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.FunNode n) {
    printNode(n, n.getId());
    visit(n.getRetType());
    n.getParameterList().forEach(this::visit);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IdNode n) {
    printNode(n, n.getId());
    var entry = n.getEntry();
    if (entry != null) {
      visit(n.getEntry());
    }
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.CallNode n) {
    printNode(n, n.getId());
    var entry = n.getEntry();
    if (entry != null) {
      visit(n.getEntry());
    }
    n.getArgumentList().forEach(this::visit);
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.ParameterNode n) {
    printNode(n, n.getId());
    visit(n.getType());
    return null;
  }

  @Override
  public Void visitSymTabEntry(SymTabEntry entry) {
    super.printSymTabEntry("nesting level " + entry.getNestingLevel());
    return null;
  }
}
