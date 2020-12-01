package fool;

import fool.compiler.abssyntree.visitors.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.abssyntree.visitors.PrintAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.PrintEnrAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.SymbolTableAbsSynTreeVisitor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

/**
 * Test FOOL language with functions.
 */
public class TestEnrAbsSynTree {
  private FOOLObjectFactory factory;

  @Before
  public void setup() {
    factory = FOOLObjectFactory.getInstance();
  }

  @Test
  public void testTry2711() throws IOException {
    final var fileName = "prova_27_11.fool"; // quicksort.fool.asm

    final var lexer = factory.getLexer(fileName);
    final var parser = factory.getParser(lexer);
    final var pt = parser.prog();

    log(String.format("You had: %d lexical errors and %d syntax errors.",
        lexer.lexicalErrors, parser.getNumberOfSyntaxErrors()));
    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    Assert.assertEquals(0, lexer.lexicalErrors);
    Assert.assertEquals(0, parser.getNumberOfSyntaxErrors());

    final var astGenVisitor = new AbsSynTreeGenSynTreeVisitor(true);
    final var ast = astGenVisitor.visit(pt);

    log("Visualizing AST...");

    new PrintAbsSynTreeVisitor().visit(ast);

    log("Enriching AST.");

    final var symbolTableVisitor = new SymbolTableAbsSynTreeVisitor();
    symbolTableVisitor.visit(ast);

    log(String.format("You had: %d symbol table errors.\n",
        symbolTableVisitor.getErrors()));
    log("Visualizing enriched AST");
    new PrintEnrAbsSynTreeVisitor().visit(ast);
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}
