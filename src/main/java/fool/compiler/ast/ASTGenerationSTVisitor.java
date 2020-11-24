package fool.compiler.ast;

import fool.FOOLBaseVisitor;
import fool.FOOLParser;
import fool.compiler.ast.lib.Node;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import static fool.compiler.Utils.extractCtxName;
import static fool.compiler.Utils.lowerFirstChar;

public class ASTGenerationSTVisitor extends FOOLBaseVisitor<Node> {

  private boolean print;
  private String indent;

  public ASTGenerationSTVisitor() {
  }

  public ASTGenerationSTVisitor(boolean debug) {
    print = debug;
  }

  public boolean mustPrint() {
    return print;
  }

  private void printVarAndProdName(final ParserRuleContext ctx) {
    String prefix = "";
    Class<?> ctxClass = ctx.getClass();
    Class<?> parentClass = ctxClass.getSuperclass();
    if (!parentClass.equals(ParserRuleContext.class)) {
      // parentClass is the var context (and not ctxClass itself)
      prefix = lowerFirstChar(extractCtxName(parentClass.getName())) +
          ": production #";
    }
    System.out.println(
        indent + prefix + lowerFirstChar(extractCtxName(ctxClass.getName())));
  }

  @Override
  public final Node visit(final ParseTree t) {
    String temp = indent;
    indent = (indent == null) ? "" : indent + "  ";
    Node result = super.visit(t);
    indent = temp;
    return result;
  }

  @Override
  public Node visitProg(final FOOLParser.ProgContext c) {
    if (print) {
      printVarAndProdName(c);
    }
    return visit(c.progbody());
  }

  @Override
  public Node visitLetInProg(final FOOLParser.LetInProgContext c) {
    if (print) {
      printVarAndProdName(c);
    }
    List<Node> declarationList = new LinkedList<>();
    c.dec().forEach(declaration -> declarationList.add(visit(declaration)));
    Node n = visit(c.exp());
    return new AbstractSyntaxTree.ProgLetInNode(declarationList, n);
  }

  @Override
  public Node visitNoDecProg(final FOOLParser.NoDecProgContext c) {
    if (print) {
      printVarAndProdName(c);
    }
    return new AbstractSyntaxTree.ProgNode(visit(c.exp()));
  }

  @Override
  public Node visitTimes(
      final FOOLParser.TimesContext c) {       //modified production tags
    System.out.println(indent + "exp: prod with TIMES");

    return new AbstractSyntaxTree.TimesNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  @Override
  public Node visitPlus(final FOOLParser.PlusContext c) {
    System.out.println(indent + "exp: prod with PLUS");

    return new AbstractSyntaxTree.PlusNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  @Override
  public Node visitPars(final FOOLParser.ParsContext c) {
    System.out.println(indent + "exp: prod with LPAR RPAR");

    return visit(c.exp());
  }

  @Override
  public Node visitInteger(final FOOLParser.IntegerContext c) {
    int v = Integer.parseInt(c.NUM().getText());
    boolean minus = c.MINUS() != null;
    int res = minus ? -v : v;
    System.out.println(
        indent + "exp: prod with " + (minus ? "MINUS " : "") + "NUM " + res);

    return new AbstractSyntaxTree.IntValueNode(res);
  }

  @Override
  public Node visitEq(final FOOLParser.EqContext c) {
    System.out.println(indent + "exp: prod with PLUS");

    return new AbstractSyntaxTree.EqualNode(visit(c.exp(0)), visit(c.exp(1)));
  }

  @Override
  public Node visitPrint(final FOOLParser.PrintContext c) {
    return new AbstractSyntaxTree.PrintNode(visit(c.exp()));
  }

  @Override
  public Node visitTrue(final FOOLParser.TrueContext c) {
    System.out.println(indent + "exp: prod with TRUE");

    return new AbstractSyntaxTree.BoolValueNode(true);
  }

  @Override
  public Node visitFalse(final FOOLParser.FalseContext c) {
    System.out.println(indent + "exp: prod with FALSE");

    return new AbstractSyntaxTree.BoolValueNode(false);
  }

  @Override
  public Node visitIf(final FOOLParser.IfContext c) {
    System.out.println(indent + "exp: prod with IF THEN ELSE");
    return new AbstractSyntaxTree.IfNode(
        visit(c.exp(0)),
        visit(c.exp(1)),
        visit(c.exp(2)));
  }

}
