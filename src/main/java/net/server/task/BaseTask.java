package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public abstract class BaseTask implements Runnable {
    protected World wserv;

    @Override
    public void run() {}

    public BaseTask(World world) {
        wserv = world;
    }
}
