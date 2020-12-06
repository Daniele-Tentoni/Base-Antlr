package lcmc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is a simple class that you can copy to make your own tests.
 * This is needed only to show you how to make things.
 */
public class ExampleTest {
  private ExampleObjectsFactory factory;

  /**
   * You can use this method to instance factories properly.
   */
  @Before
  public void setup() {
    factory = ExampleObjectsFactory.getInstance();
  }

  @Test
  public void testWithFileNotFound() {
    String fileName = "non.exist";
    try {
      factory.getLexer(fileName);
      Assert.fail("Expected an FileNotFoundException");
    } catch (FileNotFoundException e) {
      Assert.assertEquals(String.format("Not found %s file.", fileName),
          e.getMessage());
    } catch (Exception e) {
      Assert.fail("Unexpected exception thrown.");
    }
  }

  @Test
  public void testExamples() throws IOException {
    lcmc.ExampleLexer lexer = factory.getLexer("strings.txt");
    lcmc.ExampleParser parser = factory.getParser(lexer);
    parser.prog();

    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    Assert.assertEquals(0, lexer.lexicalErrors);
    Assert.assertEquals(0, parser.getNumberOfSyntaxErrors());
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}
