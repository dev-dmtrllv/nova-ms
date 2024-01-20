
package net.server.channel.handlers;

import client.BuffStat;
import client.Character;
import client.Client;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import server.life.Monster;
import server.maps.MapObject;
import tools.PacketCreator;
import tools.Pair;

import java.util.Collections;
import java.util.List;
/**
 * @author Ronan
 */
public final class PlayerMapTransitionHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();
        chr.setMapTransitionComplete();

        int beaconid = chr.getBuffSource(BuffStat.HOMING_BEACON);
        if (beaconid != -1) {
            chr.cancelBuffStats(BuffStat.HOMING_BEACON);

            final List<Pair<BuffStat, Integer>> stat = Collections.singletonList(new Pair<>(BuffStat.HOMING_BEACON, 0));
            chr.sendPacket(PacketCreator.giveBuff(1, beaconid, stat));
        }

        if (!chr.isHidden()) {  // thanks Lame (Conrad) for noticing hidden characters controlling mobs
            for (MapObject mo : chr.getMap().getMonsters()) {    // thanks BHB, IxianMace, Jefe for noticing several issues regarding mob statuses (such as freeze)
                Monster m = (Monster) mo;
                if (m.getSpawnEffect() == 0 || m.getHp() < m.getMaxHp()) {     // avoid effect-spawning mobs
                    if (m.getController() == chr) {
                        c.sendPacket(PacketCreator.stopControllingMonster(m.getObjectId()));
                        m.sendDestroyData(c);
                        m.aggroRemoveController();
                    } else {
                        m.sendDestroyData(c);
                    }

                    m.sendSpawnData(c);
                    m.aggroSwitchController(chr, false);
                }
            }
        }
    }
}
