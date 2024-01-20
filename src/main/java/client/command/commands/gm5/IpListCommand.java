package client.command.commands.gm5;

import client.Character;
import client.Client;
import client.command.Command;
import constants.game.GameConstants;
import net.server.Server;
import net.server.world.World;

import java.util.Collection;
/**
 * @author Mist
 * @author Blood (Tochi)
 * @author Ronan
 */
public class IpListCommand extends Command {
    {
        setDescription("Show IP of all players.");
    }

    @Override
    public void execute(Client c, String[] params) {
        String str = "Player-IP relation:";

        for (World w : Server.getInstance().getWorlds()) {
            Collection<Character> chars = w.getPlayerStorage().getAllCharacters();

            if (!chars.isEmpty()) {
                str += "\r\n" + GameConstants.WORLD_NAMES[w.getId()] + "\r\n";

                for (Character chr : chars) {
                    str += "  " + chr.getName() + " - " + chr.getClient().getRemoteAddress() + "\r\n";
                }
            }
        }

        c.getAbstractPlayerInteraction().npcTalk(22000, str);
    }

}
