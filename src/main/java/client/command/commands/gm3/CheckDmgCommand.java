/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.BuffStat;
import client.Character;
import client.Client;
import client.command.Command;

public class CheckDmgCommand extends Command {
    {
        setDescription("Show stats and damage of a player.");
    }

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        Character victim = c.getWorldServer().getPlayerStorage().getCharacterByName(params[0]);
        if (victim != null) {
            int maxBase = victim.calculateMaxBaseDamage(victim.getTotalWatk());
            Integer watkBuff = victim.getBuffedValue(BuffStat.WATK);
            Integer matkBuff = victim.getBuffedValue(BuffStat.MATK);
            int blessing = victim.getSkillLevel(10000000 * player.getJobType() + 12);
            if (watkBuff == null) {
                watkBuff = 0;
            }
            if (matkBuff == null) {
                matkBuff = 0;
            }

            player.dropMessage(5, "Cur Str: " + victim.getTotalStr() + " Cur Dex: " + victim.getTotalDex() + " Cur Int: " + victim.getTotalInt() + " Cur Luk: " + victim.getTotalLuk());
            player.dropMessage(5, "Cur WATK: " + victim.getTotalWatk() + " Cur MATK: " + victim.getTotalMagic());
            player.dropMessage(5, "Cur WATK Buff: " + watkBuff + " Cur MATK Buff: " + matkBuff + " Cur Blessing Level: " + blessing);
            player.dropMessage(5, victim.getName() + "'s maximum base damage (before skills) is " + maxBase);
        } else {
            player.message("Player '" + params[0] + "' could not be found on this world.");
        }
    }
}
