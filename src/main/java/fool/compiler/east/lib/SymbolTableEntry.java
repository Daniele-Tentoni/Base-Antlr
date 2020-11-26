package fool.compiler.east.lib;

import fool.compiler.Visitable;
import fool.compiler.ast.lib.ASTVisitor;
import java.util.Objects;

/**
 * Entry for any symbol in the program.
 */
public class SymbolTableEntry implements Visitable {
  private final int nestingLevel;

  /**
   * Create the entry with the defined nesting level.
   *
   * @param nl nesting level.
   */
  public SymbolTableEntry(int nl) {
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
   * @param visitor that have to call visit method.
   * @param <S>     return type.
   * @return return value.
   */
  @Override
  public <S> S accept(ASTVisitor<S> visitor) {
    Objects.requireNonNull(visitor);
    if (!visitor.getClass().equals(EASTVisitor.class)) {
      System.out.println("Class not compatible");
    }
    return ((EASTVisitor<S>) visitor).visit(this);
  }
}
