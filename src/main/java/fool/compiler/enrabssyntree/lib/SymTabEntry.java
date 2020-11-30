package fool.compiler.enrabssyntree.lib;

import fool.compiler.Visitable;
import fool.compiler.abssyntree.visitors.AbsSynTreeVisitor;
import fool.compiler.abssyntree.lib.nodes.TypeNode;
import fool.compiler.enrabssyntree.visitors.EnrAbsSynTreeVisitor;
import java.util.Objects;

/**
 * Entity for Symbol Table Entry.
 * There is an entry for any symbol in the program.
 */
public class SymTabEntry implements Visitable {
  private final int nestingLevel;
  private TypeNode type;

  /**
   * Create the entry with the defined nesting level.
   *
   * @param nl nesting level.
   */
  public SymTabEntry(int nl, TypeNode node) {
    nestingLevel = nl;
    type = node;
  }

  /**
   * @return the nesting level for this entry.
   */
  public int getNestingLevel() {
    return nestingLevel;
  }

  /**
   * Visitor pattern to call methods on runtime type instance.
   *
   * @param visitor visitor to recall. Must be an EASTVisitor.
   * @param <S>     return type.
   * @return return value.
   */
  @Override
  public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
      throws E {
    Objects.requireNonNull(visitor);

    // Make sure that visitor is an EASTVisitor.
    var visitorClass = visitor.getClass();
    var visitorSuperClass = visitorClass.getSuperclass();
    if (!visitorSuperClass.equals(EnrAbsSynTreeVisitor.class)) {
      System.out.println("Class not compatible");
      throw new ClassCastException("Need a EnrAbsSynTreeVisitor class");
    }

    return ((EnrAbsSynTreeVisitor<S, E>) visitor).visitSymTabEntry(this);
  }

  public TypeNode getType() {
    return type;
  }
}