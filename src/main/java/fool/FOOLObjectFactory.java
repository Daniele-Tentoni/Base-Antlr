package fool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import lcmc.ObjectFactory;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Provide factory methods for antlr objects for FOOL language.
 */
public final class FOOLObjectFactory implements
    ObjectFactory<fool.FOOLLexer, fool.FOOLParser> {

  private static FOOLObjectFactory instance;

  /**
   * Generate an empty factory. Used private for singleton pattern.
   */
  private FOOLObjectFactory() {
  }

  /**
   * Get the current singleton instance of the factory.
   *
   * @return singleton instance.
   */
  public static FOOLObjectFactory getInstance() {
    if (instance == null) {
      instance = new FOOLObjectFactory();
    }
    return instance;
  }

  /**
   * Get the singleton FOOLLexer instance.
   *
   * @param fileName that lexer have to read.
   * @return lexer singleton instance.
   * @throws IOException when file is not found.
   */
  @Override
  public fool.FOOLLexer getLexer(final String fileName) throws IOException {
    URL strings = getClass().getClassLoader().getResource(fileName);
    if (strings == null) {
      throw new FileNotFoundException(
          String.format("Not found %s file.", fileName));
    }
    CharStream chars = CharStreams.fromFileName(strings.getPath());
    return new fool.FOOLLexer(chars);
  }

  /**
   * Get the singleton FOOLParser instance.
   *
   * @param foolLexer lexer that produce tokens.
   * @return parser that create syntax trees.
   */
  @Override
  public fool.FOOLParser getParser(final fool.FOOLLexer foolLexer) {
    Objects.requireNonNull(foolLexer);

    CommonTokenStream tokens = new CommonTokenStream(foolLexer);
    return new fool.FOOLParser(tokens);
  }
}
