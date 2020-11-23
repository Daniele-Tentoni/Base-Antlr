package fool.compiler.ast;

import fool.compiler.ast.lib.ASTVisitor;
import fool.compiler.ast.lib.Node;

public class AST {
    public final static class ProgNode extends Node {
        private final Node exp;

        public ProgNode(Node e) {
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

    public final static class PlusNode extends Node {
        private final Node left;
        private final Node right;

        public PlusNode(Node l, Node r) {
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

    public final static class TimesNode extends Node {
        private final Node left;
        private final Node right;

        public TimesNode(Node l, Node r) {
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

    public final static class IntValueNode extends Node {
        private final int val;

        public IntValueNode(int n) {
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

    public final static class EqualNode extends Node {
        private final Node left;
        private final Node right;

        public EqualNode(Node left, Node right) {
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

    public final static class BoolValueNode extends Node {
        private final boolean val;

        public BoolValueNode(boolean v) {
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

    public final static class IfNode extends Node {
        private final Node condition;
        private final Node then;
        private final Node els;

        public IfNode(Node c, Node t, Node e) {
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

    public final static class PrintNode extends Node {
        private final Node print;

        public PrintNode(Node p) {
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