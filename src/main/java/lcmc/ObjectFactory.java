package lcmc;

import java.io.IOException;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

/**
 * Contract for Object Factory. Implement this to use factory methods.
 *
 * @param <T> subclass for Lexer.
 * @param <U> subclass for Parser.
 */
public interface ObjectFactory<T extends Lexer, U extends Parser> {

  /**
   * Get the singleton lexer instance.
   *
   * @param fileName that lexer must process.
   * @return lexer instance.
   * @throws IOException when file is not found.
   */
  T getLexer(String fileName) throws IOException;

  /**
   * Get the singleton parser instance.
   *
   * @param lexer that parser must process.
   * @return parser instance.
   */
  U getParser(T lexer);
}
