package fool.compiler.east;

import fool.compiler.ast.AbstractSyntaxTree;
import fool.compiler.ast.lib.Node;
import fool.compiler.east.lib.SymbolTableEntry;
import fool.compiler.east.lib.EASTVisitor;

public class PrintEASTVisitor extends EASTVisitor<Void> {
  PrintEASTVisitor() {
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
    for (Node dec : n.getDeclarationList()) visit(dec);
    visit(n.getExp());
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
    // for (ParNode par : n.parlist) visit(par);
    for (Node dec : n.getDeclarationList()) visit(dec);
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IdNode n) {
    printNode(n, n.getId());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.CallNode n) {
    printNode(n, n.getId());
    // for (Node arg : n.arglist) visit(arg);
    return null;
  }

  @Override
  public Void visit(SymbolTableEntry entry) {
    super.printSTEntry("nestlev " + entry.getNestingLevel());
    return null;
  }
}
