package fool.compiler.ast;

import fool.compiler.ast.AST.*;

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
    @Override
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
    @Override
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
    @Override
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
    @Override
    public Void visit(IntNode n) {
        printNode(n, ": " + n.getVal());
        return null;
    }

    @Override
    public Void visit(EqualNode n) {
        printNode(n);
        visit(n.getLeft());
        visit(n.getRight());
        return null;
    }

    @Override
    public Void visit(BoolNode n) {
        printNode(n);
        return null;
    }

    @Override
    public Void visit(IfNode n) {
        printNode(n);
        visit(n.getCondition());
        visit(n.getThen());
        visit(n.getEls());
        return null;
    }

    @Override
    public Void visit(PrintNode n) {
        printNode(n);
        visit(n.getPrint());
        return null;
    }
}