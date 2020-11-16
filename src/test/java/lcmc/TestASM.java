package lcmc;

import org.antlr.v4.runtime.*;

public class TestASM {
    public static void main(String[] args) throws Exception {

        String fileName = "prova.asm"; // quicksort.fool.asm

        CharStream charsASM = CharStreams.fromFileName(fileName);
        lcmc.SVMLexer lexerASM = new lcmc.SVMLexer(charsASM);
        CommonTokenStream tokensASM = new CommonTokenStream(lexerASM);
        lcmc.SVMParser parserASM = new lcmc.SVMParser(tokensASM);

        parserASM.assembly();

        System.out.println("You had: " + lexerASM.lexicalErrors + " lexical errors and " + parserASM.getNumberOfSyntaxErrors() + " syntax errors.");
        if (lexerASM.lexicalErrors > 0 || parserASM.getNumberOfSyntaxErrors() > 0) System.exit(1);

        System.out.println("Starting Virtual Machine...");
        ExecuteVM vm = new ExecuteVM(parserASM.code);
        vm.cpu();
    }
}