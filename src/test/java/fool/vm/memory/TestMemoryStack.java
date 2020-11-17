package fool.vm.memory;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestMemoryStack {

    @Test
    public void testGet() {
        MemoryStack stack = new MemoryStack(100);
        stack.push(1);
        int value = stack.get(0);
        assertEquals(value, 1);
    }

    @Test
    public void testPop() {
        MemoryStack stack = new MemoryStack(100);
        stack.push(1);
        int value = stack.pop();
        assertEquals(1, value);
    }

    @Test public void testSet() {
        MemoryStack stack = new MemoryStack(100);
        stack.push(1);
        stack.push(3);
        int set = stack.set(0, 2);
        assertEquals(1, set);
        int firstPop = stack.pop();
        assertEquals(3, firstPop);
        int secondPop = stack.pop();
        assertEquals(2, secondPop);
    }
}
