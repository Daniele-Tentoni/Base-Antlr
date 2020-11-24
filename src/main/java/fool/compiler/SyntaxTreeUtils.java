package fool.compiler;

/**
 * Some utils methods for the Syntax Trees generation.
 */
public final class SyntaxTreeUtils {
  private static final int NODE_NAME_LENGTH = 4;
  private static final int CONTEXT_NAME_LENGTH = 7;

  /**
   * String s is in the form compiler.AST$NameNode.
   *
   * @param s rich node name.
   * @return simple node name.
   */
  public static String extractNodeName(final String s) {
    //
    return s.substring(s.lastIndexOf('$') + 1, s.length() - NODE_NAME_LENGTH);
  }

  /**
   * String s is in the form compiler.FOOLParser$NameContext.
   * Used to extract the NameContext from Class name.
   *
   * @param s class name of the context.
   * @return name of the context.
   */
  public static String extractCtxName(final String s) {
    return s
        .substring(s.lastIndexOf('$') + 1, s.length() - CONTEXT_NAME_LENGTH);
  }

  /**
   * Lower the first char of any String.
   * Hello -> hello.
   * PLUS ULTRA -> pLUS ULTRA.
   *
   * @param s string to process.
   * @return string processed.
   */
  public static String lowerFirstChar(final String s) {
    return Character.toLowerCase(s.charAt(0)) + s.substring(1);
  }
}
