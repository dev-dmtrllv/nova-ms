/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;

public class GiveMesosCommand extends Command {
    {
        setDescription("Give mesos to a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !givems [<playername>] <gainmeso>");
            return;
        }

        String recv_, value_;
        long mesos_ = 0;

        if (params.length == 2) {
            recv_ = params[0];
            value_ = params[1];
        } else {
            recv_ = c.getPlayer().getName();
            value_ = params[0];
        }

        try {
            mesos_ = Long.parseLong(value_);
            if (mesos_ > Integer.MAX_VALUE) {
                mesos_ = Integer.MAX_VALUE;
            } else if (mesos_ < Integer.MIN_VALUE) {
                mesos_ = Integer.MIN_VALUE;
            }
        } catch (NumberFormatException nfe) {
            if (value_.contentEquals("max")) {  // "max" descriptor suggestion thanks to Vcoc
                mesos_ = Integer.MAX_VALUE;
            } else if (value_.contentEquals("min")) {
                mesos_ = Integer.MIN_VALUE;
            }
        }

        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(recv_);
        if (victim != null) {
            victim.gainMeso((int) mesos_, true);
            player.message("MESO given.");
        } else {
            player.message("Player '" + recv_ + "' could not be found.");
        }
    }
}
