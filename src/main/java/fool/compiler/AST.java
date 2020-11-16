package fool.compiler;

import fool.compiler.lib.ASTVisitor;
import fool.compiler.lib.Node;

public class AST {
    public static class ProgNode implements Node {
        private final Node exp;

        ProgNode(Node e) {
            exp = e;
        }

        public Node getExp() {
            return exp;
        }

        @Override
        public <S> S accept(ASTVisitor<S> visitor) {
            return visitor.visit(this);
        }
    }

    public static class PlusNode implements Node {
        private final Node left;
        private final Node right;

        PlusNode(Node l, Node r) {
            left = l;
            right = r;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public <S> S accept(ASTVisitor<S> visitor) {
            return visitor.visit(this);
        }
    }

    public static class TimesNode implements Node {
        private final Node left;
        private final Node right;

        TimesNode(Node l, Node r) {
            left = l;
            right = r;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public <S> S accept(ASTVisitor<S> visitor) {
            return visitor.visit(this);
        }
    }

    public static class IntNode implements Node {
        private final int val;

        IntNode(int n) {
            val = n;
        }

        public int getVal() {
            return val;
        }

        @Override
        public <S> S accept(ASTVisitor<S> visitor) {
            return visitor.visit(this);
        }
    }
}