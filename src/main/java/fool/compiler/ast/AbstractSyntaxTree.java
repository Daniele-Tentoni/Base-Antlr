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

  public final static class ProgLetInNode extends Node {
    private final List<Node> declarationList;
    private final Node exp;

    public ProgLetInNode(List<Node> d, Node e) {
      declarationList = d;
      exp = e;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }

    public List<Node> getDeclarationList() {
      return declarationList;
    }

    public Node getExp() {
      return exp;
    }
  }

  public final static class PlusNode extends Node {
    private final Node left;
    private final Node right;

    public PlusNode(Node l, Node r) {
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

  public final static class IntTypeNode extends Node {

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

  public final static class BoolTypeNode extends Node {

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

  public final static class VarNode extends Node {
    private final String id;
    private final Node type;
    private final Node exp;

    public VarNode(String i, Node t, Node v) {
      id = i;
      type = t;
      exp = v;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }

    public String getId() {
      return id;
    }

    public Node getType() {
      return type;
    }

    public Node getExp() {
      return exp;
    }
  }

  public final static class FunNode extends Node {
    private final String id;
    private final Node retType;
    //List<ParNode> parlist;
    private final List<Node> declarationList;
    private final Node exp;

    public FunNode(String i, Node rt, /* List<ParNode> pl, */ List<Node> dl,
                   Node e) {
      id = i;
      retType = rt; /* parlist=pl; */
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

  public final static class IdNode extends Node {
    private final String id;
    private SymbolTableEntry entry;

    public IdNode(String i) {
      id = i;
    }

    public SymbolTableEntry getEntry() {
      return entry;
    }

    public void setEntry(SymbolTableEntry entry) {
      this.entry = entry;
    }

    @Override
    public <S> S accept(ASTVisitor<S> visitor) {
      return visitor.visit(this);
    }

    public String getId() {
      return id;
    }
  }

  public final static class CallNode extends Node {
    private final String id;
    private SymbolTableEntry entry;

    // List<Node> arglist;
    public CallNode(String i /*, List<Node> p */) {
      id = i; /* arglist = p; */
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
  }
}