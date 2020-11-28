package lcmc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Provide factory methods for antlr objects for SVM language.
 */
public final class SVMObjectFactory
    implements ObjectFactory<lcmc.SVMLexer, lcmc.SVMParser> {
  private static SVMObjectFactory instance;

  private SVMObjectFactory() {
  }

  /**
   * @return current singleton instance of the factory.
   */
  public static SVMObjectFactory getInstance() {
    if (instance == null) {
      instance = new SVMObjectFactory();
    }
    return instance;
  }

  /**
   * Get the singleton SVMLexer instance.
   *
   * @param fileName that lexer have to read.
   * @return lexer singleton instance.
   * @throws IOException when file is not found.
   */
  @Override
  public lcmc.SVMLexer getLexer(String fileName) throws IOException {
    URL strings = getClass().getClassLoader().getResource(fileName);
    if (strings == null) {
      throw new FileNotFoundException(
          String.format("Not found %s file.", fileName));
    }
    CharStream chars = CharStreams.fromFileName(strings.getPath());
    return new lcmc.SVMLexer(chars);
  }

  /**
   * Get the singleton SVMParser instance.
   *
   * @param svmLexer lexer that produce tokens.
   * @return parser that create syntax trees.
   */
  @Override
  public lcmc.SVMParser getParser(lcmc.SVMLexer svmLexer) {
    Objects.requireNonNull(svmLexer);
    CommonTokenStream tokens = new CommonTokenStream(svmLexer);
    return new lcmc.SVMParser(tokens);
  }
}
