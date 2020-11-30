package fool.compiler.abssyntree.lib.nodes;

import fool.compiler.Visitable;

/**
 * Represent a single node for the AST.
 *
 * @author ap
 */
public abstract class Node implements Visitable {
  private int line;

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
