package fool.compiler.east;

import fool.compiler.ast.AbstractSyntaxTree;
import fool.compiler.ast.lib.ASTVisitor;
import fool.compiler.east.lib.SymbolTableEntry;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SymbolTableASTVisitor extends ASTVisitor<Void> {
  private int errors;
  private List<Map<String, SymbolTableEntry>> symbolTable;
  private int nestingLevel;

  SymbolTableASTVisitor() {
    errors = 0;
    symbolTable = new LinkedList<>();
    nestingLevel = 0;
  }

  SymbolTableASTVisitor(boolean debug) {
    super(debug);
  }

  @Override
  public Void visit(AbstractSyntaxTree.ProgNode n) {
    if (mustPrint())
      printNode(n);
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.TimesNode n) {
    if (mustPrint())
      printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.PlusNode n) {
    if (mustPrint())
      printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IntValueNode n) {
    if (mustPrint())
      printNode(n, String.valueOf(n.getVal()));
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.EqualNode n) {
    if (mustPrint())
      printNode(n);
    visit(n.getLeft());
    visit(n.getRight());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.BoolValueNode n) {
    if (mustPrint())
      printNode(n, String.valueOf(n.getVal()));
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IfNode n) {
    if (mustPrint())
      printNode(n);
    visit(n.getCondition());
    visit(n.getThen());
    visit(n.getElse());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.PrintNode n) {
    if (mustPrint())
      printNode(n);
    visit(n.getPrint());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.ProgLetInNode n) {
    if (mustPrint())
      printNode(n);
    Map<String, SymbolTableEntry> hashMap = new HashMap<>();
    symbolTable.add(hashMap);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.BoolTypeNode n) {
    return super.visit(n);
  }

  @Override
  public Void visit(AbstractSyntaxTree.IntTypeNode n) {
    return super.visit(n);
  }

  @Override
  public Void visit(AbstractSyntaxTree.VarNode n) {
    if (mustPrint())
      printNode(n, n.getId());
    visit(n.getExp());
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.FunNode n) {
    if (mustPrint())
      printNode(n, n.getId());
    Map<String, SymbolTableEntry> hashMap = new HashMap<>();
    symbolTable.add(hashMap);
    n.getDeclarationList().forEach(this::visit);
    visit(n.getExp());
    symbolTable.remove(nestingLevel--);
    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.IdNode n) {
    if (mustPrint())
      printNode(n, n.getId());
    SymbolTableEntry entry = lookUp(n.getId());
    if (entry == null) {
      System.out.println(
          "Var id " + n.getId() + " at line " + n.getLine() + " not declared.");
      errors++;
    } else {
      n.setEntry(entry);
    }

    return null;
  }

  @Override
  public Void visit(AbstractSyntaxTree.CallNode n) {
    if (mustPrint()) {
      printNode(n, n.getId());
    }
    SymbolTableEntry entry = lookUp(n.getId());
    if (entry == null) {
      System.out.println(
          "Fun id " + n.getId() + " at line " + n.getLine() + " not declared.");
      errors++;
    } else {
      n.setEntry(entry);
    }
    return null;
  }

  private SymbolTableEntry lookUp(String id) {
    int j = nestingLevel;
    SymbolTableEntry entry = null;
    while (j >= 0 && entry == null) {
      entry = symbolTable.get(j--).get(id);
    }
    return entry;
  }
}
