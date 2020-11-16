package fool.vm.memory;

import java.util.Stack;

public class MemoryStack {
    private final int maxSize;
    private final Stack<Integer> memory;

    public MemoryStack(int maxSize) {
        this.maxSize = maxSize;
        memory = new Stack<>();
    }

    /**
     * Return a defined element in the memory.
     *
     * @param index index of the resource.
     * @return required element.
     */
    public int get(int index) {
        if (index >= memory.size() || index < 0)
            throw new IndexOutOfBoundsException();
        return memory.get(index);
    }

    /**
     * Set a defined element in the memory.
     *
     * @param index index of the resource.
     * @param value given value.
     * @return -1 if operation fail, given value otherwise.
     */
    public int set(int index, Integer value) {
        if (index >= memory.size())
            throw new IndexOutOfBoundsException("Index out of range of memory");
        return memory.set(index, value);
    }

    /**
     * Retrieve the last element from memory and return it.
     *
     * @return value of last element.
     */
    public int pop() {
        if (memory.size() == 0)
            throw new StackOverflowError("Reached bottom memory limit in the memory stack.");
        return memory.pop();
    }

    /**
     * Insert a new element on top of memory.
     *
     * @param v value to insert.
     */
    public void push(int v) {
        if (memory.size() == maxSize)
            throw new StackOverflowError("Reached upper memory limit in the memory stack.");
        memory.push(v);
    }
}
