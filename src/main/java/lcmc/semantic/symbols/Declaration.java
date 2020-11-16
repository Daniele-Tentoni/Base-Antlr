package lcmc.semantic;

public class Declaration {
    private final String name;
    private final int level;

    public Declaration(final String name, final int level){
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }
}
