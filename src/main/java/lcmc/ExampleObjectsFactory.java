package lcmc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**
 * Provide factory methods for antlr objects for example language.
 */
public final class ExampleObjectsFactory
    implements ObjectFactory<lcmc.ExampleLexer, lcmc.ExampleParser> {
  private static ExampleObjectsFactory instance;

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
  public lcmc.ExampleLexer getLexer(String fileName) throws IOException {
    URL strings = getClass().getClassLoader().getResource(fileName);
    if (strings == null) {
      throw new FileNotFoundException(
          String.format("Not found %s file.", fileName));
    }
    CharStream chars = CharStreams.fromFileName(strings.getPath());
    return new lcmc.ExampleLexer(chars);
  }

  @Override
  public lcmc.ExampleParser getParser(lcmc.ExampleLexer exampleLexer) {
    Objects.requireNonNull(exampleLexer);
    CommonTokenStream tokens = new CommonTokenStream(exampleLexer);
    return new lcmc.ExampleParser(tokens);
  }
}