package lcmc;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import java.io.IOException;

/**
 * Contract for Object Factory. Implement this to use factory methods.
 */
public interface ObjectFactory<T extends Lexer, U extends Parser> {
    /**
     * Get the singleton lexer instance.
     *
     * @return lexer instance.
     */
    T getLexer(String fileName) throws IOException;

    /**
     * Get the singleton parser instance.
     *
     * @return parser instance.
     */
    U getParser(T lexer);
}
