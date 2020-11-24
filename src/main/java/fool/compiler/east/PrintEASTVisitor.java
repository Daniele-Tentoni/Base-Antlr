package fool.compiler.east;

import fool.compiler.ast.AST;
import fool.compiler.ast.lib.Node;
import fool.compiler.east.lib.SymbolTableEntry;
import fool.compiler.east.lib.EASTVisitor;

public class PrintEASTVisitor extends EASTVisitor<Void> {
    PrintEASTVisitor() {
        super(true);
    }

    @Override
    public Void visit(AST.ProgNode n) {
        printNode(n);
        visit(n.getExp());
        return null;
    }

    @Override
    public Void visit(AST.IntValueNode n) {
        printNode(n, String.valueOf(n.getVal()));
        return null;
    }

    @Override
    public Void visit(AST.PlusNode n) {
        printNode(n);
        visit(n.getLeft());
        visit(n.getRight());
        return null;
    }

    @Override
    public Void visit(AST.TimesNode n) {
        printNode(n);
        visit(n.getLeft());
        visit(n.getRight());
        return null;
    }

    @Override
    public Void visit(AST.EqualNode n) {
        printNode(n);
        visit(n.getLeft());
        visit(n.getRight());
        return null;
    }

    @Override
    public Void visit(AST.BoolValueNode n) {
        printNode(n, String.valueOf(n.getVal()));
        return null;
    }


    @Override
    public Void visit(AST.IfNode n) {
        printNode(n);
        visit(n.getCondition());
        visit(n.getThen());
        visit(n.getElse());
        return null;
    }

    @Override
    public Void visit(AST.PrintNode n) {
        printNode(n);
        visit(n.getPrint());
        return null;
    }

    //
    @Override
    public Void visit(AST.ProgLetInNode n) {
        printNode(n);
        for (Node dec : n.getDeclarationList()) visit(dec);
        visit(n.getExp());
        return null;
    }

    @Override
    public Void visit(AST.BoolTypeNode n) {
        printNode(n);
        return null;
    }

    @Override
    public Void visit(AST.IntTypeNode n) {
        printNode(n);
        return null;
    }

    @Override
    public Void visit(AST.VarNode n) {
        printNode(n, n.getId());
        visit(n.getType());
        visit(n.getExp());
        return null;
    }

    @Override
    public Void visit(AST.FunNode n) {
        printNode(n, n.getId());
        visit(n.getRetType());
        // for (ParNode par : n.parlist) visit(par);
        for (Node dec : n.getDeclarationList()) visit(dec);
        visit(n.getExp());
        return null;
    }

    @Override
    public Void visit(AST.IdNode n) {
        printNode(n, n.getId());
        return null;
    }

    @Override
    public Void visit(AST.CallNode n) {
        printNode(n, n.getId());
        // for (Node arg : n.arglist) visit(arg);
        return null;
    }

    @Override
    public Void visit(SymbolTableEntry entry) {
        super.printSTEntry("nestlev " + entry.getNestingLevel());
        return null;
    }
}
