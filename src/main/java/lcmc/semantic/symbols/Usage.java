package lcmc.semantic.symbols;

/**
 * Represent the usage of a symbol.
 *
 * @param <T> Type of the symbol.
 */
public class Usage<T> extends Symbol {
    private final T value;

    public Usage(final String name, final Class<T> type, final T value) {
        super(name, type);
        this.value = value;
    }

    /**
     * Get the value of the usage.
     *
     * @return the value.
     */
    public T getValue() {
        return value;
    }
}
