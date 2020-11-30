package fool.compiler.enrabssyntree.lib;

import fool.compiler.execptions.TypeException;
import fool.compiler.execptions.UnImplementedException;
import fool.compiler.abssyntree.visitors.CalcAbsSynTreeVisitor;
import fool.compiler.enrabssyntree.visitors.PrintEnrAbsSynTreeVisitor;
import org.junit.Assert;
import org.junit.Test;

public class TestSymTabEntry {
  @Test
  public void testNestingLevel() {
    int nl = 0;
    var entry = new SymTabEntry(0, null);
    var nestingLevel = entry.getNestingLevel();
    Assert.assertEquals(nl, nestingLevel);
  }

  @Test
  public void testCorrectAccept() {
    var entry = new SymTabEntry(0, null);
    var visitor = new PrintEnrAbsSynTreeVisitor();
    visitor.visitSymTabEntry(entry);
  }

  @Test
  public void testIncorrectAccept() {
    var entry = new SymTabEntry(0, null);
    var visitor = new CalcAbsSynTreeVisitor();
    try {
      visitor.visit(entry);
      Assert.fail("Expected an UnImplementedException");
    } catch (UnImplementedException e) {
      Assert.assertEquals("", e.getMessage());
    } catch (ClassCastException c) {
      Assert.assertEquals("Need a EnrAbsSynTreeVisitor class", c.getMessage());
    } catch (TypeException e) {
      e.printStackTrace();
    }
  }
}