package lcmc;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import lcmc.SVMLexer;
import lcmc.SVMParser;

public final class SVMObjectFactory
    implements ObjectFactory<SVMLexer, SVMParser> {
  private static SVMObjectFactory instance;
  private SVMParser parser;
  private SVMLexer lexer;
  private String openedFile;

  private SVMObjectFactory() {
  }

  public static SVMObjectFactory getInstance() {
    if (instance == null) {
      instance = new SVMObjectFactory();
    }
    return instance;
  }

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
