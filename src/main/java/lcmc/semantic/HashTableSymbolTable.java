package lcmc.semantic;

import lcmc.semantic.symbols.Declaration;
import lcmc.semantic.symbols.Usage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * This table have a table of lists.
 * Powerful on more deeper programs.
 */
public class HashTableSymbolTable implements SymbolTable {
    private int index;
    private final Stack<List<SymbolEntry>> stack;

    public HashTableSymbolTable() {
        index = 0;
        stack = new Stack<>();
    }

    @Override
    public void scopeEntry() {
        index++;
        stack.push(new LinkedList<>());
    }

    @Override
    public void processDeclaration(Declaration declaration) throws AlreadyDeclaredException {
        // Get the current list of symbols.
        List<SymbolEntry> current = stack.peek();
        // Check if declaration is already used.
        boolean same = current.stream().anyMatch(sym -> sym.getSymbol().getName().equals(declaration.getName()));
        if(same) {
            // The declaration is already used.
            throw new AlreadyDeclaredException();
        }

        // The declaration is not used, we add it to the table.
        SymbolEntry entry = new SymbolEntry(declaration);
        current.add(entry);
    }

    @Override
    public void processStatement(Usage usage) throws UndeclaredException {
        List<SymbolEntry> current = stack.peek();
        Optional<SymbolEntry> declaration = current.stream().filter(sym -> sym.getSymbol().getName().equals(usage.getName())).findAny();
        if(declaration.isEmpty()) {
            // The variable is not declared.
            throw new UndeclaredException();
        }

        // The use is permitted, we update the value in the table.

    }

    @Override
    public void scopeExit() {
        index--;
        // We have to update all the values in the table.
        stack.pop();
    }
}
