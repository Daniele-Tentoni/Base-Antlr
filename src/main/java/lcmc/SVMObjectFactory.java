package lcmc;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class SVMObjectFactory implements ObjectFactory<lcmc.SVMLexer, lcmc.SVMParser> {
    private static SVMObjectFactory instance;
    private lcmc.SVMParser parser;
    private lcmc.SVMLexer lexer;

    private SVMObjectFactory() {}

    public static SVMObjectFactory getInstance() {
        if (instance == null) {
            instance = new SVMObjectFactory();
        }
        return instance;
    }

    @Override
    public lcmc.SVMLexer getLexer(String fileName) throws IOException {
        if (lexer == null) {
            URL strings = getClass().getClassLoader().getResource(fileName);
            if (strings == null) {
                throw new FileNotFoundException(String.format("Not found %s file.", fileName));
            }
            CharStream chars = CharStreams.fromFileName(strings.getPath());
            lexer = new lcmc.SVMLexer(chars);
        }

        return lexer;
    }

    @Override
    public lcmc.SVMParser getParser(lcmc.SVMLexer lexer) {
        Objects.requireNonNull(lexer);

        if (lexer.getClass() != lcmc.SVMLexer.class)
            throw new ClassCastException("Required ExampleLexer class");

        if (parser == null) {
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new lcmc.SVMParser(tokens);
        }

        return parser;
    }
}
