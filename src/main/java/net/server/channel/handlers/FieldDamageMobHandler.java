package net.server.channel.handlers;

import client.Character;
import client.Client;
import constants.game.GameConstants;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.life.Monster;
import server.life.MonsterInformationProvider;
import server.maps.MapleMap;
import tools.PacketCreator;

public class FieldDamageMobHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(FieldDamageMobHandler.class);

    @Override
    public final void handlePacket(InPacket p, Client c) {
        int mobOid = p.readInt();    // packet structure found thanks to Darter (Rajan)
        int dmg = p.readInt();

        Character chr = c.getPlayer();
        MapleMap map = chr.getMap();

        if (map.getEnvironment().isEmpty()) {   // no environment objects activated to actually hit the mob
            log.warn("Chr {} tried to use an obstacle on mapid {} to attack", c.getPlayer().getName(), map.getId());
            return;
        }

        Monster mob = map.getMonsterByOid(mobOid);
        if (mob != null) {
            if (dmg < 0 || dmg > GameConstants.MAX_FIELD_MOB_DAMAGE) {
                log.warn("Chr {} tried to use an obstacle on mapid {} to attack {} with damage {}", c.getPlayer().getName(),
                        map.getId(), MonsterInformationProvider.getInstance().getMobNameFromId(mob.getId()), dmg);
                return;
            }

            map.broadcastMessage(chr, PacketCreator.damageMonster(mobOid, dmg), true);
            map.damageMonster(chr, mob, dmg);
        }
    }
}
