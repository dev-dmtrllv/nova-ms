package net.server.task;

import net.server.coordinator.world.InviteCoordinator;
/**
 * @author Ronan
 */
public class InvitationTask implements Runnable {

    @Override
    public void run() {
        InviteCoordinator.runTimeoutSchedule();
    }
}
