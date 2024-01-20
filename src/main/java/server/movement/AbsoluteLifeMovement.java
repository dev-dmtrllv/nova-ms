package server.movement;

import net.packet.OutPacket;

import java.awt.*;

public class AbsoluteLifeMovement extends AbstractLifeMovement {
    private Point pixelsPerSecond;
    private int fh;

    public AbsoluteLifeMovement(int type, Point position, int duration, int newstate) {
        super(type, position, duration, newstate);
    }

    public Point getPixelsPerSecond() {
        return pixelsPerSecond;
    }

    public void setPixelsPerSecond(Point wobble) {
        this.pixelsPerSecond = wobble;
    }

    public int getFh() {    // unk -> fh, thanks Spoon for pointing this out
        return fh;
    }

    public void setFh(int fh) {
        this.fh = fh;
    }

    @Override
    public void serialize(OutPacket p) {
        p.writeByte(getType());
        p.writePos(getPosition());
        p.writePos(pixelsPerSecond);
        p.writeShort(fh);
        p.writeByte(getNewstate());
        p.writeShort(getDuration());
    }
}
