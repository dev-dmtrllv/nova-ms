package constants.net;

import net.opcodes.RecvOpcode;
import net.opcodes.SendOpcode;

import java.util.HashMap;
import java.util.Map;
/**
 * @author Ronan
 */
public class OpcodeConstants {
    public static Map<Integer, String> sendOpcodeNames = new HashMap<>();
    public static Map<Integer, String> recvOpcodeNames = new HashMap<>();

    public static void generateOpcodeNames() {
        for (SendOpcode op : SendOpcode.values()) {
            sendOpcodeNames.put(op.getValue(), op.name());
        }

        for (RecvOpcode op : RecvOpcode.values()) {
            recvOpcodeNames.put(op.getValue(), op.name());
        }
    }

}
