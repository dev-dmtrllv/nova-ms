package client.command.commands.gm6;

import client.Character;
import client.Client;
import client.command.Command;
import config.YamlConfig;

public class SupplyRateCouponCommand extends Command {
    {
        setDescription("Set availability of coupons in Cash Shop.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.dropMessage(5, "Syntax: !supplyratecoupon <yes|no>");
            return;
        }

        YamlConfig.config.server.USE_SUPPLY_RATE_COUPONS = params[0].compareToIgnoreCase("no") != 0;
        player.dropMessage(5, "Rate coupons are now " + (YamlConfig.config.server.USE_SUPPLY_RATE_COUPONS ? "enabled" : "disabled") + " for purchase at the Cash Shop.");
    }
}
