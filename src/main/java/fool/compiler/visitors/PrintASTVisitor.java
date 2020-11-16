package fool.compiler.visitors;

import fool.compiler.AST.*;
import fool.compiler.lib.ASTVisitor;

/**
 * Classe che ci permette di stampare l'ast.
 *
 * @author ap nono, è il professore che l'ha fatto.
 */
public class PrintASTVisitor extends ASTVisitor<Void> {

    public PrintASTVisitor() {
        super(true);
    }

    /**
     * Visita per il node Prog.
     *
     * @param n il Prog Node da visitare.
     */
    public Void visit(ProgNode n) {
        printNode(n);
        visit(n.getExp());
        return null;
    }

    /**
     * Visita per il node Times.
     *
     * @param n il Times Node da visitare.
     */
    public Void visit(TimesNode n) {
        printNode(n);
        visit(n.getLeft());
        visit(n.getRight());
        return null;
    }

    /**
     * Visita per il node Plus.
     *
     * @param n il Plus node da visitare.
     */
    public Void visit(PlusNode n) {
        printNode(n);
        visit(n.getLeft());
        visit(n.getRight());
        return null;
    }

    /**
     * Visita per il node Int.
     *
     * @param n il Int node da visitare.
     */
    public Void visit(IntNode n) {
        printNode(n, ": " + n.getVal());
        return null;
    }
}