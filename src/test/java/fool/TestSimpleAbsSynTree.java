package fool;

import fool.compiler.ast.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.ast.CalcAbsSynTreeVisitor;
import fool.compiler.ast.PrintAbsSynTreeVisitor;
import fool.compiler.ast.lib.nodes.Node;
import fool.compiler.east.PrintEnrAbsSynTreeVisitor;
import fool.compiler.east.SymbolTableAbsSynTreeVisitor;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
      fail("Expected an FileNotFoundException");
    } catch (FileNotFoundException e) {
      assertEquals(String.format("Not found %s file.", fileName),
          e.getMessage());
    } catch (Exception e) {
      fail("Unexpected exception thrown.");
    }
  }

  @Test
  public void testTryBase() throws IOException {

    String fileName = "prova.fool";

    fool.FOOLLexer lexer = factory.getLexer(fileName);
    fool.FOOLParser parser = factory.getParser(lexer);
    ParseTree pt = parser.prog();

    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    assertEquals(0, lexer.lexicalErrors);
    assertEquals(0, parser.getNumberOfSyntaxErrors());

    AbsSynTreeGenSynTreeVisitor astGenVisitor = new AbsSynTreeGenSynTreeVisitor();
    Node ast = astGenVisitor.visit(pt);

    System.out.println("Visualizing AST...");

    new PrintAbsSynTreeVisitor().visit(ast);

    log("Calculating program value...");
    log("Program value is: " + new CalcAbsSynTreeVisitor(false).visit(ast));
  }

  @Test
  public void testTry2311() throws IOException {
    String fileName = "prova_23_11.fool"; // quicksort.fool.asm

    fool.FOOLLexer lexer = factory.getLexer(fileName);
    fool.FOOLParser parser = factory.getParser(lexer);
    ParseTree pt = parser.prog();

    log(
        "You had: " + lexer.lexicalErrors + " lexical errors and " +
            parser.getNumberOfSyntaxErrors() + " syntax errors.");
    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    assertEquals(0, lexer.lexicalErrors);
    assertEquals(0, parser.getNumberOfSyntaxErrors());

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
