package lcmc.semantic.symbols;

/**
 * Abstract symbol to put in the table.
 */
public abstract class Symbol {
    private final String name;
    private final Class type;

    public Symbol(final String name, final Class type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Get the name of the symbol.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of the symbol.
     *
     * @return the type.
     */
    public Class getType() {
        return type;
    }
}
