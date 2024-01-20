package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.packet.InPacket;
import server.movement.LifeMovementFragment;
import tools.PacketCreator;
import tools.exceptions.EmptyMovementException;

import java.util.List;

public final class MovePetHandler extends AbstractMovementPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        int petId = p.readInt();
        p.readLong();
//        Point startPos = StreamUtil.readShortPoint(slea);
        List<LifeMovementFragment> res;

        try {
            res = parseMovement(p);
        } catch (EmptyMovementException e) {
            return;
        }
        Character player = c.getPlayer();
        byte slot = player.getPetIndex(petId);
        if (slot == -1) {
            return;
        }
        player.getPet(slot).updatePosition(res);
        player.getMap().broadcastMessage(player, PacketCreator.movePet(player.getId(), petId, slot, res), false);
    }
}
