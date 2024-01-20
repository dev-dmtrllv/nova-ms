/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.SkillFactory;
import client.command.Command;

public class UnHideCommand extends Command {
    {
        setDescription("Toggle Hide.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        SkillFactory.getSkill(9101004).getEffect(SkillFactory.getSkill(9101004).getMaxLevel()).applyTo(player);

    }
}
