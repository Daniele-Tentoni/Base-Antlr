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

public class TestAdvAbsSynTree {
  private FOOLObjectFactory factory;

  @Before
  public void setup() {
    factory = FOOLObjectFactory.getInstance();
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
