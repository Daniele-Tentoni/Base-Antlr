package lcmc;

import org.antlr.v4.runtime.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import lcmc.ExampleLexer;
import lcmc.ExampleParser;

public class ExampleObjectsFactory implements ObjectFactory<ExampleLexer, ExampleParser> {
  private static ExampleObjectsFactory instance;

  private ExampleLexer lexer;
  private ExampleParser parser;
  private String openedFile;

  private ExampleObjectsFactory() {
  }

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
  public ExampleLexer getLexer(String fileName) throws IOException {
    if (lexer == null || !openedFile.equals(fileName)) {
      URL strings = getClass().getClassLoader().getResource(fileName);
      if (strings == null) {
        throw new FileNotFoundException(String.format("Not found %s file.", fileName));
      }
      CharStream chars = CharStreams.fromFileName(strings.getPath());
      lexer = new ExampleLexer(chars);
      openedFile = fileName;
    }

    return lexer;
  }

  @Override
  public ExampleParser getParser(ExampleLexer lexer) {
    Objects.requireNonNull(lexer);

    if (parser == null) {
      CommonTokenStream tokens = new CommonTokenStream(lexer);
      parser = new ExampleParser(tokens);
    }

    return parser;
  }
}