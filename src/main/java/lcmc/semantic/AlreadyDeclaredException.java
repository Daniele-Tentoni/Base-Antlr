package lcmc.semantic;

public class AlreadyDeclaredException extends Exception {
    public AlreadyDeclaredException() {
        super("The variable is already declared");
    }
}

