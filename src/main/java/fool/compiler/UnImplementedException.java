package fool.compiler;

/**
 * Ereditiamo da una eccezione unchecked. Non viene catturata dal compilatore di
 * Java. Quindi non siamo obbligati ad inserirla in un try catch.
 *
 * @author ap
 */
public class UnImplementedException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Instance a new Exception with a user message.
   *
   * @param message message for the user.
   */
  public UnImplementedException(final String message) {
    super(message);
  }

  /**
   * Instance a new Exception without a message.
   */
  public UnImplementedException() {
    super();
  }

}
