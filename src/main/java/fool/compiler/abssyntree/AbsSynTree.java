package fool.compiler.abssyntree;

import fool.compiler.abssyntree.lib.nodes.Node;
import fool.compiler.abssyntree.lib.nodes.TypeNode;
import fool.compiler.abssyntree.visitors.AbsSynTreeVisitor;
import fool.compiler.enrabssyntree.lib.SymTabEntry;
import java.util.List;

/**
 * Collection of all nodes of the ABS of FOOL language.
 */
public class AbsSynTree {
  /**
   * Start node of FOOL language.
   */
  public static final class ProgNode extends Node {
    private final Node expression;

    /**
     * Create a Prog Node with a node inside.
     *
     * @param e node wrapped by Prog.
     */
    public ProgNode(Node e) {
      expression = e;
    }

    /**
     * Get the wrapped node.
     *
     * @return node.
     */
    public Node getExpression() {
      return expression;
    }

    /**
     * Execute the visit of a visitor on the runtime object class.
     * Practice of Visitor Pattern.
     *
     * @param visitor visitor to recall.
     * @param <S>     visitor type.
     * @return visitor object type return.
     */
    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * First node when there are declaration at start of program.
   */
  public static final class ProgLetInNode extends Node {
    private final List<Node> declarationList;
    private final Node expression;

    /**
     * Complex constructor with all declarations and expression node.
     *
     * @param d declaration list.
     * @param e expression.
     */
    public ProgLetInNode(List<Node> d, Node e) {
      declarationList = d;
      expression = e;
    }

    /**
     * Execute the visit of a visitor on the runtime object class.
     * Practice of Visitor Pattern.
     *
     * @param visitor visitor to recall.
     * @param <S>     visitor type.
     * @return visitor object type return.
     */
    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }

    /**
     * Return the declaration list.
     *
     * @return declaration list.
     */
    public List<Node> getDeclarationList() {
      return declarationList;
    }

    /**
     * Return the expression.
     *
     * @return expression.
     */
    public Node getExpression() {
      return expression;
    }
  }

  /**
   * Node to sum two nodes.
   */
  public static final class PlusNode extends Node {
    private final Node left;
    private final Node right;

    /**
     * Complex constructor with left and right nodes.
     *
     * @param l left node.
     * @param r right node.
     */
    public PlusNode(Node l, Node r) {
      left = l;
      right = r;
    }

    /**
     * Return the left node.
     *
     * @return left node.
     */
    public Node getLeft() {
      return left;
    }

    /**
     * Return the right node.
     *
     * @return right node.
     */
    public Node getRight() {
      return right;
    }

    /**
     * Execute the visit of a visitor on the runtime object class.
     *
     * @param visitor visitor to recall.
     * @param <S>     visitor type.
     * @return visitor object type return.
     */
    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * Multiply two expressions.
   */
  public static final class TimesNode extends Node {
    private final Node left;
    private final Node right;

    /**
     * Complex constructor with both nodes.
     *
     * @param l left node.
     * @param r right node.
     */
    public TimesNode(Node l, Node r) {
      left = l;
      right = r;
    }

    public Node getLeft() {
      return left;
    }

    public Node getRight() {
      return right;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * Node of an Int value.
   */
  public static final class IntValueNode extends Node {
    private final int val;

    /**
     * Create the node with his value.
     *
     * @param n int value.
     */
    public IntValueNode(int n) {
      val = n;
    }

    public int getVal() {
      return val;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * The int TYPE node. This is not a value.
   */
  public static final class IntTypeNode extends TypeNode {

    public IntTypeNode() {
      super();
    }

    /**
     * Visit the runtime type of the node.
     * This apply the visitor pattern.
     *
     * @param visitor visitor to recall.
     * @param <S>     return type.
     * @return node visited.
     */
    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * The equals comparison node.
   */
  public static final class EqualNode extends Node {
    private final Node left;
    private final Node right;

    /**
     * Create a simple equal node.
     *
     * @param left  left node.
     * @param right right node.
     */
    public EqualNode(Node left, Node right) {
      this.left = left;
      this.right = right;
    }

    public Node getLeft() {
      return left;
    }

    public Node getRight() {
      return right;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * Parameter node.
   */
  public static final class ParameterNode extends Node {
    private final String id;
    private final TypeNode type;

    /**
     * Simple constructor without line.
     * Generate a Parameter Node.
     *
     * @param i identifier.
     * @param t data type.
     */
    public ParameterNode(String i, TypeNode t) {
      id = i;
      type = t;
    }

    /**
     * Visit the runtime type of the node.
     * This apply the visitor pattern.
     *
     * @param visitor visitor to recall.
     * @param <S>     return type.
     * @return node visited.
     */
    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }

    /**
     * Return the identifier.
     *
     * @return identifier.
     */
    public String getId() {
      return id;
    }

    /**
     * Return the type.
     *
     * @return type.
     */
    public TypeNode getType() {
      return type;
    }
  }

  /**
   * Node for boolean value.
   */
  public static final class BoolValueNode extends Node {
    private final boolean val;

    /**
     * Create the node with his value.
     *
     * @param v boolean value.
     */
    public BoolValueNode(boolean v) {
      val = v;
    }

    public boolean getVal() {
      return val;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * The bool TYPE node. This is not a value.
   */
  public static final class BoolTypeNode extends TypeNode {
    /**
     * @param visitor visitor to recall.
     * @param <S>     return type.
     * @return node visited.
     */
    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * The selection construct node.
   * Always return a value, since this is a functional language.
   */
  public static final class IfNode extends Node {
    private final Node condition;
    private final Node then;
    private final Node els;

    /**
     * Complex constructor with all branches defined. Atm, we don't permit to
     * declare an if without a branch.
     *
     * @param c condition node.
     * @param t then node.
     * @param e else node.
     */
    public IfNode(Node c, Node t, Node e) {
      this.condition = c;
      this.then = t;
      this.els = e;
    }

    public Node getCondition() {
      return condition;
    }

    public Node getElse() {
      return els;
    }

    public Node getThen() {
      return then;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * Node to print in console some information.
   */
  public static final class PrintNode extends Node {
    private final Node print;

    /**
     * Create the node with some values to print.
     *
     * @param p value to print.
     */
    public PrintNode(Node p) {
      print = p;
    }

    public Node getPrint() {
      return print;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }
  }

  /**
   * Variable declaration node.
   */
  public static final class VarNode extends Node {
    private final String id;
    private final TypeNode type;
    private final Node exp;

    /**
     * Generate a simple Variable Declaration Node.
     *
     * @param i identifier.
     * @param t type.
     * @param v value. This is a functional language, there aren't
     *          declaration without assignment.
     */
    public VarNode(String i, TypeNode t, Node v) {
      id = i;
      type = t;
      exp = v;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }

    /**
     * Get the identifier.
     *
     * @return identifier.
     */
    public String getId() {
      return id;
    }

    /**
     * Get the type node.
     *
     * @return type.
     */
    public TypeNode getType() {
      return type;
    }

    /**
     * Get the exp node.
     *
     * @return exp.
     */
    public Node getExp() {
      return exp;
    }
  }

  /**
   * A Function declaration node. Contains all info to recall it at need.
   * TODO: Expand his features.
   */
  public static final class FunNode extends Node {
    private final String id;
    private final TypeNode returnType;
    private final List<Node> declarationList;
    private final Node exp;
    private final List<ParameterNode> parameterList;

    /**
     * Create a simple function declaration node.
     *
     * @param i  identifier.
     * @param rt return type.
     * @param pl parameter list.
     * @param dl declaration list.
     * @param e  expression inside the function scope.
     */
    public FunNode(String i, TypeNode rt, List<ParameterNode> pl, List<Node> dl,
                   Node e) {
      super();
      id = i;
      returnType = rt;
      parameterList = pl;
      declarationList = dl;
      exp = e;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }

    /**
     * Return the declaration list.
     *
     * @return declaration list.
     */
    public List<Node> getDeclarationList() {
      return declarationList;
    }

    /**
     * Return the return type.
     *
     * @return return type.
     */
    public TypeNode getReturnType() {
      return returnType;
    }

    /**
     * Return the identifier.
     *
     * @return identifier.
     */
    public String getId() {
      return id;
    }

    /**
     * Return the child expression.
     *
     * @return expression.
     */
    public Node getExp() {
      return exp;
    }

    /**
     * Return the parameter list.
     *
     * @return parameter list.
     */
    public List<ParameterNode> getParameterList() {
      return parameterList;
    }
  }

  /**
   * Create a variable use node.
   * Contain a Symbol Table Entry field to store the linked declaration to
   * get the value.
   */
  public static final class IdNode extends Node {
    private final String id;
    private SymTabEntry entry;

    /**
     * Create the identifier using node.
     *
     * @param i identifier.
     */
    public IdNode(String i, int line) {
      id = i;
      super.setLine(line);
    }

    /**
     * Get the entry that link declaration -> use.
     *
     * @return the Symbol Table Entry.
     */
    public SymTabEntry getEntry() {
      return entry;
    }

    /**
     * Set the entry that link declaration -> use.
     * Check that is at the right nesting level.
     *
     * @param entry the Symbol Table Entry.
     */
    public void setEntry(SymTabEntry entry) {
      this.entry = entry;
    }

    /**
     * Apply the visitor pattern to call visit on runtime type.
     *
     * @param visitor visitor to recall.
     * @param <S>     return type.
     * @return return value.
     */
    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }

    /**
     * Get the Identifier of the var.
     *
     * @return identifier.
     */
    public String getId() {
      return id;
    }
  }

  /**
   * A function call node. Contain a Symbol Table Entry to store the linked
   * declaration to call it at need.
   */
  public static final class CallNode extends Node {
    private final String id;
    private final List<Node> argumentList;
    private SymTabEntry entry;

    /**
     * Create a simple function call node.
     *
     * @param i identifier called.
     * @param p parameters list called.
     */
    public CallNode(String i, List<Node> p) {
      super();
      id = i;
      argumentList = p;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }

    /**
     * Return the identifier.
     *
     * @return identifier.
     */
    public String getId() {
      return id;
    }

    /**
     * Return the symbol table entry.
     *
     * @return entry.
     */
    public SymTabEntry getEntry() {
      return entry;
    }

    /**
     * Set the symbol table entry.
     *
     * @param entry entry.
     */
    public void setEntry(SymTabEntry entry) {
      this.entry = entry;
    }

    /**
     * Return the argument list.
     *
     * @return argument list.
     */
    public List<Node> getArgumentList() {
      return argumentList;
    }
  }

  /**
   * A Function type node in the form of:
   * arg type, arg type -> ret type.
   */
  public static final class ArrowTypeNode extends TypeNode {
    private final List<TypeNode> parameterTypesList;
    private final TypeNode returnType;

    public ArrowTypeNode(List<TypeNode> p, TypeNode r) {
      parameterTypesList = p;
      returnType = r;
    }

    @Override
    public <S, E extends Exception> S accept(AbsSynTreeVisitor<S, E> visitor)
        throws E {
      return visitor.visit(this);
    }

    public TypeNode getReturnType() {
      return returnType;
    }

    public List<TypeNode> getParameterTypesList() {
      return parameterTypesList;
    }
  }
}