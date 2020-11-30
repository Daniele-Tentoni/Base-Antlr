package fool;

import fool.compiler.abssyntree.visitors.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.abssyntree.visitors.PrintAbsSynTreeVisitor;
import fool.compiler.abssyntree.lib.nodes.Node;
import fool.compiler.enrabssyntree.visitors.PrintEnrAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.SymbolTableAbsSynTreeVisitor;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the use of functions.
 */
public class TestAdvAbsSynTree {
  private FOOLObjectFactory factory;

  @Before
  public void setup() {
    factory = FOOLObjectFactory.getInstance();
  }

  @Test
  public void testTry2311() throws IOException {
    final var fileName = "prova_23_11.fool"; // quicksort.fool.asm

    final var lexer = factory.getLexer(fileName);
    final var parser = factory.getParser(lexer);
    final var pt = parser.prog();

    log(String.format("You had: %d lexical errors and %d syntax errors.",
        lexer.lexicalErrors, parser.getNumberOfSyntaxErrors()));
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    Assert.assertEquals(0, lexer.lexicalErrors);
    Assert.assertEquals(0, parser.getNumberOfSyntaxErrors());

    AbsSynTreeGenSynTreeVisitor astGenVisitor =
        new AbsSynTreeGenSynTreeVisitor(true);
    Node ast = astGenVisitor.visit(pt);

    log("Visualizing AST...");

    new PrintAbsSynTreeVisitor().visit(ast);

    log("Enriching AST.");

    SymbolTableAbsSynTreeVisitor symbolTableVisitor = new SymbolTableAbsSynTreeVisitor();
    symbolTableVisitor.visit(ast);

    log("You had: " + symbolTableVisitor.getErrors() + " "
        + "symbol table errors.\n");
    log("Visualizing enriched AST");
    new PrintEnrAbsSynTreeVisitor().visit(ast);
  }

  private void log(String msg) {
    System.out.println(msg);
  }

}
