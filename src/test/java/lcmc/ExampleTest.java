package lcmc;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ExampleTest {
    ExampleObjectsFactory factory;

    /**
     * You can use this method to instance factories properly.
     */
    @Before public void setup() {
        factory = ExampleObjectsFactory.getInstance();
    }

    @Test public void testExamples() throws IOException {
        lcmc.ExampleLexer lexer = factory.getLexer("strings.txt");
        lcmc.ExampleParser parser = factory.getParser(lexer);
        parser.prog();

        log("Prog");
        log("Lexical errors: " + lexer.lexicalErrors);
        log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
        assertEquals(0, lexer.lexicalErrors);
        assertEquals(0, parser.getNumberOfSyntaxErrors());
    }

    private void log(String msg) {
        System.out.println(msg);
    }
}
