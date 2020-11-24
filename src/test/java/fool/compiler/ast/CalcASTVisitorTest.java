package fool.compiler.ast;

import fool.compiler.ast.lib.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalcASTVisitorTest {
    private CalcASTVisitor visitor;

    @Before
    public void setup() {
        visitor = new CalcASTVisitor();
    }

    @Test
    public void testVisitIntNode() {
        int v = 1;
        Node n = new AST.IntValueNode(1);
        Integer res = visitor.visit(n);
        assertEquals(v, res.intValue());
    }

    @Test
    public void testVisitBoolNode() {
        Node n = new AST.BoolValueNode(true);
        Integer res = visitor.visit(n);
        assertEquals(0, (int) res);
    }

    @Test
    public void testVisitProgNode() {
        Node n = new AST.BoolValueNode(true);
        Node p = new AST.ProgNode(n);
        var res = visitor.visit(p);
        assertEquals(0, (int) res);
    }

    @Test
    public void testVisitPrintNode() {
        int v = 1;
        Node i = new AST.IntValueNode(v);
        Node n = new AST.PrintNode(i);
        var res = visitor.visit(n);
        assertEquals(v, (int) res);
    }

    @Test
    public void testVisitPlusNode() {
        int v1 = 1;
        int v2 = 2;
        Node n1 = new AST.IntValueNode(v1);
        Node n2 = new AST.IntValueNode(v2);
        Node p = new AST.PlusNode(n1, n2);
        var res = visitor.visit(p);
        assertEquals(v1 + v2, (int) res);
    }

    @Test
    public void testVisitTimesNode() {
        int v1 = 1;
        int v2 = 2;
        Node n1 = new AST.IntValueNode(v1);
        Node n2 = new AST.IntValueNode(v2);
        Node t = new AST.TimesNode(n1, n2);
        var res = visitor.visit(t);
        assertEquals(v1 * v2, (int) res);
    }

    @Test
    public void testVisitEqualNode() {
        // Here aren't equals, so I expect 0.
        int v1 = 1;
        int v2 = 2;
        Node n1 = new AST.IntValueNode(v1);
        Node n2 = new AST.IntValueNode(v2);
        Node e1 = new AST.EqualNode(n1, n2);
        var res = visitor.visit(e1);
        assertEquals(1, (int) res);

        // Here are equals, so I expect 1.
        int v3 = 2;
        Node n3 = new AST.IntValueNode(v3);
        Node e2 = new AST.EqualNode(n2, n3);
        res = visitor.visit(e2);
        assertEquals(0, (int) res);
    }

    @Test
    public void testVisitIfNode() {
        // Test left branch for if.
        int v2 = 1;
        int v3 = 2;
        Node cond = new AST.BoolValueNode(true);
        Node t = new AST.IntValueNode(v2);
        Node e = new AST.IntValueNode(v3);
        Node i = new AST.IfNode(cond, t, e);
        var res = visitor.visit(i);
        assertEquals(v2, (int) res);

        // Test right branch for if.
        cond = new AST.BoolValueNode(false);
        i = new AST.IfNode(cond, t, e);
        res = visitor.visit(i);
        assertEquals(v3, (int) res);
    }
}
