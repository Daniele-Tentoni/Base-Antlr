package fool;

import lcmc.ObjectFactory;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Provide factory methods for Antlr objects for FOOL language.
 */
public final class FOOLObjectFactory implements
    ObjectFactory<fool.FOOLLexer, fool.FOOLParser> {
  /**
   * This is the singleton instance of the factory.
   */
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
   * Create a new FOOLLexer instance.
   *
   * @param fileName that lexer have to read. Must be in the resource
   *                 directory of the project. Modify him after the lexer
   *                 generation affect him, because the stream reading happen
   *                 when you call methods on FOOL Lexer instance.
   * @return lexer singleton instance.
   * @throws IOException when file is not found. Check the path to the
   *                     resource path of the project. If you call him from test classes a file
   *                     must be in the resource path of test, not of the source.
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
   * Calling this method make lexer trigger.
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
