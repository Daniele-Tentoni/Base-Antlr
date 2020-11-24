package fool.compiler;

import fool.compiler.ast.lib.ASTVisitor;

/**
 * Provide a contract for any visitable object from a Visitor.
 */
public interface Visitable {
  /**
   * Use the Visitor Pattern to call correct visit method on
   * the runtime instance of a Node instead is compile time type.
   *
   * @param visitor that have to call visit method.
   * @param <S>     return type of the visitor.
   * @return visit return value.
   */
  <S> S accept(ASTVisitor<S> visitor);
}
