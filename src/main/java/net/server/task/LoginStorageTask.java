package net.server.task;

import net.server.coordinator.login.LoginBypassCoordinator;
import net.server.coordinator.session.SessionCoordinator;
/**
 * @author Ronan
 */
public class LoginStorageTask implements Runnable {

    @Override
    public void run() {
        SessionCoordinator.getInstance().runUpdateLoginHistory();
        LoginBypassCoordinator.getInstance().runUpdateLoginBypass();
    }
}
