package fool.compiler.ast.lib.nodes;

import fool.compiler.Visitable;

/**
 * Represent a single node for the AST.
 *
 * @author ap
 */
public abstract class Node implements Visitable {

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
}
