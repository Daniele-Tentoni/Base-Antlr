package fool.compiler;

/**
 * Ereditiamo da una eccezione unchecked. Non viene catturata dal compilatore di
 * Java. Quindi non siamo obbligati ad inserirla in un try catch.
 *
 * @author ap
 */
public class UnImplementedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnImplementedException(String message){
        super(message);
    }

    public UnImplementedException(){
        super();
    }

}
