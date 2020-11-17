package fool;

import fool.compiler.ASTGenerationSTVisitor;
import fool.compiler.lib.Node;
import fool.compiler.visitors.CalcASTVisitor;
import fool.compiler.visitors.PrintASTVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestAST {
    FOOLObjectFactory factory;

    @Before
    public void setup() {
        factory = FOOLObjectFactory.getInstance();
    }

    @Test
    public void testSVM() throws IOException {

        String fileName = "prova.fool"; // quicksort.fool.asm

        fool.FOOLLexer lexer = factory.getLexer(fileName);
        fool.FOOLParser parser = factory.getParser(lexer);
        ParseTree pt = parser.prog();

        System.out.println("You had: " + lexer.lexicalErrors + " lexical errors and " + parser.getNumberOfSyntaxErrors() + " syntax errors.");
        log("Prog");
        log("Lexical errors: " + lexer.lexicalErrors);
        log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
        assertEquals(0, lexer.lexicalErrors);
        assertEquals(0, parser.getNumberOfSyntaxErrors());

        ASTGenerationSTVisitor astGenVisitor = new ASTGenerationSTVisitor();
        Node ast = astGenVisitor.visit(pt);

        System.out.println("Visualizing AST...");

        new PrintASTVisitor().visit(ast);

        System.out.println("Calculating program value...");
        System.out.println("Program value is: " + new CalcASTVisitor(false).visit(ast));

    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
