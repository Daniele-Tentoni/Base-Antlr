package lcmc;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestASM {
  private SVMObjectFactory factory;

  @Before
  public void setup() {
    factory = SVMObjectFactory.getInstance();
  }

  @Test
  public void testWithFileNotFound() {
    String fileName = "non.exist";
    try {
      factory.getLexer(fileName);
      fail("Expected an FileNotFoundException");
    } catch (FileNotFoundException e) {
      assertEquals(String.format("Not found %s file.", fileName),
          e.getMessage());
    } catch (Exception e) {
      fail("Unexpected exception thrown.");
    }
  }

  @Test
  public void testSVM() throws IOException {
    String fileName = "prova.asm";

    lcmc.SVMLexer lexer = factory.getLexer(fileName);
    lcmc.SVMParser parser = factory.getParser(lexer);
    parser.assembly();

    log("You had: " + lexer.lexicalErrors + " lexical errors and " +
        parser.getNumberOfSyntaxErrors() + " syntax errors.");
    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    assertEquals(0, lexer.lexicalErrors);
    assertEquals(0, parser.getNumberOfSyntaxErrors());

    log("Starting Virtual Machine...");
    ExecuteViMa vm = new ExecuteViMa(parser.code);
    vm.cpu();
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}