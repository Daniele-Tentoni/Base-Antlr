package fool.compiler.ast;

import fool.compiler.ast.AST.*;
import fool.compiler.ast.lib.ASTVisitor;

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
    public Void visit(IntValueNode n) {
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
    public Void visit(BoolValueNode n) {
        printNode(n);
        return null;
    }

    @Override
    public Void visit(IfNode n) {
        printNode(n);
        visit(n.getCondition());
        visit(n.getThen());
        visit(n.getElse());
        return null;
    }

    @Override
    public Void visit(PrintNode n) {
        printNode(n);
        visit(n.getPrint());
        return null;
    }

    @Override
    public Void visit(ProgLetInNode n){
        printNode(n);
        n.getDeclarationList().forEach(this::visit);
        visit(n.getExp());
        return null;
    }
}