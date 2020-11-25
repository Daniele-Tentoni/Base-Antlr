package fool.compiler.ast.lib;

import fool.compiler.ast.AbstractSyntaxTree;
import fool.compiler.UnImplementedException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractSyntaxTreeVisitorTest {

  private ASTVisitor<Integer> visitor;

  @Before
  public void setup() {
    visitor = new ASTVisitor<>();
  }

  @Test
  public void testUnimplementedExceptionsOnProgNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.ProgNode(null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertEquals("ProgNode", e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnTimesNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.TimesNode(null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnPlusNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.PlusNode(null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIntNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.IntValueNode(0));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnEqualNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.EqualNode(null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnBoolNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.BoolValueNode(true));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIfNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.IfNode(null, null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnPrintNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.PrintNode(null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnProgLetInNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.ProgLetInNode(null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnBoolTypeNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.BoolTypeNode());
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIntTypeNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.IntTypeNode());
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnVarNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.VarNode(null, null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnFunNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.FunNode(null, null, null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnIdNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.IdNode(null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }

  @Test
  public void testUnimplementedExceptionsOnCallNode() {
    try {
      visitor.visit(new AbstractSyntaxTree.CallNode(null, null));
      fail("Expected an FileNotFoundException");
    } catch (UnImplementedException e) {
      assertNull(e.getMessage());
    }
  }
}
