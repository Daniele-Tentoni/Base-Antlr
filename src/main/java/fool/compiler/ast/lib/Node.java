package fool.compiler.ast.lib;

/**
 * Represent a single node for the AST.
 *
 * @author ap
 */
public abstract class Node {

  // This is the line in the file of the node.
  private int line;

  /**
   * Complex constructor. Set defined line number.
   *
   * @param n line number.
   */
  public Node(int n) {
    line = n;
  }

  /**
   * Simple constructor. Set the line at -1 by default.
   */
  public Node() {
    // Is set at -1 to debug the right assignment of this var.
    this(-1);
  }

  /**
   * Get the line where the node is used.
   *
   * @return line number.
   */
  public int getLine() {
    return line;
  }

  /**
   * Set the line where the node is used.
   *
   * @param line line number.
   */
  public void setLine(int line) {
    this.line = line;
  }

  /**
   * Use the specific visit implementation at runtime.
   *
   * @param visitor visitor to recall.
   * @param <S>     return type.
   * @return TODO
   */
  public abstract <S> S accept(ASTVisitor<S> visitor);
}
