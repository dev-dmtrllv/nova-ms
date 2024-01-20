/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import server.life.LifeFactory;
import server.life.NPC;
import tools.PacketCreator;

public class NpcCommand extends Command {
    {
        setDescription("Spawn an NPC on your location.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !npc <npcid>");
            return;
        }
        NPC npc = LifeFactory.getNPC(Integer.parseInt(params[0]));
        if (npc != null) {
            npc.setPosition(player.getPosition());
            npc.setCy(player.getPosition().y);
            npc.setRx0(player.getPosition().x + 50);
            npc.setRx1(player.getPosition().x - 50);
            npc.setFh(player.getMap().getFootholds().findBelow(c.getPlayer().getPosition()).getId());
            player.getMap().addMapObject(npc);
            player.getMap().broadcastMessage(PacketCreator.spawnNPC(npc));
        }
    }
}
