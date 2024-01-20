package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class TimedMapObjectTask extends BaseTask implements Runnable {

    @Override
    public void run() {
        wserv.runTimedMapObjectSchedule();
    }

    public TimedMapObjectTask(World world) {
        super(world);
    }
}
