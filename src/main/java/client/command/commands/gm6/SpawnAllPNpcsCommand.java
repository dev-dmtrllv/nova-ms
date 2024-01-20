/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.PlayerNPC;

public class SpawnAllPNpcsCommand extends Command {
    {
        setDescription("Spawn player NPCs of all existing players.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        PlayerNPC.multicastSpawnPlayerNPC(player.getMapId(), player.getWorld());
    }
}
