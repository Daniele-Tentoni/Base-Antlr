package fool.compiler.execptions;

/**
 * Thrown when, due to lexical or semantical errors, we're not able to generate
 * the correct Abstract Syntax Tree.
 */
public class IncompleteException extends RuntimeException {
  private static final long serialVersionUID = 1L;
}
