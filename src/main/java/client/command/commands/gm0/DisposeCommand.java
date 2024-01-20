/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm0;

import client.Client;
import client.command.Command;
import scripting.npc.NPCScriptManager;
import scripting.quest.QuestScriptManager;
import tools.PacketCreator;

public class DisposeCommand extends Command {
    {
        setDescription("Dispose to fix NPC chat.");
    }

    @Override
    public void execute(Client c, String[] params) {
        NPCScriptManager.getInstance().dispose(c);
        QuestScriptManager.getInstance().dispose(c);
        c.sendPacket(PacketCreator.enableActions());
        c.removeClickedNPC();
        c.getPlayer().message("You've been disposed.");
    }
}
