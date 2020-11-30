package fool.compiler.abssyntree.visitors;

import fool.FOOLBaseVisitor;
import fool.compiler.SyntaxTreeUtils;
import fool.compiler.abssyntree.AbsSynTree;
import fool.compiler.abssyntree.lib.nodes.Node;
import fool.compiler.abssyntree.lib.nodes.TypeNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Visit the Abstract Syntax Tree of FOOL language.
 */
public class AbsSynTreeGenSynTreeVisitor extends FOOLBaseVisitor<Node> {

  private final boolean print;
  private String indent;

  public AbsSynTreeGenSynTreeVisitor() {
    this(false);
  }

  public AbsSynTreeGenSynTreeVisitor(boolean debug) {
    print = debug;
    indent = "";
  }

  /**
   * Return if the process must print debug info during execution.
   *
   * @return true for print, false otherwise.
   */
  protected boolean mustPrint() {
    return print;
  }

  /**
   * Print a debug message in console from given Context.
   *
   * @param ctx the rule context to debug. Can be a Var or a Fun.
   */
  private void printVarAndProdName(final ParserRuleContext ctx) {
    String prefix = "";
    Class<?> ctxClass = ctx.getClass();
    Class<?> parentClass = ctxClass.getSuperclass();
    if (!parentClass.equals(ParserRuleContext.class)) {
      // parentClass is the var context (and not ctxClass itself)
      var pcName = SyntaxTreeUtils.extractCtxName(parentClass.getName());
      prefix = SyntaxTreeUtils.lowerFirstChar(pcName) + ": production #";
    }
    var clName = SyntaxTreeUtils.extractCtxName(ctxClass.getName());
    System.out.printf("%s%s%s", indent, prefix,
        SyntaxTreeUtils.lowerFirstChar(clName));
  }

  /**
   * Start here the visit of the Parse tree.
   *
   * @param t tree to visit.
   * @return abstract syntax tree node from visit.
   */
  @Override
  public final Node visit(final ParseTree t) {
    String temp = indent;
    indent = (indent == null) ? "" : indent + "  ";
    Node result = super.visit(t);
    indent = temp;
    return result;
  }

  /**
   * Start here the visit of a Prog Context, the root od a program.
   *
   * @param c context to visit.
   * @return abstract syntax tree node from visit.
   */
  @Override
  public Node visitProg(final fool.FOOLParser.ProgContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return visit(c.progbody());
  }

  @Override
  public Node visitLetInProg(final fool.FOOLParser.LetInProgContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    List<Node> declarationList = c.dec().stream().map(this::visit).collect(
        Collectors.toList());
    Node n = visit(c.exp());
    return new AbsSynTree.ProgLetInNode(declarationList, n);
  }

  @Override
  public Node visitNoDecProg(final fool.FOOLParser.NoDecProgContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return new AbsSynTree.ProgNode(visit(c.exp()));
  }

  @Override
  public Node visitTimes(final fool.FOOLParser.TimesContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    var left = visit(c.exp(0));
    var right = visit(c.exp(1));
    var node = new AbsSynTree.TimesNode(left, right);
    node.setLine(c.TIMES().getSymbol().getLine());
    return node;
  }

  @Override
  public Node visitPlus(final fool.FOOLParser.PlusContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    var left = visit(c.exp(0));
    var right = visit(c.exp(1));
    var node = new AbsSynTree.PlusNode(left, right);
    node.setLine(c.PLUS().getSymbol().getLine());
    return node;
  }

  @Override
  public Node visitEq(final fool.FOOLParser.EqContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    final var left = visit(c.exp(0));
    final var right = visit(c.exp(1));
    final var node = new AbsSynTree.EqualNode(left, right);
    node.setLine(c.EQ().getSymbol().getLine());
    return node;
  }

  /**
   * Visit a variable declaration context.
   *
   * @param c context to visit.
   * @return node created from visit.
   */
  @Override
  public Node visitVardec(final fool.FOOLParser.VardecContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    // Generate var node.
    final var name = c.ID().getText();
    final var type = visit(c.type());
    final var val = visit(c.exp());
    final var node = new AbsSynTree.VarNode(name, (TypeNode) type, val);

    // Add line to report errors.
    final var line = c.VAR().getSymbol().getLine();
    node.setLine(line);
    return node;
  }

  @Override
  public Node visitFundec(final fool.FOOLParser.FundecContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    final var id = c.ID(0).getText();

    List<AbsSynTree.ParameterNode> pl =
        IntStream.range(1, c.COLON().size()).mapToObj(i -> {
          // Generate parameter node.
          final var pId = c.ID(i).getText();
          final var pType = visit(c.type(i));
          final var pNode = new AbsSynTree.ParameterNode(pId,
              (TypeNode) pType);

          // Set his line to report errors.
          final var pLine = c.COLON(i).getSymbol().getLine();
          pNode.setLine(pLine);

          return pNode;
        }).collect(Collectors.toList());

    // Generate declaration list.
    final var dl =
        c.dec().stream().map(this::visit).collect(Collectors.toList());

    final var returnType = (TypeNode) visit(c.type(0));
    final var exp = visit(c.exp());
    final var node = new AbsSynTree.FunNode(id, returnType, pl, dl, exp);

    final var line = c.ID(0).getSymbol().getLine();
    node.setLine(line);
    return node;
  }

  @Override
  public Node visitIntType(final fool.FOOLParser.IntTypeContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return new AbsSynTree.IntTypeNode();
  }

  @Override
  public Node visitBoolType(final fool.FOOLParser.BoolTypeContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return new AbsSynTree.BoolTypeNode();
  }

  @Override
  public Node visitInteger(final fool.FOOLParser.IntegerContext c) {
    final int v = Integer.parseInt(c.NUM().getText());
    final boolean minus = c.MINUS() != null;
    final int res = minus ? -v : v;
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbsSynTree.IntValueNode(res);
  }

  @Override
  public Node visitTrue(final fool.FOOLParser.TrueContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbsSynTree.BoolValueNode(true);
  }

  @Override
  public Node visitFalse(final fool.FOOLParser.FalseContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbsSynTree.BoolValueNode(false);
  }

  @Override
  public Node visitIf(final fool.FOOLParser.IfContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    final var i = visit(c.exp(0));
    final var t = visit(c.exp(1));
    final var e = visit(c.exp(2));
    final var n = new AbsSynTree.IfNode(i, t, e);
    final var l = c.IF().getSymbol().getLine();
    n.setLine(l);
    return n;
  }

  @Override
  public Node visitPrint(final fool.FOOLParser.PrintContext c) {
    return new AbsSynTree.PrintNode(visit(c.exp()));
  }

  /**
   * Visit a Pars Context exploring his expression child.
   *
   * @param c pars context.
   * @return pars node.
   */
  @Override
  public Node visitPars(final fool.FOOLParser.ParsContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return visit(c.exp());
  }

  @Override
  public Node visitId(final fool.FOOLParser.IdContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    final var name = c.ID().getText();
    final var line = c.ID().getSymbol().getLine();
    final var node = new AbsSynTree.IdNode(name, line);
    node.setLine(line);
    return node;
  }

  @Override
  public Node visitCall(final fool.FOOLParser.CallContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    // Generate call node.
    final var name = c.ID().getText();
    final var params = c.exp().stream().map(this::visit)
        .collect(Collectors.toList());
    final var node = new AbsSynTree.CallNode(name, params);

    // Add line number to report errors.
    final var line = c.ID().getSymbol().getLine();
    node.setLine(line);
    return node;
  }

}
