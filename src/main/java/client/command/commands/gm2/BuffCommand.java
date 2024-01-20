/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm2;

import client.Character;
import client.Client;
import client.Skill;
import client.SkillFactory;
import client.command.Command;

public class BuffCommand extends Command {
    {
        setDescription("Activate a buff.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        if (params.length < 1) {
            player.yellowMessage("Syntax: !buff <buffid>");
            return;
        }
        int skillid = Integer.parseInt(params[0]);

        Skill skill = SkillFactory.getSkill(skillid);
        if (skill != null) {
            skill.getEffect(skill.getMaxLevel()).applyTo(player);
        }
    }
}
