package fool.compiler.ast;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ASTVisitorTest {

    private ASTVisitor<Integer> visitor;
    private final int size = 10;
    private final int v1 = 1;

    @Before
    public void setup() {
        visitor = new ASTVisitor<>();
    }

    @Test
    public void testUnimplementedExceptionsOnProgNode() {
        try {
            visitor.visit(new AST.ProgNode(null));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testUnimplementedExceptionsOnTimesNode() {
        try {
            visitor.visit(new AST.TimesNode(null, null));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testUnimplementedExceptionsOnPlusNode() {
        try {
            visitor.visit(new AST.PlusNode(null, null));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testUnimplementedExceptionsOnIntNode() {
        try {
            visitor.visit(new AST.IntNode(0));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testUnimplementedExceptionsOnEqualNode() {
        try {
            visitor.visit(new AST.EqualNode(null, null));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testUnimplementedExceptionsOnBoolNode() {
        try {
            visitor.visit(new AST.BoolNode(true));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testUnimplementedExceptionsOnIfNode() {
        try {
            visitor.visit(new AST.IfNode(null, null, null));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }

    @Test
    public void testUnimplementedExceptionsOnPrintNode() {
        try {
            visitor.visit(new AST.PrintNode(null));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e) {
            assertNull(e.getMessage());
        }
    }
}
