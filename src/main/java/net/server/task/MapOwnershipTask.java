package net.server.task;

import net.server.channel.Channel;
import net.server.world.World;
/**
 * @author Ronan
 */
public class MapOwnershipTask extends BaseTask implements Runnable {

    @Override
    public void run() {
        for (Channel ch : wserv.getChannels()) {
            ch.runCheckOwnedMapsSchedule();
        }
    }

    public MapOwnershipTask(World world) {
        super(world);
    }
}
