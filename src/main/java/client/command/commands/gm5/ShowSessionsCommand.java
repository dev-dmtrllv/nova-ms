package client.command.commands.gm5;

import client.Client;
import client.command.Command;
import net.server.coordinator.session.SessionCoordinator;
/**
 * @author Ronan
 */
public class ShowSessionsCommand extends Command {
    {
        setDescription("Show online sessions.");
    }

    @Override
    public void execute(Client c, String[] params) {
        SessionCoordinator.getInstance().printSessionTrace(c);
    }
}
