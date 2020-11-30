package fool.compiler.execptions;

/**
 * Thrown when Type Checker find an error using type with operations.
 */
public class TypeException extends Exception {
  private static final long serialVersionUID = 1L;

  public TypeException(String m, int line) {
    // TODO: Add error to counter.
    super(m + " at line " + line);
  }
}
