package net.server.channel.handlers;

import client.Character;
import client.Client;
import client.SkillMacro;
import client.autoban.AutobanFactory;
import net.AbstractPacketHandler;
import net.packet.InPacket;

public final class SkillMacroHandler extends AbstractPacketHandler {

    @Override
    public final void handlePacket(InPacket p, Client c) {
        Character chr = c.getPlayer();
        int num = p.readByte();
        if (num > 5) {
            return;
        }

        for (int i = 0; i < num; i++) {
            String name = p.readString();
            if (name.length() > 12) {
                AutobanFactory.PACKET_EDIT.alert(chr, "Invalid name length " + name + " (" + name.length() + ") for skill macro.");
                c.disconnect(false, false);
                break;
            }

            int shout = p.readByte();
            int skill1 = p.readInt();
            int skill2 = p.readInt();
            int skill3 = p.readInt();
            SkillMacro macro = new SkillMacro(skill1, skill2, skill3, name, shout, i);
            chr.updateMacros(i, macro);
        }
    }
}
