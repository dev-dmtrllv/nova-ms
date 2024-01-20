package server.maps;

import client.Client;

import java.awt.*;

public interface MapObject {
    int getObjectId();
    void setObjectId(int id);
    MapObjectType getType();
    Point getPosition();
    void setPosition(Point position);
    void sendSpawnData(Client client);
    void sendDestroyData(Client client);
    void nullifyPosition();
}
