package fool.compiler.ast;

import fool.FOOLBaseVisitor;
import fool.FOOLParser;
import fool.compiler.ast.lib.Node;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.LinkedList;
import java.util.List;

import static fool.compiler.Utils.extractCtxName;
import static fool.compiler.Utils.lowerFirstChar;

public class ASTGenerationSTVisitor extends FOOLBaseVisitor<Node> {

    public boolean print;
    private String indent;

    public ASTGenerationSTVisitor() {}

    public ASTGenerationSTVisitor(boolean debug) { print = debug; }

    private void printVarAndProdName(ParserRuleContext ctx) {
        String prefix = "";
        Class<?> ctxClass = ctx.getClass();
        Class<?> parentClass = ctxClass.getSuperclass();
        if (!parentClass.equals(ParserRuleContext.class)) {
            // parentClass is the var context (and not ctxClass itself)
            prefix = lowerFirstChar(extractCtxName(parentClass.getName())) + ": production #";
        }
        System.out.println(indent + prefix + lowerFirstChar(extractCtxName(ctxClass.getName())));
    }

    @Override
    public Node visit(ParseTree t) {
        String temp = indent;
        indent = (indent == null) ? "" : indent + "  ";
        Node result = super.visit(t);
        indent = temp;
        return result;
    }

    @Override
    public Node visitProg(FOOLParser.ProgContext c) {
        if (print) {
            printVarAndProdName(c);
        }
        return visit(c.progbody());
    }

    @Override
    public Node visitLetInProg(FOOLParser.LetInProgContext c) {
        if (print) {
            printVarAndProdName(c);
        }
        List<Node> declarationList = new LinkedList<>();
        c.dec().forEach(declaration -> declarationList.add(visit(declaration)));
        Node n = visit(c.exp());
        return new AST.ProgLetInNode(declarationList, n);
    }

    @Override
    public Node visitNoDecProg(FOOLParser.NoDecProgContext c) {
        if (print) {
            printVarAndProdName(c);
        }
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