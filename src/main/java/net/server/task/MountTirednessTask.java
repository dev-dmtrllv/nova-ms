package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class MountTirednessTask extends BaseTask {

    @Override
    public void run() {
        wserv.runMountSchedule();
    }

    public MountTirednessTask(World world) {
        super(world);
    }
}
