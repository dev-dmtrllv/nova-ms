package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class FishingTask extends BaseTask implements Runnable {

    @Override
    public void run() {
        wserv.runCheckFishingSchedule();
    }

    public FishingTask(World world) {
        super(world);
    }
}
