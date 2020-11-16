package lcmc.semantic;

public class Usage {
    private final String name;
    private final int level;
    private final String type;

    public Usage(final String name, final int level, final String type) {
        this.name = name;
        this.level = level;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public String getType() {
        return type;
    }
}
