package fool.compiler.east.lib;

import fool.compiler.Visitable;
import fool.compiler.ast.lib.AbsSynTreeVisitor;
import fool.compiler.ast.lib.nodes.Node;
import java.util.Objects;

/**
 * Entity for Symbol Table Entry.
 * There is an entry for any symbol in the program.
 */
public class SymTabEntry implements Visitable {
  private final int nestingLevel;
  private Node type;

  /**
   * Create the entry with the defined nesting level.
   *
   * @param nl nesting level.
   */
  public SymTabEntry(int nl) {
    nestingLevel = nl;
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
  public <S> S accept(AbsSynTreeVisitor<S> visitor) {
    Objects.requireNonNull(visitor);

    // Make sure that visitor is an EASTVisitor.
    var visitorClass = visitor.getClass();
    var visitorSuperClass = visitorClass.getSuperclass();
    if (!visitorSuperClass.equals(EnrAbsSynTreeVisitor.class)) {
      System.out.println("Class not compatible");
    }

    return ((EnrAbsSynTreeVisitor<S>) visitor).visitSymTabEntry(this);
  }
}