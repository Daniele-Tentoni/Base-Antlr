package lcmc;

import lcmc.SVMParser;

public class ExecuteVM {

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

    public ExecuteVM(int[] code) {
        this.code = code;
        fp = sp;
    }

    public int getStackPointer() { return sp; }

    public int getTemporaryMemory() { return tm; }

    public int getHeapPointer() { return hp; }

    public int getReturnAddress() { return ra; }

    public int framePointer() { return fp; }

    public void cpu() {
        while (true) {
            int bytecode = code[ip++]; // fetch
            int v1;
            int v2;
            int address;
            switch (bytecode) {
                case SVMParser.PUSH:
                    push(code[ip++]);
                    break;
                case SVMParser.POP:
                    pop();
                    break;
                case SVMParser.ADD:
                    v1 = pop();
                    v2 = pop();
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
                    address = code[ip];
                    ip = address;
                    break;
                case SVMParser.BRANCHEQ:
                    // Qui invece non salto sempre.
                    v1 = pop();
                    v2 = pop();
                    ip = v2 == v1 ? code[ip] : ip + 1;
                    break;
                case SVMParser.BRANCHLESSEQ:
                    // Qui invece non salto sempre.
                    v1 = pop();
                    v2 = pop();
                    ip = v2 <= v1 ? code[ip] : ip + 1;
                    break;
                case SVMParser.LOADTM:
                    push(tm);
                    break;
                case SVMParser.STORETM:
                    tm = pop();
                    break;
                case SVMParser.LOADRA:
                    push(ra);
                    break;
                case SVMParser.STORERA:
                    ra = pop();
                    break;
                case SVMParser.LOADFP:
                    push(fp);
                    break;
                case SVMParser.STOREFP:
                    fp = pop();
                    break;
                case SVMParser.LOADHP:
                    push(hp);
                    break;
                case SVMParser.STOREHP:
                    hp = pop();
                    break;
                case SVMParser.COPYFP:
                    fp = sp;
                    break;
                case SVMParser.JS:
                    ra = pop();
                    ip = ra;
                    break;
                case SVMParser.LOADW:
                    v1 = pop();
                    v2 = memory[v1];
                    push(v2);
                    break;
                case SVMParser.STOREW:
                    v1 = pop();
                    v2 = pop();
                    memory[v1] = v2;
                    break;
                case SVMParser.PRINT:
                    System.out.printf("Print: %s%n", sp == MEM_SIZE ? "Empty stack." : memory[sp]);
                    break;
                case SVMParser.HALT:
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