package fool;

import fool.compiler.abssyntree.visitors.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.abssyntree.visitors.PrintAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.PrintEnrAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.SymbolTableAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.TypeCheckingAbsSynTreeVisitor;
import fool.compiler.execptions.IncompleteException;
import fool.compiler.execptions.TypeException;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the use of a Type Checking Visitor.
 */
public class TestTypeCheckingTree {
  private FOOLObjectFactory factory;

  @Before
  public void setup() {
    factory = FOOLObjectFactory.getInstance();
  }

  @Test
  public void testTry3011() throws IOException {
    final var fileName = "prova_30_11.fool"; // quicksort.fool.asm

    final var lexer = factory.getLexer(fileName);
    final var parser = factory.getParser(lexer);
    final ParseTree pt = parser.prog();

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

    Assert.assertEquals(0, symbolTableVisitor.getErrors());
    log("You had: " + symbolTableVisitor.getErrors() + " "
        + "symbol table errors.\n");
    log("Visualizing enriched AST");
    final var printer = new PrintEnrAbsSynTreeVisitor();
    printer.visit(ast);

    log("Checking Types.");
    final var typeChecker = new TypeCheckingAbsSynTreeVisitor();
    try {
      final var mainType = typeChecker.visit(ast);
      log("Type of main program expression is: ");
      printer.visit(mainType);
    } catch (TypeException e) {
      log("Type checking error in main program expression: " + e.getMessage());
    } catch (IncompleteException e) {
      log("Could not determine main program expression type"
          + " due to errors detected before type checking.");
    }
    log("You had " + typeChecker.getTypeErrors() + " type checking errors.\n");

    int frontEndErrors =
        lexer.lexicalErrors + parser.getNumberOfSyntaxErrors()
            + symbolTableVisitor.getErrors() + typeChecker.getTypeErrors();
    log("You had a total of " + frontEndErrors + " front-end errors.\n");
    Assert.assertEquals(0, frontEndErrors);
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}