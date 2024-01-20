package client;
/**
 * @author Ronan
 */
public interface AbstractCharacterListener {
    void onHpChanged(int oldHp);
    void onHpmpPoolUpdate();
    void onStatUpdate();
    void onAnnounceStatPoolUpdate();
}
