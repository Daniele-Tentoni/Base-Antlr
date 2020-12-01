package fool.vm.memory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Memory Stack class. This is a useless class.
 */
public class TestMemoryStack {

  private final int size = 10;
  private final int v1 = 1;
  private MemoryStack stack;

  @Before
  public void setup() {
    stack = new MemoryStack(size);
  }

  @Test(expected = StackOverflowError.class)
  public void testPushUpperBound() {
    // All is going well.
    for (int i = 0; i < size; i++) {
      stack.push(i);
    }
    // Crack.
    stack.push(v1);
  }

  @Test
  public void testGet() {
    stack.push(v1);
    int value = stack.get(0);
    Assert.assertEquals(value, v1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetOnUpperBounds() {
    // Crack.
    stack.get(v1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetOnLowerBounds() {
    // Crack.
    stack.get(-v1);
  }

  @Test
  public void testPop() {
    stack.push(v1);
    int value = stack.pop();
    Assert.assertEquals(1, value);
  }

  @Test(expected = StackOverflowError.class)
  public void testPopWithoutPush() {
    int v = stack.pop();
    Assert.assertEquals(v1, v);
  }

  @Test
  public void testSet() {
    stack.push(1);
    stack.push(3);
    int set = stack.set(0, 2);
    Assert.assertEquals(1, set);
    int firstPop = stack.pop();
    Assert.assertEquals(3, firstPop);
    int secondPop = stack.pop();
    Assert.assertEquals(2, secondPop);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testSetOnUpperBounds() {
    int set = stack.set(1, 2);
    Assert.assertEquals(set, 2);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testSetOnLowerBounds() {
    int set = stack.set(-1, 2);
    Assert.assertEquals(set, 2);
  }
}
