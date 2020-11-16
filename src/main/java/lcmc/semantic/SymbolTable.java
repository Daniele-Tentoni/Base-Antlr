package lcmc.semantic;

import lcmc.semantic.symbols.Declaration;
import lcmc.semantic.symbols.Usage;

/**
 * Provide an abstractation for the Symbol Table.
 */
public interface SymbolTable {
    void scopeEntry();

    void processDeclaration(Declaration declaration) throws AlreadyDeclaredException;

    void processStatement(Usage usage) throws UndeclaredException;

    void scopeExit();
}

