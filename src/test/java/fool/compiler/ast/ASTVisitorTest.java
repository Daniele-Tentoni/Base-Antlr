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
    public void testUnimplementedExceptions() {
        String fileName = "non.exist";
        try {
            visitor.visit(new AST.ProgNode(null));
            fail("Expected an FileNotFoundException");
        } catch (UnImplementedException e){
            assertNull(e.getMessage());
        }
    }
}
