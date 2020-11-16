package lcmc.semantic;

import lcmc.semantic.symbols.Symbol;

/**
 * Represent the single entry for the table.
 */
public class SymbolEntry {
    private final Symbol symbol;

    public SymbolEntry(final Symbol symbol) {
        this.symbol = symbol;
    }

    /**
     * Return the symbol in the entry.
     *
     * @return the Symbol.
     */
    public Symbol getSymbol() {
        return symbol;
    }
}
