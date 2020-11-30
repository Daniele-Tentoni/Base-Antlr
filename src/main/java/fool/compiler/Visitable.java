package fool.compiler;

import fool.compiler.abssyntree.visitors.AbsSynTreeVisitor;

/**
 * Provide a contract for any visitable object from a Visitor.
 */
public interface Visitable {
  /**
   * Use the Visitor Pattern to call correct visit method on
   * the runtime instance of a Visitable instead is compile time type.
   *
   * @param visitor visitor to recall.
   * @param <S>     return type of the visitor.
   * @return visit return value.
   */
  <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor) throws E;
}