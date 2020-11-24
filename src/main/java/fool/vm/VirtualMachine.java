package fool.vm;

import fool.vm.memory.MemoryStack;
import lcmc.SVMParser;

public class VirtualMachine {
  private static final int CODE_SIZE = 10000;
  private static final int MEMORY_SIZE = 10000;

  private final int[] code;
  private final MemoryStack memory;

  // TODO: What is ra?
  private int instructionPointer = 0;
  private int temporaryPointer;
  private int framePointer;
  private int heapPointer;
  private int ra;
  private int stackPointer = MEMORY_SIZE;

  public VirtualMachine(int[] code) {
    this.code = code;
    memory = new MemoryStack(MEMORY_SIZE);
  }

  public int getStackPointer() {
    return stackPointer;
  }

  /**
   * This is the cpu prof method.
   */
  public void start() {
    while (true) {
      int bytecode = code[instructionPointer++];
      switch (bytecode) {
        case SVMParser.PUSH:
          push(code[instructionPointer++]);
          break;
        case SVMParser.POP:
          pop();
          break;
        case SVMParser.ADD:
          int v1 = pop();
          int v2 = pop();
          push(v2 + v1);
          break;
        case SVMParser.SUB:
          v1 = pop();
          v2 = pop();
          push(v2 - v1);
          break;
        case SVMParser.MULT:
          v1 = pop();
          v2 = pop();
          push(v2 * v1);
          break;
        case SVMParser.DIV:
          v1 = pop();
          v2 = pop();
          push(v2 / v1);
          break;
        case SVMParser.BRANCH:
          // Salto incondizionatamente all'ip puntato contenuto in code.
          instructionPointer = code[instructionPointer];
          break;
        case SVMParser.BRANCHEQ:
          // Qui invece non salto sempre.
          v1 = pop();
          v2 = pop();
          instructionPointer = v2 == v1 ? code[instructionPointer] : instructionPointer + 1;
          break;
        case SVMParser.BRANCHLESSEQ:
          // Qui invece non salto sempre.
          v1 = pop();
          v2 = pop();
          instructionPointer = v2 <= v1 ? code[instructionPointer] : instructionPointer + 1;
          break;
        case SVMParser.LOADTM:
          push(temporaryPointer);
          break;
        case SVMParser.STORETM:
          temporaryPointer = pop();
          break;
        case SVMParser.LOADRA:
          push(ra);
          break;
        case SVMParser.STORERA:
          ra = pop();
          break;
        case SVMParser.LOADFP:
          push(framePointer);
          break;
        case SVMParser.STOREFP:
          framePointer = pop();
          break;
        case SVMParser.LOADHP:
          push(heapPointer);
          break;
        case SVMParser.STOREHP:
          heapPointer = pop();
          break;
        case SVMParser.COPYFP:
          framePointer = stackPointer;
          break;
        case SVMParser.JS:
          ra = pop();
          instructionPointer = ra;
          break;
        case SVMParser.LOADW:
          v1 = pop();
          v2 = memory.get(v1);
          push(v2);
          break;
        case SVMParser.STOREW:
          v1 = pop();
          v2 = pop();
          memory.set(v1, v2);
          break;
        case SVMParser.PRINT:
          System.out.printf("Print: %s%n", stackPointer == MEMORY_SIZE ? "Empty stack." : memory.get(stackPointer));
          break;
        case SVMParser.HALT:
          // Leave cpu() method.
          return;
      }
    }
  }

  private int pop() {
    return memory.pop();
  }

  private void push(int v) {
    memory.push(v);
  }
}
