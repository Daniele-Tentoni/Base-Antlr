package fool.compiler.lib;

/**
 * Represent a single node for the AST.
 *
 * @author ap
 */
public interface Node {
    /**
     * Use the specific visit implementation at runtime.
     *
     * @param visitor Visitor to recall.
     * @return TODO
     */
    <S> S accept(ASTVisitor<S> visitor);
}
