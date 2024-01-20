/*
   @Author: Arthur L - Refactored command content into modules
*/
package client.command.commands.gm3;

import client.Character;
import client.Client;
import client.command.Command;
import io.netty.buffer.Unpooled;
import net.PacketHandler;
import net.PacketProcessor;
import net.packet.ByteBufInPacket;
import net.packet.InPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.HexTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PeCommand extends Command {
    {
        setDescription("Handle synthesized packets from file, and handle them as if sent from a client");
    }

    private static final Logger log = LoggerFactory.getLogger(PeCommand.class);

    @Override
    public void execute(Client c, String[] params) {
        Character player = c.getPlayer();
        String packet = "";
        try (BufferedReader br = Files.newBufferedReader(Path.of("pe.txt"))) {
            Properties packetProps = new Properties();
            packetProps.load(br);
            packet = packetProps.getProperty("pe");
        } catch (IOException ex) {
            ex.printStackTrace();
            player.yellowMessage("Failed to load pe.txt");
            return;

        }

        byte[] packetContent = HexTool.toBytes(packet);
        InPacket inPacket = new ByteBufInPacket(Unpooled.wrappedBuffer(packetContent));
        short packetId = inPacket.readShort();
        final PacketHandler packetHandler = PacketProcessor.getProcessor(0, c.getChannel()).getHandler(packetId);
        if (packetHandler != null && packetHandler.validateState(c)) {
            try {
                player.yellowMessage("Receiving: " + packet);
                packetHandler.handlePacket(inPacket, c);
            } catch (final Throwable t) {
                final String chrInfo = player != null ? player.getName() + " on map " + player.getMapId() : "?";
                log.warn("Error in packet handler {}. Chr {}, account {}. Packet: {}", packetHandler.getClass().getSimpleName(),
                        chrInfo, c.getAccountName(), packet, t);
            }
        }
    }
}
