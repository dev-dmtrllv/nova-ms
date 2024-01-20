package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class PartySearchTask extends BaseTask implements Runnable {

    @Override
    public void run() {
        wserv.runPartySearchUpdateSchedule();
    }

    public PartySearchTask(World world) {
        super(world);
    }
}
