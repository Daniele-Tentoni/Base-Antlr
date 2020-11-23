package fool.compiler.ast;

import fool.FOOLBaseVisitor;
import fool.FOOLParser;
import fool.compiler.ast.lib.Node;
import org.antlr.v4.runtime.tree.ParseTree;

public class ASTGenerationSTVisitor extends FOOLBaseVisitor<Node> {

    private String indent;

    @Override
    public Node visit(ParseTree t) {             //visit now returns Node
        String temp = indent;
        indent = (indent == null) ? "" : indent + "  ";
        Node result = super.visit(t);
        indent = temp;
        return result;
    }

    @Override
    public Node visitProg(FOOLParser.ProgContext c) {
        System.out.println(indent + "prog");

        return new AST.ProgNode(visit(c.exp()));
    }

    @Override
    public Node visitTimes(FOOLParser.TimesContext c) {       //modified production tags
        System.out.println(indent + "exp: prod with TIMES");

        return new AST.TimesNode(visit(c.exp(0)), visit(c.exp(1)));
    }

    @Override
    public Node visitPlus(FOOLParser.PlusContext c) {
        System.out.println(indent + "exp: prod with PLUS");

        return new AST.PlusNode(visit(c.exp(0)), visit(c.exp(1)));
    }

    @Override
    public Node visitPars(FOOLParser.ParsContext c) {
        System.out.println(indent + "exp: prod with LPAR RPAR");

        return visit(c.exp());
    }

    @Override
    public Node visitInteger(FOOLParser.IntegerContext c) {
        int v = Integer.parseInt(c.NUM().getText());
        boolean minus = c.MINUS() != null;
        int res = minus ? -v : v;
        System.out.println(indent + "exp: prod with " + (minus ? "MINUS " : "") + "NUM " + res);

        return new AST.IntValueNode(res);
    }

    @Override
    public Node visitEq(FOOLParser.EqContext c) {
        System.out.println(indent + "exp: prod with PLUS");

        return new AST.EqualNode(visit(c.exp(0)), visit(c.exp(1)));
    }

    @Override
    public Node visitPrint(FOOLParser.PrintContext c) {
        return new AST.PrintNode(visit(c.exp()));
    }

    @Override
    public Node visitTrue(FOOLParser.TrueContext c) {
        System.out.println(indent + "exp: prod with TRUE");

        return new AST.BoolValueNode(true);
    }

    @Override
    public Node visitFalse(FOOLParser.FalseContext c) {
        System.out.println(indent + "exp: prod with FALSE");

        return new AST.BoolValueNode(false);
    }

    @Override
    public Node visitIf(FOOLParser.IfContext c) {
        System.out.println(indent + "exp: prod with IF THEN ELSE");
        return new AST.IfNode(
                visit(c.exp(0)),
                visit(c.exp(1)),
                visit(c.exp(2)));
    }

}