package net.server.channel.handlers;

import client.BuffStat;
import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.maps.MapObject;
import server.maps.Summon;
import tools.PacketCreator;

public final class DamageSummonHandler extends AbstractPacketHandler {
    @Override
    public final void handlePacket(InPacket p, Client c) {
        int oid = p.readInt();
        p.skip(1);   // -1
        int damage = p.readInt();
        int monsterIdFrom = p.readInt();

        Character player = c.getPlayer();
        MapObject mmo = player.getMap().getMapObject(oid);

        if (mmo != null && mmo instanceof Summon) {
            Summon summon = (Summon) mmo;

            summon.addHP(-damage);
            if (summon.getHP() <= 0) {
                player.cancelEffectFromBuffStat(BuffStat.PUPPET);
            }
            player.getMap().broadcastMessage(player, PacketCreator.damageSummon(player.getId(), oid, damage, monsterIdFrom), summon.getPosition());
        }
    }
}
