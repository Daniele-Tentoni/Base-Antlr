package fool;

import fool.compiler.abssyntree.visitors.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.abssyntree.visitors.CalcAbsSynTreeVisitor;
import fool.compiler.abssyntree.visitors.PrintAbsSynTreeVisitor;
import fool.compiler.abssyntree.lib.nodes.Node;
import fool.compiler.enrabssyntree.visitors.PrintEnrAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.SymbolTableAbsSynTreeVisitor;
import fool.compiler.execptions.TypeException;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test if version 1 of language is supported.
 */
public class TestSimpleAbsSynTree {
  private FOOLObjectFactory factory;

  @Before
  public void setup() {
    factory = FOOLObjectFactory.getInstance();
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
  public void testTryBase() throws IOException, TypeException {

    final var fileName = "prova.fool";

    final var lexer = factory.getLexer(fileName);
    final var parser = factory.getParser(lexer);
    final var pt = parser.prog();

    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    Assert.assertEquals(0, lexer.lexicalErrors);
    Assert.assertEquals(0, parser.getNumberOfSyntaxErrors());

    final var astGenVisitor = new AbsSynTreeGenSynTreeVisitor();
    final var ast = astGenVisitor.visit(pt);

    log("Visualizing AST...");

    new PrintAbsSynTreeVisitor().visit(ast);

    log("Calculating program value...");
    log("Program value is: " + new CalcAbsSynTreeVisitor(false).visit(ast));
  }

  @Test
  public void testTry2311() throws IOException {
    final var fileName = "prova_23_11.fool"; // quicksort.fool.asm

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

    log(String.format("You had: %d symbol table errors.",
        symbolTableVisitor.getErrors()));
    log("Visualizing enriched AST");
    new PrintEnrAbsSynTreeVisitor().visit(ast);
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}
