package lcmc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import lcmc.ExampleLexer;
import lcmc.ExampleParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Provide factory methods for antlr objects for example language.
 */
public final class ExampleObjectsFactory
    implements ObjectFactory<ExampleLexer, ExampleParser> {
  private static ExampleObjectsFactory instance;

  private ExampleLexer lexer;
  private ExampleParser parser;
  private String openedFile;

  private ExampleObjectsFactory() {
  }

  /**
   * @return current singleton instance of the factory.
   */
  public static ExampleObjectsFactory getInstance() {
    if (instance == null) {
      instance = new ExampleObjectsFactory();
    }

    return instance;
  }

  @Override
  public ExampleLexer getLexer(String fileName) throws IOException {
    if (lexer == null || !openedFile.equals(fileName)) {
      URL strings = getClass().getClassLoader().getResource(fileName);
      if (strings == null) {
        throw new FileNotFoundException(
            String.format("Not found %s file.", fileName));
      }
      CharStream chars = CharStreams.fromFileName(strings.getPath());
      lexer = new ExampleLexer(chars);
      openedFile = fileName;
    }

    return lexer;
  }

  @Override
  public ExampleParser getParser(ExampleLexer exampleLexer) {
    Objects.requireNonNull(exampleLexer);

    if (parser == null) {
      CommonTokenStream tokens = new CommonTokenStream(exampleLexer);
      parser = new ExampleParser(tokens);
    }

    return parser;
  }
}