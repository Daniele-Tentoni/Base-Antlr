package fool.compiler.ast;

import fool.compiler.ast.lib.ASTVisitor;
import fool.compiler.ast.lib.Node;
import fool.compiler.east.lib.SymbolTableEntry;

import java.util.List;

/**
 * Collection of all nodes of the ABS of FOOL language.
 */
public class AbstractSyntaxTree {
  /**
   * Start node of FOOL language.
   */
  public final static class ProgNode extends Node {
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
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * First node when there are declaration at start of program.
   */
  public final static class ProgLetInNode extends Node {
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
    public <S> S accept(ASTVisitor<S> visitor) {
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
  public final static class PlusNode extends Node {
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
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public final static class TimesNode extends Node {
    private final Node left;
    private final Node right;

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
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public final static class IntValueNode extends Node {
    private final int val;

    public IntValueNode(int n) {
      val = n;
    }

    public int getVal() {
      return val;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * The int TYPE node. This is not a value.
   */
  public final static class IntTypeNode extends Node {

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
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public final static class EqualNode extends Node {
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
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public final static class BoolValueNode extends Node {
    private final boolean val;

    public BoolValueNode(boolean v) {
      val = v;
    }

    public boolean getVal() {
      return val;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * The bool TYPE node. This is not a value.
   */
  public final static class BoolTypeNode extends Node {

    public BoolTypeNode() {
      super();
    }

    /**
     * @param visitor visitor to recall.
     * @param <S>     return type.
     * @return node visited.
     */
    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public final static class IfNode extends Node {
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
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  public final static class PrintNode extends Node {
    private final Node print;

    public PrintNode(Node p) {
      this.print = p;
    }

    public Node getPrint() {
      return print;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }
  }

  /**
   * Variable declaration node.
   */
  public final static class VarNode extends Node {
    private final String id;
    private final Node type;
    private final Node exp;

    /**
     * Generate a Variable Declaration Node.
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

    public VarNode(String i, Node t, Node v) {
      super();
      id = i;
      type = t;
      exp = v;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
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

  public final static class FunNode extends Node {
    private final String id;
    private final Node retType;
    private final List<Node> declarationList;
    private final Node exp;
    //private final List<ParNode> parlist;@param pl parameter list.

    /**
     * Create a function declaration node.
     *
     * @param i  identifier.
     * @param rt return type.
     * @param dl declaration list.
     * @param e  expression inside the function scope.
     */
    public FunNode(String i, int line, Node rt, /*List<Par> pl,*/ List<Node> dl,
                   Node e) {
      super(line);
      id = i;
      retType = rt;
      //parlist = pl;
      declarationList = dl;
      exp = e;
    }

    public FunNode(String i, Node rt, /*List<Par> pl,*/ List<Node> dl,
                   Node e) {
      super();
      id = i;
      retType = rt;
      //parlist = pl;
      declarationList = dl;
      exp = e;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }

    public List<Node> getDeclarationList() {
      return declarationList;
    }

    public Node getRetType() {
      return retType;
    }

    public String getId() {
      return id;
    }

    public Node getExp() {
      return exp;
    }
  }

  /**
   * Create a variable use node.
   * Contain a Symbol Table Entry field to store the linked declaration to
   * get the value.
   */
  public final static class IdNode extends Node {
    private final String id;
    private SymbolTableEntry entry;

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
    public SymbolTableEntry getEntry() {
      return entry;
    }

    /**
     * Set the entry that link declaration -> use.
     * Check that is at the right nesting level.
     *
     * @param entry the Symbol Table Entry.
     */
    public void setEntry(SymbolTableEntry entry) {
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
    public <S> S accept(ASTVisitor<S> visitor) {
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

  public final static class CallNode extends Node {
    private final String id;
    //private final List<Node> arglist;
    private SymbolTableEntry entry;

    /**
     * Create a function call node.
     *
     * @param i identifier called.
     * @param p parameters list called.
     */
    public CallNode(String i, List<Node> p) {
      super();
      id = i;
      //arglist = p;
    }

    public CallNode(String i, int line, List<Node> p) {
      super(line);
      id = i;
      //arglist = p;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }

    public String getId() {
      return id;
    }

    public SymbolTableEntry getEntry() {
      return entry;
    }

    public void setEntry(SymbolTableEntry entry) {
      this.entry = entry;
    }

    //public List<Node> getArglist() {
    //  return arglist;
    //}
  }
}