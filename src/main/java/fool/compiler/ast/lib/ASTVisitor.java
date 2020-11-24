package fool.compiler.ast.lib;

import fool.compiler.ast.AbstractSyntaxTree;
import fool.compiler.UnImplementedException;

/**
 * Classe che ci permette di stampare l'ast.
 *
 * @author ap nono, è il professore che l'ha fatto.
 */
public class ASTVisitor<T> {

  private final boolean print;
  private String indent;

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

  protected String getIndent() {
    return indent;
  }

  protected void printNode(Node n, String s) {
    final String className = n.getClass()
        .getName(); // returns a string compiler.AST$ClassName
    final String nodeName = className
        .substring(className.lastIndexOf('$') + 1, className.length() - 4);
    System.out.println(indent + nodeName + s);
  }

  protected void printNode(Node n) {
    printNode(n, "");
  }

  public T visit(Node n, String s) {
    if (mustPrint()) {
      final String temp = indent;
      indent = (indent == null) ? "" : indent + s;
      // Dobbiamo eseguire qui la visitazione del'albero per sfruttare l'identazione.
      final T result = visitByAcc(n);
      // Tramite questa istruzione resettiamo la indent prima di tornare al livello superiore.
      indent = temp;
      return result;
    }

    return visitByAcc(n);
  }

  public T visit(Node n) {
    return n.accept(this);
  }

  /**
   * Metodo di visita generico per tutti i nodes. A runtime eseguiremo sempre
   * questo metodo. Quindi dobbiamo poi redirigere la computazione dove vogliamo.
   *
   * @param n il Node da valutare.
   * @return TODO
   */
  private T visitByAcc(Node n) {
    return n.accept(this);
  }

  /**
   * Visita per il node Prog.
   *
   * @param n il Prog Node da visitare.
   * @return TODO
   */
  public T visit(AbstractSyntaxTree.ProgNode n) {
    throw new UnImplementedException("ProgNode");
  }

  /**
   * Visita per il node Times.
   *
   * @param n il Times Node da visitare.
   * @return TODO
   */
  public T visit(AbstractSyntaxTree.TimesNode n) {
    throw new UnImplementedException();
  }

  /**
   * Visita per il node Plus.
   *
   * @param n il Plus node da visitare.
   * @return TODO
   */
  public T visit(AbstractSyntaxTree.PlusNode n) {
    throw new UnImplementedException();
  }

  /**
   * Visita per il node Int.
   *
   * @param n il Int node da visitare.
   * @return TODO
   */
  public T visit(AbstractSyntaxTree.IntValueNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.EqualNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.BoolValueNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.IfNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.PrintNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.ProgLetInNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.BoolTypeNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.IntTypeNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.VarNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.FunNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.IdNode n) {
    throw new UnImplementedException();
  }

  public T visit(AbstractSyntaxTree.CallNode n) {
    throw new UnImplementedException();
  }

}