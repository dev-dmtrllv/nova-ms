/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import net.server.Server;
import net.server.channel.Channel;
import server.expeditions.Expedition;

import java.util.List;
import java.util.Map.Entry;

public class ExpedsCommand extends Command {
    {
        setDescription("Show all ongoing boss expeditions.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        for (Channel ch : Server.getInstance().getChannelsFromWorld(c.getWorld())) {
            List<Expedition> expeds = ch.getExpeditions();
            if (expeds.isEmpty()) {
                player.yellowMessage("No Expeditions in Channel " + ch.getId());
                continue;
            }
            player.yellowMessage("Expeditions in Channel " + ch.getId());
            int id = 0;
            for (Expedition exped : expeds) {
                id++;
                player.yellowMessage("> Expedition " + id);
                player.yellowMessage(">> Type: " + exped.getType().toString());
                player.yellowMessage(">> Status: " + (exped.isRegistering() ? "REGISTERING" : "UNDERWAY"));
                player.yellowMessage(">> Size: " + exped.getMembers().size());
                player.yellowMessage(">> Leader: " + exped.getLeader().getName());
                int memId = 2;
                for (Entry<Integer, String> e : exped.getMembers().entrySet()) {
                    if (exped.isLeader(e.getKey())) {
                        continue;
                    }
                    player.yellowMessage(">>> Member " + memId + ": " + e.getValue());
                    memId++;
                }
            }
        }
    }
}
