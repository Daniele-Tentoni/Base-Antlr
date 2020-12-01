package fool.compiler.abssyntree.visitors;

import fool.compiler.SyntaxTreeUtils;
import fool.compiler.Visitable;
import fool.compiler.abssyntree.AbsSynTree;
import fool.compiler.abssyntree.lib.nodes.Node;
import fool.compiler.execptions.IncompleteException;
import fool.compiler.execptions.UnImplementedException;

/**
 * Visit an Abstract Syntax Tree. Implement to return a different type.
 *
 * @param <T> return type of visitor.
 */
public class AbsSynTreeVisitor<T, E extends Exception> {

  private final boolean print;
  private final boolean incompleteException;
  private String indent;

  /**
   * You can enable or not the print of all results.
   *
   * @param debug enable or not result prints.
   */
  protected AbsSynTreeVisitor(boolean ie, boolean debug) {
    print = debug;
    indent = "";
    incompleteException = ie;
  }

  protected AbsSynTreeVisitor(boolean ie) {
    this(ie, false);
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
    final String nodeName = SyntaxTreeUtils.extractNodeName(className);
    System.out.println(indent + nodeName + ": " + s);
  }

  protected void printNode(Node n) {
    // TODO: We can refactor those two methods?
    final String className = n.getClass().getName();
    final String nodeName = SyntaxTreeUtils.extractNodeName(className);
    System.out.println(indent + nodeName);
  }

  /**
   * Visit a visitable object printing an additional String.
   *
   * @param v visitable object.
   * @param s additional string to print.
   * @return calculated value.
   */
  public T visit(Visitable v, String s) throws E {
    if (v == null) {
      if (incompleteException) {
        throw new IncompleteException();
      } else {
        return null;
      }
    }

    if (mustPrint()) {
      final String temp = indent;
      indent = (indent == null) ? "" : indent + "  ";
      indent += s;
      // Visit further node with increased indentation.
      try {
        return visitByAcc(v);
      } finally {
        // Reset indentation in any case.
        indent = temp;
      }
    }

    return visitByAcc(v);
  }

  public T visit(Visitable v) throws E {
    return visit(v, "");
  }

  /**
   * Generic visit method. Is called a runtime to enable the visitor pattern.
   *
   * @param v visitable object with accept method.
   * @return value computed.
   */
  private T visitByAcc(Visitable v) throws E {
    return v.accept(this);
  }

  public T visit(AbsSynTree.ProgLetInNode n) throws E {
    throw new UnImplementedException();
  }

  /**
   * Visit a Prog Node.
   *
   * @param n node to visit.
   * @return value computed.
   */
  public T visit(AbsSynTree.ProgNode n) throws E {
    throw new UnImplementedException("ProgNode");
  }

  public T visit(AbsSynTree.FunNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.ParameterNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.VarNode n) throws E {
    throw new UnImplementedException();
  }

  /**
   * Abstract method visit for ArrowTypeNode. Must be overridden by visitor
   * pattern.
   *
   * @param n node to visit.
   * @return calculated value.
   * @throws E possible exception.
   */
  public T visit(AbsSynTree.ArrowTypeNode n) throws E {
    throw new UnImplementedException("ArrowTypeNode visit isn't implemented "
        + "yet.");
  }

  public T visit(AbsSynTree.BoolTypeNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.IntTypeNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.PrintNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.IfNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.EqualNode n) throws E {
    throw new UnImplementedException();
  }

  /**
   * Visita per il node Times.
   *
   * @param n il Times Node da visitare.
   * @return value computed.
   */
  public T visit(AbsSynTree.TimesNode n) throws E {
    throw new UnImplementedException();
  }

  /**
   * Visita per il node Plus.
   *
   * @param n il Plus node da visitare.
   * @return value computed.
   */
  public T visit(AbsSynTree.PlusNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.IdNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.CallNode n) throws E {
    throw new UnImplementedException();
  }

  /**
   * Visita per il node Int.
   *
   * @param n il Int node da visitare.
   * @return value computed.
   */
  public T visit(AbsSynTree.IntValueNode n) throws E {
    throw new UnImplementedException();
  }

  public T visit(AbsSynTree.BoolValueNode n) throws E {
    throw new UnImplementedException();
  }
}