package fool.compiler.ast;

import fool.FOOLBaseVisitor;
import fool.compiler.SyntaxTreeUtils;
import fool.compiler.ast.lib.nodes.Node;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

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
   * @return process must print debug info during execution.
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
      var pName = SyntaxTreeUtils.extractCtxName(parentClass.getName());
      prefix = SyntaxTreeUtils.lowerFirstChar(pName) + ": production #";
    }
    var cName = SyntaxTreeUtils.extractCtxName(ctxClass.getName());
    System.out.println(indent + prefix + SyntaxTreeUtils.lowerFirstChar(cName));
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
    List<Node> declarationList = new LinkedList<>();
    c.dec().forEach(declaration -> declarationList.add(visit(declaration)));
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

    var name = c.ID().getText();
    var line = c.VAR().getSymbol().getLine();
    var type = visit(c.type());
    var val = visit(c.exp());
    return new AbsSynTree.VarNode(name, line, type, val);
  }

  @Override
  public Node visitFundec(final fool.FOOLParser.FundecContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    final var id = c.ID(0).getText();
    final var line = c.ID(0).getSymbol().getLine();

    // Generate parameter list.
    List<Node> pList =
        IntStream.range(1, c.COLON().size()).mapToObj(i -> {
          final var pId = c.ID(i).getText();
          final var pLine = c.COLON(i).getSymbol().getLine();
          final var pType = visit(c.type(i));
          return new AbsSynTree.ParameterNode(pId,
              pLine,
              pType);
        }).collect(Collectors.toList());

    // Generate declaration list.
    var dList = c.dec().stream().map(this::visit).collect(Collectors.toList());

    var returnType = visit(c.type(0));
    var exp = visit(c.exp());
    return new AbsSynTree.FunNode(id, line, returnType,
        pList, dList, exp);
  }

  @Override
  public Node visitCall(final fool.FOOLParser.CallContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    var name = c.ID().getText();
    var line = c.ID().getSymbol().getLine();
    var params = new LinkedList<Node>();
    c.exp().forEach(elem -> params.add(visit(elem)));
    return new AbsSynTree.CallNode(name, line, params);
  }

  @Override
  public Node visitId(final fool.FOOLParser.IdContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    var name = c.ID().getText();
    var line = c.ID().getSymbol().getLine();
    return new AbsSynTree.IdNode(name, line);
  }

  @Override
  public Node visitTimes(final fool.FOOLParser.TimesContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbsSynTree.TimesNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  @Override
  public Node visitPlus(final fool.FOOLParser.PlusContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbsSynTree.PlusNode(visit(c.exp(0)), visit(c.exp(1)));
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
  public Node visitInteger(final fool.FOOLParser.IntegerContext c) {
    int v = Integer.parseInt(c.NUM().getText());
    boolean minus = c.MINUS() != null;
    int res = minus ? -v : v;
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    //System.out.println(
    //    indent + "exp: prod with " + (minus ? "MINUS " : "") + "NUM " + res);

    return new AbsSynTree.IntValueNode(res);
  }

  @Override
  public Node visitEq(final fool.FOOLParser.EqContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbsSynTree.EqualNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  @Override
  public Node visitPrint(final fool.FOOLParser.PrintContext c) {
    return new AbsSynTree.PrintNode(visit(c.exp()));
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
    return new AbsSynTree.IfNode(
        visit(c.exp(0)),
        visit(c.exp(1)),
        visit(c.exp(2)));
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

}
