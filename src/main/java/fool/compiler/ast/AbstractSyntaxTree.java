package fool.compiler.ast;

import fool.compiler.ast.lib.AbsSynTreeVisitor;
import fool.compiler.ast.lib.Node;
import fool.compiler.east.lib.SymTabEntry;
import java.util.List;

/**
 * Collection of all nodes of the ABS of FOOL language.
 */
public class AbstractSyntaxTree {
  /**
   * Start node of FOOL language.
   */
  public static final class ProgNode extends Node {
    private final Node exp;

    /**
     * Create a Prog Node with a node inside.
     *
     * @param e node wrapped by Prog.
     */
    public ProgNode(Node e) {
      exp = e;
    }

    /**
     * Get the wrapped node.
     *
     * @return node.
     */
    public Node getExp() {
      return exp;
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * The int TYPE node. This is not a value.
   */
  public static final class IntTypeNode extends Node {

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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public static final class EqualNode extends Node {
    private final Node left;
    private final Node right;

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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * Parenthesis node.
   */
  public static final class ParameterNode extends Node {
    private final String id;
    private final Node type;

    /**
     * Generate a Parameter Node.
     *
     * @param i    identifier.
     * @param line declaration line.
     * @param t    data type.
     */
    public ParameterNode(String i, int line, Node t) {
      super(line);
      id = i;
      type = t;
    }

    /**
     * Simple constructor without line.
     * Generate a Parameter Node.
     *
     * @param i identifier.
     * @param t data type.
     */
    public ParameterNode(String i, Node t) {
      super();
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public Node getType() {
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * The bool TYPE node. This is not a value.
   */
  public static final class BoolTypeNode extends Node {

    public BoolTypeNode() {
      super();
    }

    /**
     * @param visitor visitor to recall.
     * @param <S>     return type.
     * @return node visited.
     */
    @Override
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public static final class IfNode extends Node {
    private final Node condition;
    private final Node then;
    private final Node els;

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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * Variable declaration node.
   */
  public static final class VarNode extends Node {
    private final String id;
    private final Node type;
    private final Node exp;

    /**
     * Generate a rich Variable Declaration Node.
     *
     * @param i identifier.
     * @param t type.
     * @param v value. This is a functional language, there aren't
     *          declaration without assignment.
     */
    public VarNode(String i, int line, Node t, Node v) {
      super(line);
      id = i;
      type = t;
      exp = v;
    }

    /**
     * Generate a simple Variable Declaration Node.
     *
     * @param i identifier.
     * @param t type.
     * @param v value. This is a functional language, there aren't
     *          declaration without assignment.
     */
    public VarNode(String i, Node t, Node v) {
      super();
      id = i;
      type = t;
      exp = v;
    }

    @Override
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public Node getType() {
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
   * A Function declaration node. Contains all infos to recall it at need.
   * TODO: Expand his features.
   */
  public static final class FunNode extends Node {
    private final String id;
    private final Node retType;
    private final List<Node> declarationList;
    private final Node exp;
    private final List<ParameterNode> parameterList;

    /**
     * Create a rich function declaration node.
     *
     * @param i  identifier.
     * @param rt return type.
     * @param pl parameter list.
     * @param dl declaration list.
     * @param e  expression inside the function scope.
     */
    public FunNode(String i, int line, Node rt, List<ParameterNode> pl,
                   List<Node> dl, Node e) {
      super(line);
      id = i;
      retType = rt;
      parameterList = pl;
      declarationList = dl;
      exp = e;
    }

    /**
     * Create a simple function declaration node.
     *
     * @param i  identifier.
     * @param rt return type.
     * @param pl parameter list.
     * @param dl declaration list.
     * @param e  expression inside the function scope.
     */
    public FunNode(String i, Node rt, List<ParameterNode> pl, List<Node> dl,
                   Node e) {
      super();
      id = i;
      retType = rt;
      parameterList = pl;
      declarationList = dl;
      exp = e;
    }

    @Override
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
    public Node getRetType() {
      return retType;
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
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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

    /**
     * Create a rich function call node.
     *
     * @param i    identifier called.
     * @param line declaration line.
     * @param p    parameters list called.
     */
    public CallNode(String i, int line, List<Node> p) {
      super(line);
      id = i;
      argumentList = p;
    }

    @Override
    public <S> S accept(AbsSynTreeVisitor<S> visitor) {
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
}