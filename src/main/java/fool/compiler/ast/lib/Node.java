package fool.compiler.ast.lib;

/**
 * Represent a single node for the AST.
 *
 * @author ap
 */
public abstract class Node {

  // This is the line in the file of the node.
  private int line;

  public Node() {
    // Is set at -1 to debug the right assignment of this var.
    line = -1;
  }

  public int getLine() {
    return line;
  }

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
