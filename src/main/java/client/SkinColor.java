package client;

public enum SkinColor {
    NORMAL(0),
    DARK(1),
    BLACK(2),
    PALE(3),
    BLUE(4),
    GREEN(5),
    WHITE(9),
    PINK(10);

    final int id;

    SkinColor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SkinColor getById(int id) {
        for (SkinColor l : SkinColor.values()) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }
}
