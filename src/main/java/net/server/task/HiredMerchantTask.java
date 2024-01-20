package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class HiredMerchantTask extends BaseTask implements Runnable {

    @Override
    public void run() {
        wserv.runHiredMerchantSchedule();
    }

    public HiredMerchantTask(World world) {
        super(world);
    }
}
