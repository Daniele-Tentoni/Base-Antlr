package fool;

import fool.FOOLLexer;
import fool.FOOLParser;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import lcmc.ObjectFactory;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public final class FOOLObjectFactory implements
    ObjectFactory<FOOLLexer, FOOLParser> {

  private static FOOLObjectFactory instance;
  private FOOLParser parser;
  private FOOLLexer lexer;
  private String openedFile;

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
  public FOOLLexer getLexer(final String fileName) throws IOException {
    if (lexer == null || !openedFile.equals(fileName)) {
      URL strings = getClass().getClassLoader().getResource(fileName);
      if (strings == null) {
        throw new FileNotFoundException(
            String.format("Not found %s file.", fileName));
      }
      CharStream chars = CharStreams.fromFileName(strings.getPath());
      lexer = new FOOLLexer(chars);
      openedFile = fileName;
    }

    return lexer;
  }

  /**
   * Get the singleton FOOLParser instance.
   *
   * @param foolLexer lexer that produce tokens.
   * @return parser that create syntax trees.
   */
  @Override
  public FOOLParser getParser(final FOOLLexer foolLexer) {
    Objects.requireNonNull(foolLexer);

    if (parser == null) {
      CommonTokenStream tokens = new CommonTokenStream(foolLexer);
      parser = new FOOLParser(tokens);
    }

    return parser;
  }
}
