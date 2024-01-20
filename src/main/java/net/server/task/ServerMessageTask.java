package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class ServerMessageTask extends BaseTask implements Runnable {

    @Override
    public void run() {
        // It's purpose is for tracking whether the player client currently displays a boss HPBar and, if so,
        // temporarily disable the server message for that player.

        wserv.runDisabledServerMessagesSchedule();
    }

    public ServerMessageTask(World world) {
        super(world);
    }
}
