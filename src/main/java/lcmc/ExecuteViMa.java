package lcmc;

public class ExecuteViMa {

  public static final int CODE_SIZE = 10000;
  public static final int MEM_SIZE = 10000;

  private final int[] code;
  private final int[] memory = new int[MEM_SIZE];

  private int ip = 0;
  private int sp = MEM_SIZE; // punta al top dello stack
  private int tm;
  private int hp;
  private int fp;
  private int ra;

  public ExecuteViMa(int[] code) {
    this.code = code;
    fp = sp;
  }

  public int getStackPointer() {
    return sp;
  }

  public int getTemporaryMemory() {
    return tm;
  }

  public int getHeapPointer() {
    return hp;
  }

  public int getReturnAddress() {
    return ra;
  }

  public int framePointer() {
    return fp;
  }

  public void cpu() {
    while (true) {
      int bytecode = code[ip++]; // fetch
      int v1;
      int v2;
      int address;
      switch (bytecode) {
        case lcmc.SVMParser.PUSH:
          push(code[ip++]);
          break;
        case lcmc.SVMParser.POP:
          pop();
          break;
        case lcmc.SVMParser.ADD:
          v1 = pop();
          v2 = pop();
          push(v2 + v1);
          break;
        case lcmc.SVMParser.SUB:
          v1 = pop();
          v2 = pop();
          push(v2 - v1);
          break;
        case lcmc.SVMParser.MULT:
          v1 = pop();
          v2 = pop();
          push(v2 * v1);
          break;
        case lcmc.SVMParser.DIV:
          v1 = pop();
          v2 = pop();
          push(v2 / v1);
          break;
        case lcmc.SVMParser.BRANCH:
          // Salto incondizionatamente all'ip puntato contenuto in code.
          address = code[ip];
          ip = address;
          break;
        case lcmc.SVMParser.BRANCHEQ:
          // Qui invece non salto sempre.
          v1 = pop();
          v2 = pop();
          ip = v2 == v1 ? code[ip] : ip + 1;
          break;
        case lcmc.SVMParser.BRANCHLESSEQ:
          // Qui invece non salto sempre.
          v1 = pop();
          v2 = pop();
          ip = v2 <= v1 ? code[ip] : ip + 1;
          break;
        case lcmc.SVMParser.LOADTM:
          push(tm);
          break;
        case lcmc.SVMParser.STORETM:
          tm = pop();
          break;
        case lcmc.SVMParser.LOADRA:
          push(ra);
          break;
        case lcmc.SVMParser.STORERA:
          ra = pop();
          break;
        case lcmc.SVMParser.LOADFP:
          push(fp);
          break;
        case lcmc.SVMParser.STOREFP:
          fp = pop();
          break;
        case lcmc.SVMParser.LOADHP:
          push(hp);
          break;
        case lcmc.SVMParser.STOREHP:
          hp = pop();
          break;
        case lcmc.SVMParser.COPYFP:
          fp = sp;
          break;
        case lcmc.SVMParser.JS:
          ra = pop();
          ip = ra;
          break;
        case lcmc.SVMParser.LOADW:
          v1 = pop();
          v2 = memory[v1];
          push(v2);
          break;
        case lcmc.SVMParser.STOREW:
          v1 = pop();
          v2 = pop();
          memory[v1] = v2;
          break;
        case lcmc.SVMParser.PRINT:
          System.out.printf("Print: %s%n",
              sp == MEM_SIZE ? "Empty stack." : memory[sp]);
          break;
        case lcmc.SVMParser.HALT:
          // Leave cpu() method.
          return;
        default:
          System.out.println("Unknown token");
          break;
      }
    }
  }

  private int pop() {
    return memory[sp++];
  }

  private void push(int v) {
    memory[--sp] = v;
  }

}