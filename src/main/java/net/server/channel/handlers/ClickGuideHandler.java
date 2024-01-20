
package net.server.channel.handlers;

import client.Client;
import client.Job;
import constants.id.NpcId;
import net.AbstractPacketHandler;
import net.packet.InPacket;
import scripting.npc.NPCScriptManager;
/**
 * @author kevintjuh93
 */
public class ClickGuideHandler extends AbstractPacketHandler {
    @Override
    public void handlePacket(InPacket p, Client c) {
        if (c.getPlayer().getJob().equals(Job.NOBLESSE)) {
            NPCScriptManager.getInstance().start(c, NpcId.MIMO, null);
        } else {
            NPCScriptManager.getInstance().start(c, NpcId.LILIN, null);
        }
    }

}
