package fool;
import fool.compiler.ast.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.ast.PrintAbsSynTreeVisitor;
import fool.compiler.ast.lib.Node;
import fool.compiler.east.PrintEnrAbsSynTreeVisitor;
import fool.compiler.east.SymbolTableAbsSynTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
public class TestEnrAbsSynTree {
  private FOOLObjectFactory factory;

  @Before
  public void setup() {
    factory = FOOLObjectFactory.getInstance();
  }

  @Test
  public void testTry2711() throws IOException {
    String fileName = "prova_27_11.fool"; // quicksort.fool.asm

    fool.FOOLLexer lexer = factory.getLexer(fileName);
    fool.FOOLParser parser = factory.getParser(lexer);
    ParseTree pt = parser.prog();

    System.out.println(
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

    System.out.println("Visualizing AST...");

    new PrintAbsSynTreeVisitor().visit(ast);

    System.out.println("Enriching AST.");

    SymbolTableAbsSynTreeVisitor symbolTableVisitor = new SymbolTableAbsSynTreeVisitor();
    symbolTableVisitor.visit(ast);

    System.out.println("You had: " + symbolTableVisitor.getErrors() + " "
        + "symbol table errors.\n");
    System.out.println("Visualizing enriched AST");
    new PrintEnrAbsSynTreeVisitor().visit(ast);
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}
