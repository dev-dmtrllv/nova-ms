/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import constants.game.GameConstants;
import constants.id.NpcId;
import tools.PacketCreator;

public class MusicCommand extends Command {
    {
        setDescription("Play a song.");
    }

    private static String getSongList() {
        String songList = "Song:\r\n";
        for (String s : GameConstants.GAME_SONGS) {
            songList += ("  " + s + "\r\n");
        }

        return songList;
    }

    @Override
    public void execute(Client c, String[] params) {

        Character player = c.getPlayer();
        if (params.length < 1) {
            String sendMsg = "";

            sendMsg += "Syntax: #r!music <song>#k\r\n\r\n";
            sendMsg += getSongList();

            c.sendPacket(PacketCreator.getNPCTalk(NpcId.BILLY, (byte) 0, sendMsg, "00 00", (byte) 0));
            return;
        }

        String song = player.getLastCommandMessage();
        for (String s : GameConstants.GAME_SONGS) {
            if (s.equalsIgnoreCase(song)) {    // thanks Masterrulax for finding an issue here
                player.getMap().broadcastMessage(PacketCreator.musicChange(s));
                player.yellowMessage("Now playing song " + s + ".");
                return;
            }
        }

        String sendMsg = "";
        sendMsg += "Song not found, please enter a song below.\r\n\r\n";
        sendMsg += getSongList();

        c.sendPacket(PacketCreator.getNPCTalk(NpcId.BILLY, (byte) 0, sendMsg, "00 00", (byte) 0));
    }
}
