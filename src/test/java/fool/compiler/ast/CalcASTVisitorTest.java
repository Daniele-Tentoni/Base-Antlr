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

    @Test public void testVisitProgNode() {
        Node n = new AST.BoolNode(true);
        Node p = new AST.ProgNode(n);
        var res = visitor.visit(p);
        assertEquals(0, (int) res);
    }

    @Test
    public void testVisitPrintNode() {
        int v = 1;
        Node i = new AST.IntNode(v);
        Node n = new AST.PrintNode(i);
        var res = visitor.visit(n);
        assertEquals(v, (int) res);
    }

    @Test
    public void testVisitPlusNode() {
        int v1 = 1;
        int v2 = 2;
        Node n1 = new AST.IntNode(v1);
        Node n2 = new AST.IntNode(v2);
        Node p = new AST.PlusNode(n1, n2);
        var res = visitor.visit(p);
        assertEquals(v1 + v2, (int) res);
    }

    @Test
    public void testVisitTimesNode() {
        int v1 = 1;
        int v2 = 2;
        Node n1 = new AST.IntNode(v1);
        Node n2 = new AST.IntNode(v2);
        Node t = new AST.TimesNode(n1, n2);
        var res = visitor.visit(t);
        assertEquals(v1 * v2, (int) res);
    }

    @Test
    public void testVisitEqualNode() {
        // Here aren't equals, so I expect 0.
        int v1 = 1;
        int v2 = 2;
        Node n1 = new AST.IntNode(v1);
        Node n2 = new AST.IntNode(v2);
        Node e1 = new AST.EqualNode(n1, n2);
        var res = visitor.visit(e1);
        assertEquals(0, (int) res);

        // Here are equals, so I expect 1.
        int v3 = 2;
        Node n3 = new AST.IntNode(v3);
        Node e2 = new AST.EqualNode(n2, n3);
        res = visitor.visit(e2);
        assertEquals(1, (int) res);
    }

    @Test public void testVisitIfNode() {
        // Test left branch for if.
        int v2 = 1;
        int v3 = 2;
        Node cond = new AST.BoolNode(true);
        Node t = new AST.IntNode(v2);
        Node e = new AST.IntNode(v3);
        Node i = new AST.IfNode(cond, t, e);
        var res = visitor.visit(i);
        assertEquals(v2, (int) res);

        // Test right branch for if.
        cond = new AST.BoolNode(false);
        i = new AST.IfNode(cond, t, e);
        res = visitor.visit(i);
        assertEquals(v3, (int) res);
    }
}
