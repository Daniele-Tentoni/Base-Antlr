package fool;

import fool.FOOLLexer;
import fool.FOOLParser;
import lcmc.ObjectFactory;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class FOOLObjectFactory implements ObjectFactory<FOOLLexer, FOOLParser> {

    private static FOOLObjectFactory instance;
    private fool.FOOLParser parser;
    private fool.FOOLLexer lexer;

    private FOOLObjectFactory() {}

    public static FOOLObjectFactory getInstance() {
        if (instance == null) {
            instance = new FOOLObjectFactory();
        }
        return instance;
    }

    @Override
    public fool.FOOLLexer getLexer(String fileName) throws IOException {
        if (lexer == null) {
            URL strings = getClass().getClassLoader().getResource(fileName);
            if (strings == null) {
                throw new FileNotFoundException(String.format("Not found %s file.", fileName));
            }
            CharStream chars = CharStreams.fromFileName(strings.getPath());
            lexer = new fool.FOOLLexer(chars);
        }

        return lexer;
    }

    @Override
    public fool.FOOLParser getParser(fool.FOOLLexer lexer) {
        Objects.requireNonNull(lexer);

        if (lexer.getClass() != fool.FOOLLexer.class)
            throw new ClassCastException("Required ExampleLexer class");

        if (parser == null) {
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new fool.FOOLParser(tokens);
        }

        return parser;
    }
}
