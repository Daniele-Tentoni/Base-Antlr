package lcmc.semantic.symbols;

/**
 * The declaration of a symbol.
 *
 * @param <T> Type of the symbol.
 */
public class Declaration<T> extends Symbol {
    public Declaration(final String name, final Class<T> type) {
        super(name, type);
    }
}
