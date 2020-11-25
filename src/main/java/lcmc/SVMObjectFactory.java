package lcmc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import lcmc.SVMLexer;
import lcmc.SVMParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Provide factory methods for antlr objects for SVM language.
 */
public final class SVMObjectFactory
    implements ObjectFactory<SVMLexer, SVMParser> {
  private static SVMObjectFactory instance;
  private SVMParser parser;
  private SVMLexer lexer;
  private String openedFile;

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
  public SVMLexer getLexer(String fileName) throws IOException {
    if (lexer == null || !openedFile.equals(fileName)) {
      URL strings = getClass().getClassLoader().getResource(fileName);
      if (strings == null) {
        throw new FileNotFoundException(
            String.format("Not found %s file.", fileName));
      }
      CharStream chars = CharStreams.fromFileName(strings.getPath());
      lexer = new SVMLexer(chars);
      openedFile = fileName;
    }

    return lexer;
  }

  /**
   * Get the singleton SVMParser instance.
   *
   * @param svmLexer lexer that produce tokens.
   * @return parser that create syntax trees.
   */
  @Override
  public SVMParser getParser(SVMLexer svmLexer) {
    Objects.requireNonNull(svmLexer);

    if (parser == null) {
      CommonTokenStream tokens = new CommonTokenStream(svmLexer);
      parser = new SVMParser(tokens);
    }

    return parser;
  }
}
