package fool.compiler.visitors;

import fool.compiler.AST.*;
import fool.compiler.lib.ASTVisitor;

/**
 * Classe che ci permette di stampare l'ast.
 *
 * @author ap nono, è il professore che l'ha fatto.
 *
 */
public class CalcASTVisitor extends ASTVisitor<Integer> {

    public CalcASTVisitor(boolean print) {
        super(print);
    }

    public CalcASTVisitor() {
        super();
    }

    /**
     * Visita per il node Prog.
     *
     * @param n il Prog Node da visitare.
     */
    public Integer visit(ProgNode n) {
        if(super.mustPrint()) super.printNode(n);
        return visit(n.getExp());
    }

    /**
     * Visita per il node Times.
     *
     * @param n il Times Node da visitare.
     */
    public Integer visit(TimesNode n) {
        if(super.mustPrint()) super.printNode(n);
        return visit(n.getLeft()) * visit(n.getRight());
    }

    /**
     * Visita per il node Plus.
     *
     * @param n il Plus node da visitare.
     */
    public Integer visit(PlusNode n) {
        if(super.mustPrint()) super.printNode(n);
        return visit(n.getLeft()) + visit(n.getRight());
    }

    /**
     * Visita per il node Int.
     *
     * @param n il Int node da visitare.
     */
    public Integer visit(IntNode n) {
        if(super.mustPrint()) super.printNode(n);
        return n.getVal();
    }
}