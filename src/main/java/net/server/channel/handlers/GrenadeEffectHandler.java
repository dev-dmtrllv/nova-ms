package net.server.channel.handlers;

import client.Character;
import client.Client;
import constants.skills.Gunslinger;
import constants.skills.NightWalker;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.PacketCreator;

import java.awt.*;
/*
 * @author GabrielSin
 */
public class GrenadeEffectHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(GrenadeEffectHandler.class);

    @Override
    public void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();
        Point position = new Point(p.readInt(), p.readInt());
        int keyDown = p.readInt();
        int skillId = p.readInt();

        switch (skillId) {
            case NightWalker.POISON_BOMB:
            case Gunslinger.GRENADE:
                int skillLevel = chr.getSkillLevel(skillId);
                if (skillLevel > 0) {
                    chr.getMap().broadcastMessage(chr, PacketCreator.throwGrenade(chr.getId(), position, keyDown, skillId, skillLevel), position);
                }
                break;
            default:
                log.warn("The skill id: {} is not coded in {}", skillId, getClass().getSimpleName());
        }
    }

}
