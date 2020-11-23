package fool.compiler.ast;

/**
 * Represent a single node for the AST.
 *
 * @author ap
 */
public abstract class Node {

    // This is the line in the file of the node.
    int line;

    public void setLine(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public Node() {
        // Is set at -1 to debug the right assignment of this var.
        line = -1;
    }

    /**
     * Use the specific visit implementation at runtime.
     *
     * @param visitor Visitor to recall.
     * @return TODO
     */
    public abstract <S> S accept(ASTVisitor<S> visitor);
}
