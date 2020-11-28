package fool.compiler.ast;

import fool.compiler.ast.lib.nodes.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalcAbsSynTreeVisitorTest {
  private CalcAbsSynTreeVisitor visitor;

  @Before
  public void setup() {
    visitor = new CalcAbsSynTreeVisitor();
  }

  @Test
  public void testVisitIntNode() {
    int v = 1;
    Node n = new AbsSynTree.IntValueNode(1);
    Integer res = visitor.visit(n);
    assertEquals(v, res.intValue());
  }

  @Test
  public void testVisitBoolNode() {
    Node n = new AbsSynTree.BoolValueNode(true);
    Integer res = visitor.visit(n);
    assertEquals(0, (int) res);
  }

  @Test
  public void testVisitProgNode() {
    Node n = new AbsSynTree.BoolValueNode(true);
    Node p = new AbsSynTree.ProgNode(n);
    var res = visitor.visit(p);
    assertEquals(0, (int) res);
  }

  @Test
  public void testVisitPrintNode() {
    int v = 1;
    Node i = new AbsSynTree.IntValueNode(v);
    Node n = new AbsSynTree.PrintNode(i);
    var res = visitor.visit(n);
    assertEquals(v, (int) res);
  }

  @Test
  public void testVisitPlusNode() {
    int v1 = 1;
    int v2 = 2;
    Node n1 = new AbsSynTree.IntValueNode(v1);
    Node n2 = new AbsSynTree.IntValueNode(v2);
    Node p = new AbsSynTree.PlusNode(n1, n2);
    var res = visitor.visit(p);
    assertEquals(v1 + v2, (int) res);
  }

  @Test
  public void testVisitTimesNode() {
    int v1 = 1;
    int v2 = 2;
    Node n1 = new AbsSynTree.IntValueNode(v1);
    Node n2 = new AbsSynTree.IntValueNode(v2);
    Node t = new AbsSynTree.TimesNode(n1, n2);
    var res = visitor.visit(t);
    assertEquals(v1 * v2, (int) res);
  }

  @Test
  public void testVisitEqualNode() {
    // Here aren't equals, so I expect 0.
    int v1 = 1;
    int v2 = 2;
    Node n1 = new AbsSynTree.IntValueNode(v1);
    Node n2 = new AbsSynTree.IntValueNode(v2);
    Node e1 = new AbsSynTree.EqualNode(n1, n2);
    var res = visitor.visit(e1);
    assertEquals(1, (int) res);

    // Here are equals, so I expect 1.
    int v3 = 2;
    Node n3 = new AbsSynTree.IntValueNode(v3);
    Node e2 = new AbsSynTree.EqualNode(n2, n3);
    res = visitor.visit(e2);
    assertEquals(0, (int) res);
  }

  @Test
  public void testVisitIfNode() {
    // Test left branch for if.
    int v2 = 1;
    int v3 = 2;
    Node cond = new AbsSynTree.BoolValueNode(true);
    Node t = new AbsSynTree.IntValueNode(v2);
    Node e = new AbsSynTree.IntValueNode(v3);
    Node i = new AbsSynTree.IfNode(cond, t, e);
    var res = visitor.visit(i);
    assertEquals(v2, (int) res);

    // Test right branch for if.
    cond = new AbsSynTree.BoolValueNode(false);
    i = new AbsSynTree.IfNode(cond, t, e);
    res = visitor.visit(i);
    assertEquals(v3, (int) res);
  }
}
