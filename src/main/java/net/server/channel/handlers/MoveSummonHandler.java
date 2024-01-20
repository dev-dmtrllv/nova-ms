package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.packet.InPacket;
import server.maps.Summon;
import tools.PacketCreator;
import tools.exceptions.EmptyMovementException;

import java.awt.*;
import java.util.Collection;

public final class MoveSummonHandler extends AbstractMovementPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        int oid = p.readInt();
        Point startPos = new Point(p.readShort(), p.readShort());
        Character player = c.getPlayer();
        Collection<Summon> summons = player.getSummonsValues();
        Summon summon = null;
        for (Summon sum : summons) {
            if (sum.getObjectId() == oid) {
                summon = sum;
                break;
            }
        }
        if (summon != null) {
            try {
                int movementDataStart = p.getPosition();
                updatePosition(p, summon, 0);
                long movementDataLength = p.getPosition() - movementDataStart; //how many bytes were read by updatePosition
                p.seek(movementDataStart);

                player.getMap().broadcastMessage(player, PacketCreator.moveSummon(player.getId(), oid, startPos, p, movementDataLength), summon.getPosition());
            } catch (EmptyMovementException e) {
            }
        }
    }
}
