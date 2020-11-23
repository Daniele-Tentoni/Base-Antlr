package fool.compiler.ast;

import fool.compiler.ast.AST.*;
import fool.compiler.ast.lib.ASTVisitor;

/**
 * Classe che ci permette di stampare l'ast.
 *
 * @author ap nono, è il professore che l'ha fatto.
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
    @Override
    public Integer visit(ProgNode n) {
        if (super.mustPrint()) super.printNode(n);
        return visit(n.getExp());
    }

    /**
     * Visita per il node Times.
     *
     * @param n il Times Node da visitare.
     */
    @Override
    public Integer visit(TimesNode n) {
        if (super.mustPrint()) super.printNode(n);
        return visit(n.getLeft()) * visit(n.getRight());
    }

    /**
     * Visita per il node Plus.
     *
     * @param n il Plus node da visitare.
     */
    @Override
    public Integer visit(PlusNode n) {
        if (super.mustPrint()) super.printNode(n);
        return visit(n.getLeft()) + visit(n.getRight());
    }

    /**
     * Visita per il node Int.
     *
     * @param n il Int node da visitare.
     */
    @Override
    public Integer visit(IntNode n) {
        if (super.mustPrint()) super.printNode(n);
        return n.getVal();
    }

    @Override
    public Integer visit(EqualNode n) {
        if (super.mustPrint()) super.printNode(n);
        boolean equality = visit(n.getLeft()).equals(visit(n.getRight()));
        return equality ? 0 : 1;
    }

    @Override
    public Integer visit(BoolNode n) {
        if (super.mustPrint()) super.printNode(n);
        return n.getVal() ? 0 : 1;
    }

    @Override
    public Integer visit(IfNode n) {
        if (super.mustPrint()) super.printNode(n);
        boolean equality = visit(n.getCondition()) == 0;
        return equality ? visit(n.getThen()) : visit(n.getEls());
    }

    @Override
    public Integer visit(PrintNode n) {
        if (super.mustPrint()) super.printNode(n);
        return visit(n.getPrint());
    }
}