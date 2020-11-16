package lcmc;

import java.io.IOException;

public class SVMObjectFactory implements ObjectFactory<lcmc.ExampleLexer, lcmc.ExampleParser> {
    @Override
    public lcmc.ExampleLexer getLexer(String fileName) throws IOException {
        return null;
    }

    @Override
    public lcmc.ExampleParser getParser(lcmc.ExampleLexer lexer) {
        return null;
    }
}
