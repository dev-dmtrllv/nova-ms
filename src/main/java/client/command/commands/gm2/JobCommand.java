/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.Job;
import client.command.Command;

public class JobCommand extends Command {
    {
        setDescription("Change job of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length == 1) {
            int jobid = Integer.parseInt(params[0]);
            if (jobid < 0 || jobid >= 2200) {
                player.message("Jobid " + jobid + " is not available.");
                return;
            }

            player.changeJob(Job.getById(jobid));
            player.equipChanged();
        } else if (params.length == 2) {
            Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);

            if (victim != null) {
                int jobid = Integer.parseInt(params[1]);
                if (jobid < 0 || jobid >= 2200) {
                    player.message("Jobid " + jobid + " is not available.");
                    return;
                }

                victim.changeJob(Job.getById(jobid));
                player.equipChanged();
            } else {
                player.message("Player '" + params[0] + "' could not be found.");
            }
        } else {
            player.message("Syntax: !job <job id> <opt: IGN of another person>");
        }
    }
}
