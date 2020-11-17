package fool.compiler.lib;

import fool.compiler.AST.*;

/**
 * Classe che ci permette di stampare l'ast.
 *
 * @author ap nono, è il professore che l'ha fatto.
 */
public class ASTVisitor<T> {

    private String indent;
    private final boolean print;

    /**
     * You can enable or not the print of all results.
     *
     * @param print enable or not result prints.
     */
    protected ASTVisitor(boolean print) {
        this.print = print;
    }

    /**
     * Default constructor.
     */
    protected ASTVisitor() {
        this.print = false;
    }

    protected boolean mustPrint() {
        return print;
    }

    public T visit(Node n) {
        if (print) {
            String temp = indent;
            indent = (indent == null) ? "" : indent + "  ";
            /*
             * Tramite questa istruzione resettiamo la indent prima di tornare al livello
             * superiore.
             */
            indent = temp;
        }

        return visitByAcc(n);
    }

    /**
     * Metodo di visita generico per tutti i nodes. A runtime eseguiremo sempre
     * questo metodo. Quindi dobbiamo poi redirigere la computazione dove vogliamo.
     *
     * @param n il Node da valutare.
     * @return TODO
     */
    public T visitByAcc(Node n) {
        return n.accept(this);
    }

    /**
     * Visita per il node Prog.
     *
     * @param n il Prog Node da visitare.
     * @return TODO
     */
    public T visit(ProgNode n) {
        throw new UnImplementedException();
    }

    /**
     * Visita per il node Times.
     *
     * @param n il Times Node da visitare.
     * @return TODO
     */
    public T visit(TimesNode n) {
        throw new UnImplementedException();
    }

    /**
     * Visita per il node Plus.
     *
     * @param n il Plus node da visitare.
     * @return TODO
     */
    public T visit(PlusNode n) {
        throw new UnImplementedException();
    }

    /**
     * Visita per il node Int.
     *
     * @param n il Int node da visitare.
     * @return TODO
     */
    public T visit(IntNode n) {
        throw new UnImplementedException();
    }

    public T visit(EqualNode n) {
        throw new UnImplementedException();
    }

    public T visit(BoolNode n) {
        throw new UnImplementedException();
    }

    public T visit(IfNode n) {
        throw new UnImplementedException();
    }

    public T visit(PrintNode n) {
        throw new UnImplementedException();
    }

    protected void printNode(Node n, String s) {
        String className = n.getClass().getName(); // returns a string compiler.AST$ClassName
        String nodeName = className.substring(className.lastIndexOf('$') + 1, className.length() - 4);
        System.out.println(indent + nodeName + s);
    }

    protected void printNode(Node n) {
        printNode(n, "");
    }

}