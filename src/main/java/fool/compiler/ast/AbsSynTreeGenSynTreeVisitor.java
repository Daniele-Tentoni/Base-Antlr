package fool.compiler.ast;

import fool.FOOLBaseVisitor;
import fool.FOOLParser;
import fool.compiler.SyntaxTreeUtils;
import fool.compiler.ast.lib.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.LinkedList;
import java.util.List;

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
  public Node visitProg(final FOOLParser.ProgContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return visit(c.progbody());
  }

  @Override
  public Node visitLetInProg(final FOOLParser.LetInProgContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    List<Node> declarationList = new LinkedList<>();
    c.dec().forEach(declaration -> declarationList.add(visit(declaration)));
    Node n = visit(c.exp());
    return new AbstractSyntaxTree.ProgLetInNode(declarationList, n);
  }

  @Override
  public Node visitNoDecProg(final FOOLParser.NoDecProgContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return new AbstractSyntaxTree.ProgNode(visit(c.exp()));
  }

  /**
   * Visit a variable declaration context.
   *
   * @param c context to visit.
   * @return node created from visit.
   */
  @Override
  public Node visitVardec(final FOOLParser.VardecContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    var name = c.ID().getText();
    var line = c.VAR().getSymbol().getLine();
    var type = visit(c.type());
    var val = visit(c.exp());
    return new AbstractSyntaxTree.VarNode(name, line, type, val);
  }

  @Override
  public Node visitFundec(final FOOLParser.FundecContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    var id = c.ID(0).getText();
    var line = c.ID(0).getSymbol().getLine();
    var parameterList = new LinkedList<AbstractSyntaxTree.ParameterNode>();
    if (c.COLON().size() > 0) {
      for (int i = 1; i < c.COLON().size(); i++) {
        var paramId = c.ID(i).getText();
        var paramLine = c.COLON(i).getSymbol().getLine();
        var type = visit(c.type(i));
        var param = new AbstractSyntaxTree.ParameterNode(paramId, paramLine,
            type);
        parameterList.add(param);
      }
    }
    var declarationList = new LinkedList<Node>();
    c.dec().forEach(dec -> declarationList.add(visit(dec)));
    var returnType = visit(c.type(0));
    var exp = visit(c.exp());
    return new AbstractSyntaxTree.FunNode(id, line, returnType,
        parameterList, declarationList, exp);
  }

  @Override
  public Node visitCall(final FOOLParser.CallContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    var name = c.ID().getText();
    var line = c.ID().getSymbol().getLine();
    var params = new LinkedList<Node>();
    c.exp().forEach(elem -> params.add(visit(elem)));
    return new AbstractSyntaxTree.CallNode(name, line, params);
  }

  @Override
  public Node visitId(final FOOLParser.IdContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    var name = c.ID().getText();
    var line = c.ID().getSymbol().getLine();
    return new AbstractSyntaxTree.IdNode(name, line);
  }

  @Override
  public Node visitTimes(final FOOLParser.TimesContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbstractSyntaxTree.TimesNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  @Override
  public Node visitPlus(final FOOLParser.PlusContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbstractSyntaxTree.PlusNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  /**
   * Visit a Pars Context exploring his expression child.
   *
   * @param c pars context.
   * @return pars node.
   */
  @Override
  public Node visitPars(final FOOLParser.ParsContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return visit(c.exp());
  }

  @Override
  public Node visitInteger(final FOOLParser.IntegerContext c) {
    int v = Integer.parseInt(c.NUM().getText());
    boolean minus = c.MINUS() != null;
    int res = minus ? -v : v;
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    //System.out.println(
    //    indent + "exp: prod with " + (minus ? "MINUS " : "") + "NUM " + res);

    return new AbstractSyntaxTree.IntValueNode(res);
  }

  @Override
  public Node visitEq(final FOOLParser.EqContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbstractSyntaxTree.EqualNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  @Override
  public Node visitPrint(final FOOLParser.PrintContext c) {
    return new AbstractSyntaxTree.PrintNode(visit(c.exp()));
  }

  @Override
  public Node visitTrue(final FOOLParser.TrueContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbstractSyntaxTree.BoolValueNode(true);
  }

  @Override
  public Node visitFalse(final FOOLParser.FalseContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }

    return new AbstractSyntaxTree.BoolValueNode(false);
  }

  @Override
  public Node visitIf(final FOOLParser.IfContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return new AbstractSyntaxTree.IfNode(
        visit(c.exp(0)),
        visit(c.exp(1)),
        visit(c.exp(2)));
  }

  @Override
  public Node visitIntType(final FOOLParser.IntTypeContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return new AbstractSyntaxTree.IntTypeNode();
  }

  @Override
  public Node visitBoolType(final FOOLParser.BoolTypeContext c) {
    if (mustPrint()) {
      printVarAndProdName(c);
    }
    return new AbstractSyntaxTree.BoolTypeNode();
  }

}
