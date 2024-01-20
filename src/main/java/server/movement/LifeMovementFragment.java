package server.movement;

import net.packet.OutPacket;

import java.awt.*;

public interface LifeMovementFragment {
    void serialize(OutPacket p);
    Point getPosition();
}
