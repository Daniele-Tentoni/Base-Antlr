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

    public static class EqualNode implements Node {
        private final Node left;
        private final Node right;

        EqualNode(Node left, Node right) {
            this.left = left;
            this.right = right;
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

    public static class BoolNode implements Node {
        private final boolean val;

        BoolNode(boolean v) {
            val = v;
        }

        public boolean getVal() {
            return val;
        }

        @Override
        public <S> S accept(ASTVisitor<S> visitor) {
            return visitor.visit(this);
        }
    }

    public static class IfNode implements Node {
        private final Node condition;
        private final Node then;
        private final Node els;

        IfNode(Node c, Node t, Node e) {
            this.condition = c;
            this.then = t;
            this.els = e;
        }

        public Node getCondition() {
            return condition;
        }

        public Node getEls() {
            return els;
        }

        public Node getThen() {
            return then;
        }

        @Override
        public <S> S accept(ASTVisitor<S> visitor) {
            return visitor.visit(this);
        }
    }

    public static class PrintNode implements Node {
        private final Node print;

        PrintNode(Node p) {
            this.print = p;
        }

        public Node getPrint() {
            return print;
        }

        @Override
        public <S> S accept(ASTVisitor<S> visitor) {
            return visitor.visit(this);
        }
    }
}