/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.SkillFactory;
import client.command.Command;

public class EmpowerMeCommand extends Command {
    {
        setDescription("Activate all useful buffs.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        final int[] array = {2311003, 2301004, 1301007, 4101004, 2001002, 1101007, 1005, 2301003, 5121009, 1111002, 4111001, 4111002, 4211003, 4211005, 1321000, 2321004, 3121002};
        for (int i : array) {
            SkillFactory.getSkill(i).getEffect(SkillFactory.getSkill(i).getMaxLevel()).applyTo(player);
        }
    }
}
