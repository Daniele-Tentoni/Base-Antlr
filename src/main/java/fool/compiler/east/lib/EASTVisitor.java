package fool.compiler.east.lib;

import fool.compiler.UnImplementedException;
import fool.compiler.ast.lib.ASTVisitor;

public class EASTVisitor<S> extends ASTVisitor<S> {

  protected EASTVisitor() {
  }

  protected EASTVisitor(boolean p) {
    super(p);
  }

  protected void printSTEntry(String s) {
    System.out.println(getIndent() + "Symbol Table Entry: " + s);
  }

  public S visit(SymbolTableEntry s) {
    throw new UnImplementedException();
  }
}
