package net.server.channel.handlers;

// import client.Character;
import client.Client;
import client.processor.npc.FredrickProcessor;
import net.AbstractPacketHandler;
import net.packet.InPacket;
/**
 * @author kevintjuh93
 */
public class FredrickHandler extends AbstractPacketHandler {
    private final FredrickProcessor fredrickProcessor;

    public FredrickHandler(FredrickProcessor fredrickProcessor) {
        this.fredrickProcessor = fredrickProcessor;
    }

    @Override
    public void handlePacket(InPacket p, Client c) {
        // Character chr = c.getPlayer();
        byte operation = p.readByte();

        switch (operation) {
            case 0x19: //Will never come...
                //c.sendPacket(PacketCreator.getFredrick((byte) 0x24));
                break;
            case 0x1A:
                fredrickProcessor.fredrickRetrieveItems(c);
                break;
            case 0x1C: //Exit
                break;
            default:
        }
    }
}
