package fool.compiler.east.lib;

import fool.compiler.UnImplementedException;
import fool.compiler.ast.lib.AbsSynTreeVisitor;

/**
 * Visit an Abstract Syntax Tree. Implement to return a different type.
 *
 * @param <T> return type of visitor methods.
 */
public class EnrAbsSynTreeVisitor<T> extends AbsSynTreeVisitor<T> {

  /**
   * Simple constructor. Set debug value at false by default.
   */
  protected EnrAbsSynTreeVisitor() {
    this(false);
  }

  /**
   * Complex constructor.
   *
   * @param debug debug value.
   */
  protected EnrAbsSynTreeVisitor(boolean debug) {
    super(debug);
  }

  /**
   * Print a Symbol Table Entry.
   *
   * @param s entry to print.
   */
  protected void printSymTabEntry(String s) {
    System.out.println(getIndent() + "Symbol Table Entry: " + s);
  }

  /**
   * Visit a Symbol Table Entry.
   *
   * @param s entry to visit.
   * @return value calculated.
   */
  public T visitSymTabEntry(SymTabEntry s) {
    throw new UnImplementedException();
  }
}
