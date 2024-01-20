/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

// import client.Character;
import client.Client;
import client.command.Command;
import tools.PacketCreator;

public class MaxEnergyCommand extends Command {
    {
        setDescription("Set dojo energy to max value.");
    }

    @Override
    public void execute(Client c, String[] params) {
        // Character player = c.getPlayer();
        c.getPlayer().setDojoEnergy(10000);
        c.sendPacket(PacketCreator.getEnergy("energy", 10000));
    }
}
