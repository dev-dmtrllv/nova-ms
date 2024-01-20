package net.server.channel.handlers;

import client.Client;
import constants.id.MobId;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.life.Monster;
import tools.PacketCreator;

public final class MonsterBombHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        int oid = p.readInt();
        Monster monster = c.getPlayer().getMap().getMonsterByOid(oid);
        if (!c.getPlayer().isAlive() || monster == null) {
            return;
        }
        if (monster.getId() == MobId.HIGH_DARKSTAR || monster.getId() == MobId.LOW_DARKSTAR) {
            monster.getMap().broadcastMessage(PacketCreator.killMonster(monster.getObjectId(), 4));
            c.getPlayer().getMap().removeMapObject(oid);
        }
    }
}
