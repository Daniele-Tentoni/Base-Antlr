package fool.compiler.ast.lib;

import fool.compiler.UnImplementedException;
import fool.compiler.ast.AbsSynTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test if each node have an abstract implementation.
 */
public class AbsSynTreeVisitorTest {

  private AbsSynTreeVisitor<Integer> visitor;

  @Before
  public void setup() {
    visitor = new AbsSynTreeVisitor<>();
  }

  @Test
  public void testUnimplementedExceptionsOnProgNode() {
    try {
      visitor.visit(new AbsSynTree.ProgNode(null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertEquals("ProgNode", e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnTimesNode() {
    try {
      visitor.visit(new AbsSynTree.TimesNode(null, null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnPlusNode() {
    try {
      visitor.visit(new AbsSynTree.PlusNode(null, null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIntNode() {
    try {
      visitor.visit(new AbsSynTree.IntValueNode(0));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnEqualNode() {
    try {
      visitor.visit(new AbsSynTree.EqualNode(null, null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnBoolNode() {
    try {
      visitor.visit(new AbsSynTree.BoolValueNode(true));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIfNode() {
    try {
      visitor.visit(new AbsSynTree.IfNode(null, null, null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnPrintNode() {
    try {
      visitor.visit(new AbsSynTree.PrintNode(null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnProgLetInNode() {
    try {
      visitor.visit(new AbsSynTree.ProgLetInNode(null, null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnBoolTypeNode() {
    try {
      visitor.visit(new AbsSynTree.BoolTypeNode());
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIntTypeNode() {
    try {
      visitor.visit(new AbsSynTree.IntTypeNode());
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnVarNode() {
    try {
      visitor.visit(new AbsSynTree.VarNode(null, null, null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnFunNode() {
    try {
      visitor.visit(new AbsSynTree.FunNode(null, null, null, null,
          null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIdNode() {
    try {
      visitor.visit(new AbsSynTree.IdNode(null, 0));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnCallNode() {
    try {
      visitor.visit(new AbsSynTree.CallNode(null, null));
      Assert.fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      Assert.assertNull(e.getMessage());
    }
  }
}
