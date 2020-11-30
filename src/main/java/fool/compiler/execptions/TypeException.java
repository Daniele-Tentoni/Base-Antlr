package fool.compiler.execptions;
public class TypeException extends Exception {
  private static final long serialVersionUID = 1L;

  public TypeException(String m, int line) {
    // TODO: Add error to counter.
    super(m + " at line " + line);
  }
}
