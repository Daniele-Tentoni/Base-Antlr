package fool.compiler.ast.lib;

import fool.compiler.UnImplementedException;
import fool.compiler.Visitable;
import fool.compiler.ast.AbstractSyntaxTree;

import static fool.compiler.SyntaxTreeUtils.extractNodeName;

/**
 * Visit an Abstract Syntax Tree. Implement to return a different type.
 *
 * @param <T> return type of visitor.
 */
public class AbsSynTreeVisitor<T> {

  private final boolean print;
  private String indent;

  /**
   * You can enable or not the print of all results.
   *
   * @param debug enable or not result prints.
   */
  protected AbsSynTreeVisitor(boolean debug) {
    print = debug;
    indent = "";
  }

  /**
   * Default constructor.
   */
  protected AbsSynTreeVisitor() {
    this(false);
  }

  protected boolean mustPrint() {
    return print;
  }

  protected String getIndent() {
    return indent;
  }

  protected void printNode(Node n, String s) {
    // returns a string compiler.AST$ClassName
    final String className = n.getClass().getName();
    final String nodeName = extractNodeName(className);
    System.out.println(indent + nodeName + ": " + s);
  }

  protected void printNode(Node n) {
    // TODO: We can refactor those two methods?
    final String className = n.getClass().getName();
    final String nodeName = extractNodeName(className);
    System.out.println(indent + nodeName);
  }

  public T visit(Visitable v, String s) {
    if (mustPrint()) {
      final String temp = indent;
      indent = (indent == null) ? "" : indent + s;
      // Dobbiamo eseguire qui la visitazione del'albero per sfruttare l'identazione.
      final T result = visitByAcc(v);
      // Tramite questa istruzione resettiamo la indent prima di tornare al livello superiore.
      indent = temp;
      return result;
    }

    return visitByAcc(v);
  }

  public T visit(Visitable v) {
    return visit(v, " ");
  }

  /**
   * Metodo di visita generico per tutti i nodes. A runtime eseguiremo sempre
   * questo metodo. Quindi dobbiamo poi redirigere la computazione dove vogliamo.
   *
   * @param v il Node da valutare.
   * @return TODO
   */
  private T visitByAcc(Visitable v) {
    return v.accept(this);
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

  public T visit(AbstractSyntaxTree.ParameterNode n) {
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