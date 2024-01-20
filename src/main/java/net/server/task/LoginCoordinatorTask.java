package net.server.task;

import net.server.coordinator.session.SessionCoordinator;
/**
 * @author Ronan
 */
public class LoginCoordinatorTask implements Runnable {

    @Override
    public void run() {
        SessionCoordinator.getInstance().clearExpiredHwidHistory();
    }
}
