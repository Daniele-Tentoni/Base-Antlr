package fool.compiler.east.lib;

import fool.compiler.UnImplementedException;
import fool.compiler.ast.CalcAbsSynTreeVisitor;
import fool.compiler.east.PrintEnrAbsSynTreeVisitor;
import org.junit.Assert;
import org.junit.Test;

public class TestSymTabEntry {
  @Test
  public void testNestingLevel() {
    int nl = 0;
    var entry = new SymTabEntry(0);
    var nestingLevel = entry.getNestingLevel();
    Assert.assertEquals(nl, nestingLevel);
  }

  @Test
  public void testCorrectAccept() {
    var entry = new SymTabEntry(0);
    var visitor = new PrintEnrAbsSynTreeVisitor();
    visitor.visitSymTabEntry(entry);
  }

  @Test
  public void testIncorrectAccept() {
    var entry = new SymTabEntry(0);
    var visitor = new CalcAbsSynTreeVisitor();
    try {
      visitor.visit(entry);
      Assert.fail("Expected an UnImplementedException");
    } catch (UnImplementedException e) {
      Assert.assertEquals("", e.getMessage());
    } catch (ClassCastException c) {
      Assert.assertEquals("Need a EnrAbsSynTreeVisitor class", c.getMessage());
    }
  }
}