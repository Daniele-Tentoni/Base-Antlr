package lcmc;

import org.antlr.v4.runtime.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ExampleObjectsFactory implements ObjectFactory<lcmc.ExampleLexer, lcmc.ExampleParser> {
    private static ExampleObjectsFactory instance;

    private lcmc.ExampleLexer lexer;
    private lcmc.ExampleParser parser;

    private ExampleObjectsFactory() { }

    /**
     * Get the singleton factory instance.
     *
     * @return factory instance.
     */
    public static ExampleObjectsFactory getInstance() {
        if (instance == null) {
            instance = new ExampleObjectsFactory();
        }

        return instance;
    }

    @Override
    public lcmc.ExampleLexer getLexer(String fileName) throws IOException {
        if (lexer == null) {
            URL strings = getClass().getClassLoader().getResource(fileName);
            if (strings == null) {
                throw new FileNotFoundException(String.format("Not found %s file.", fileName));
            }
            CharStream chars = CharStreams.fromFileName(strings.getPath());
            lexer = new lcmc.ExampleLexer(chars);
        }

        return lexer;
    }

    @Override
    public lcmc.ExampleParser getParser(lcmc.ExampleLexer lexer) {
        Objects.requireNonNull(lexer);

        if (lexer.getClass() != lcmc.ExampleLexer.class)
            throw new RuntimeException("Required ExampleLexer class");

        if (parser == null) {
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new lcmc.ExampleParser(tokens);
        }

        return parser;
    }
}