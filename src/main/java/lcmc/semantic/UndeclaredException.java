package lcmc.semantic;

public class UndeclaredException extends Exception {
    public UndeclaredException() {
        super("Undeclared variable");
    }
}
