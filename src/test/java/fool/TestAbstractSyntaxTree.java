package fool;

import fool.compiler.ast.AbsSynTreeGenSynTreeVisitor;
import fool.compiler.ast.CalcASTVisitor;
import fool.compiler.ast.PrintASTVisitor;
import fool.compiler.ast.lib.Node;
import fool.FOOLLexer;
import fool.FOOLParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestAbstractSyntaxTree {
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
  public void testSVM() throws IOException {

    String fileName = "prova.fool"; // quicksort.fool.asm

    FOOLLexer lexer = factory.getLexer(fileName);
    FOOLParser parser = factory.getParser(lexer);
    ParseTree pt = parser.prog();

    System.out.println(
        "You had: " + lexer.lexicalErrors + " lexical errors and " +
            parser.getNumberOfSyntaxErrors() + " syntax errors.");
    log("Prog");
    log("Lexical errors: " + lexer.lexicalErrors);
    log("Syntax errors: " + parser.getNumberOfSyntaxErrors());
    assertEquals(0, lexer.lexicalErrors);
    assertEquals(0, parser.getNumberOfSyntaxErrors());

    AbsSynTreeGenSynTreeVisitor astGenVisitor = new AbsSynTreeGenSynTreeVisitor();
    Node ast = astGenVisitor.visit(pt);

    System.out.println("Visualizing AST...");

    new PrintASTVisitor().visit(ast);

    System.out.println("Calculating program value...");
    System.out
        .println("Program value is: " + new CalcASTVisitor(false).visit(ast));

  }

  @Test
  public void testTry2311() throws IOException {
    String fileName = "prova_23_11.fool"; // quicksort.fool.asm

    FOOLLexer lexer = factory.getLexer(fileName);
    FOOLParser parser = factory.getParser(lexer);
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

    new PrintASTVisitor().visit(ast);
  }

  private void log(String msg) {
    System.out.println(msg);
  }
}
