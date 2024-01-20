package net.server.channel.handlers;

import client.Client;
import constants.skills.*;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.PacketCreator;

public final class SkillEffectHandler extends AbstractPacketHandler {
    private static final Logger log = LoggerFactory.getLogger(SkillEffectHandler.class);

    @Override
    public void handlePacket(InPacket p, Client c) {
        int skillId = p.readInt();
        int level = p.readByte();
        byte flags = p.readByte();
        int speed = p.readByte();
        byte aids = p.readByte();//Mmmk
        switch (skillId) {
            case FPMage.EXPLOSION:
            case FPArchMage.BIG_BANG:
            case ILArchMage.BIG_BANG:
            case Bishop.BIG_BANG:
            case Bowmaster.HURRICANE:
            case Marksman.PIERCING_ARROW:
            case ChiefBandit.CHAKRA:
            case Brawler.CORKSCREW_BLOW:
            case Gunslinger.GRENADE:
            case Corsair.RAPID_FIRE:
            case WindArcher.HURRICANE:
            case NightWalker.POISON_BOMB:
            case ThunderBreaker.CORKSCREW_BLOW:
            case Paladin.MONSTER_MAGNET:
            case DarkKnight.MONSTER_MAGNET:
            case Hero.MONSTER_MAGNET:
            case Evan.FIRE_BREATH:
            case Evan.ICE_BREATH:
                c.getPlayer().getMap().broadcastMessage(c.getPlayer(), PacketCreator.skillEffect(c.getPlayer(), skillId, level, flags, speed, aids), false);
                return;
            default:
                log.warn("Chr {} entered SkillEffectHandler without being handled using {}", c.getPlayer(), skillId);
                return;
        }
    }
}
