package fool.compiler.ast;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalcASTVisitorTest {
    private CalcASTVisitor visitor;
    private CalcASTVisitor printVisitor;
    private CalcASTVisitor falsePrintVisitor;

    @Before
    public void setup() {
        visitor = new CalcASTVisitor();
        printVisitor = new CalcASTVisitor(true);
        falsePrintVisitor = new CalcASTVisitor(false);
    }

    @Test
    public void testVisitIntNode() {
        int v = 1;
        Node n = new AST.IntNode(1);
        Integer res = visitor.visit(n);
        assertEquals(v, res.intValue());
    }

    @Test
    public void testVisitBoolNode() {
        Node n = new AST.BoolNode(true);
        Integer res = visitor.visit(n);
        assertEquals(0, (int) res);
    }

    @Test
    public void testVisitPrintNode() {
        int v = 1;
        Node i = new AST.IntNode(v);
        Node n = new AST.PrintNode(i);
        var res = visitor.visit(n);
        assertEquals(v, res.intValue());
    }
}
