package fool.compiler;

public final class Utils {
  public static String extractNodeName(String s) { // s is in the form compiler.AST$NameNode
    return s.substring(s.lastIndexOf('$') + 1, s.length() - 4);
  }

  /**
   * String s is in the form compiler.FOOLParser$NameContext.
   * Used to extract the NameContext from Class name.
   *
   * @param s class name of the context.
   * @return name of the context.
   */
  public static String extractCtxName(String s) {
    return s.substring(s.lastIndexOf('$') + 1, s.length() - 7);
  }

  /**
   * Lower the first char of any String.
   * Hello -> hello.
   * PLUS ULTRA -> pLUS ULTRA.
   *
   * @param s string to process.
   * @return string processed.
   */
  public static String lowerFirstChar(String s) {
    return Character.toLowerCase(s.charAt(0)) + s.substring(1);
  }
}
