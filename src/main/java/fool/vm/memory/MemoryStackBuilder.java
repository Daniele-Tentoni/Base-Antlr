package fool.vm.memory;
/**
 * Builder for a Memory Stack implementation.
 */
public class MemoryStackBuilder {

  private int maxSize = 100000;

  /**
   * Add a fixed maxSize to the MemoryStack.
   * Default is 100000.
   *
   * @param maxSize to set.
   * @return Builder with size.
   */
  public MemoryStackBuilder withMaxSize(int maxSize) {
    this.maxSize = maxSize;
    return this;
  }

  /**
   * Build the Memory Stack.
   *
   * @return Memory Stack build.
   */
  public MemoryStack build() {
    return new MemoryStack(maxSize);
  }
}
