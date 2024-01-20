package server.maps;

public interface AnimatedMapObject extends MapObject {
    int getStance();
    void setStance(int stance);
    boolean isFacingLeft();
}
