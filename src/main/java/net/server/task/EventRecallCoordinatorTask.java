package net.server.task;

import net.server.coordinator.world.EventRecallCoordinator;
/**
 * @author Ronan
 */
public class EventRecallCoordinatorTask implements Runnable {

    @Override
    public void run() {
        EventRecallCoordinator.getInstance().manageEventInstances();
    }
}
