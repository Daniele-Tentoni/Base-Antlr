package lcmc;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import lcmc.SVMLexer;
import lcmc.SVMParser;

public class SVMObjectFactory implements ObjectFactory<SVMLexer, SVMParser> {
    private static SVMObjectFactory instance;
    private SVMParser parser;
    private SVMLexer lexer;

    private SVMObjectFactory() {}

    public static SVMObjectFactory getInstance() {
        if (instance == null) {
            instance = new SVMObjectFactory();
        }
        return instance;
    }

    @Override
    public SVMLexer getLexer(String fileName) throws IOException {
        if (lexer == null) {
            URL strings = getClass().getClassLoader().getResource(fileName);
            if (strings == null) {
                throw new FileNotFoundException(String.format("Not found %s file.", fileName));
            }
            CharStream chars = CharStreams.fromFileName(strings.getPath());
            lexer = new SVMLexer(chars);
        }

        return lexer;
    }

    @Override
    public SVMParser getParser(SVMLexer lexer) {
        Objects.requireNonNull(lexer);

        if (lexer.getClass() != SVMLexer.class)
            throw new ClassCastException("Required ExampleLexer class");

        if (parser == null) {
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new SVMParser(tokens);
        }

        return parser;
    }
}
