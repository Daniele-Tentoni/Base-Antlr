package fool.compiler.abssyntree.lib.nodes;

/**
 * Represent an abstract Value Node. Doesn't contains types.
 */
public abstract class ValueNode<T> extends Node {
  private final T value;

  public ValueNode(final T v) {
    value = v;
  }

  public T getValue() {
    return value;
  }
}
