package fool;
import fool.compiler.abssyntree.visitors.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.abssyntree.visitors.PrintAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.PrintEnrAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.SymbolTableAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.TypeCheckingAbsSynTreeVisitor;
import fool.compiler.execptions.IncompleteException;
import fool.compiler.execptions.TypeException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
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

    log("You had: " + lexer.lexicalErrors + " lexical errors and " +
        parser.getNumberOfSyntaxErrors() + " syntax errors.");
    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    assertEquals(0, lexer.lexicalErrors);
    assertEquals(0, parser.getNumberOfSyntaxErrors());

    final var astGenVisitor = new AbsSynTreeGenSynTreeVisitor(true);
    final var ast = astGenVisitor.visit(pt);

    log("Visualizing AST...");

    new PrintAbsSynTreeVisitor().visit(ast);

    log("Enriching AST.");

    final var symbolTableVisitor = new SymbolTableAbsSynTreeVisitor();
    symbolTableVisitor.visit(ast);

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
      log(
          "Could not determine main program expression type due to errors detected before type checking.");
    }
    log("You had " + typeChecker.getTypeErrors() + " type checking errors.\n");

    int frontEndErrors =
        lexer.lexicalErrors + parser.getNumberOfSyntaxErrors()
            + symbolTableVisitor.getErrors() + typeChecker.getTypeErrors();
    log("You had a total of " + frontEndErrors + " front-end errors.\n");
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}