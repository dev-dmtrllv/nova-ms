package net.server.task;

import client.Character;
import config.YamlConfig;
import net.server.PlayerStorage;
import net.server.world.World;
/**
 * @author Ronan
 */
public class CharacterAutosaverTask extends BaseTask {  // thanks Alex09 (Alex-0000) for noticing these runnable classes are tasks, "workers" runs them

    @Override
    public void run() {
        if (!YamlConfig.config.server.USE_AUTOSAVE) {
            return;
        }

        PlayerStorage ps = wserv.getPlayerStorage();
        for (Character chr : ps.getAllCharacters()) {
            if (chr != null && chr.isLoggedin()) {
                chr.saveCharToDB(false);
            }
        }
    }

    public CharacterAutosaverTask(World world) {
        super(world);
    }
}
