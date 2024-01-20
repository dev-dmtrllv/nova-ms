package net.server.channel.handlers;

import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.life.LifeFactory.BanishInfo;
import server.life.Monster;

public final class MobBanishPlayerHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        int mobid = p.readInt();     // mob banish handling detected thanks to MedicOP

        Character chr = c.getPlayer();
        Monster mob = chr.getMap().getMonsterById(mobid);

        if (mob != null) {
            BanishInfo banishInfo = mob.getBanish();
            if (banishInfo != null) {
                chr.changeMapBanish(banishInfo.getMap(), banishInfo.getPortal(), banishInfo.getMsg());
            }
        }
    }
}
