package lcmc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import lcmc.SVMLexer;
import lcmc.SVMParser;

import static org.junit.Assert.assertEquals;

public class TestASM {
    private SVMObjectFactory factory;

    @Before
    public void setup() {
        factory = SVMObjectFactory.getInstance();
    }

    @Test
    public void testSVM() throws IOException {

        String fileName = "prova.asm"; // quicksort.fool.asm

        SVMLexer lexer = factory.getLexer(fileName);
        SVMParser parser = factory.getParser(lexer);
        parser.assembly();

        System.out.println("You had: " + lexer.lexicalErrors + " lexical errors and " + parser.getNumberOfSyntaxErrors() + " syntax errors.");
        log("Prog");
        log("Lexical errors: " + lexer.lexicalErrors);
        log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
        assertEquals(0, lexer.lexicalErrors);
        assertEquals(0, parser.getNumberOfSyntaxErrors());

        System.out.println("Starting Virtual Machine...");
        ExecuteVM vm = new ExecuteVM(parser.code);
        vm.cpu();
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}