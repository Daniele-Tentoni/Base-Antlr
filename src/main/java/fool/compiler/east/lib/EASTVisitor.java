package fool.compiler.east.lib;

import fool.compiler.UnImplementedException;
import fool.compiler.ast.lib.ASTVisitor;

/**
 * Visit an Abstract Syntax Tree. Implement to return a different type.
 *
 * @param <T> return type of visitor methods.
 */
public class EASTVisitor<T> extends ASTVisitor<T> {

  /**
   * Simple constructor. Set debug value at false by default.
   */
  protected EASTVisitor() {
    this(false);
  }

  /**
   * Complex constructor.
   *
   * @param debug debug value.
   */
  protected EASTVisitor(boolean debug) {
    super(debug);
  }

  /**
   * Print a Symbol Table Entry.
   *
   * @param s entry to print.
   */
  protected void printSTEntry(String s) {
    System.out.println(getIndent() + "Symbol Table Entry: " + s);
  }

  /**
   * Visit a Symbol Table Entry.
   *
   * @param s entry to visit.
   * @return value calculated.
   */
  public T visit(SymbolTableEntry s) {
    throw new UnImplementedException();
  }
}
