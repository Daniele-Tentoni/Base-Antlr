package lcmc;

/**
 * Provide an abstractation for the Symbol Table.
 */
public interface SymbolTable {
    void scopeEntry();

    void processDeclaration(Declaration declaration);

    void processStatement(Usage usage);

    void scopeExit();
}
