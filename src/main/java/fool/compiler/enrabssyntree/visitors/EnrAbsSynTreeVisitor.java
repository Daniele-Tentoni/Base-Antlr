package fool.compiler.enrabssyntree.visitors;

import fool.compiler.abssyntree.AbsSynTree;
import fool.compiler.abssyntree.visitors.AbsSynTreeVisitor;
import fool.compiler.enrabssyntree.lib.SymTabEntry;
import fool.compiler.execptions.UnImplementedException;

/**
 * Visit an Abstract Syntax Tree. Implement to return a different type.
 *
 * @param <T> return type of visitor methods.
 */
public class EnrAbsSynTreeVisitor<T, E extends Exception>
    extends AbsSynTreeVisitor<T
    , E> {

  /**
   * Simple constructor. Set debug value at false by default.
   */
  protected EnrAbsSynTreeVisitor() {
    this(false);
  }

  protected EnrAbsSynTreeVisitor(boolean ie) {
    this(ie, false);
  }

  /**
   * Complex constructor.
   *
   * @param debug debug value.
   */
  protected EnrAbsSynTreeVisitor(boolean ie, boolean debug) {
    super(ie, debug);
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

  /**
   * Visit an Arrow Type Node, applying visitor pattern.
   *
   * @param n node.
   * @return value calculated.
   */
  public T visit(AbsSynTree.ArrowTypeNode n) {
    throw new UnImplementedException();
  }
}
