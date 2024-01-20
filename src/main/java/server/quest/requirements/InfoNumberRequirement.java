package server.quest.requirements;

import client.Character;
import provider.Data;
import provider.DataTool;
import server.quest.Quest;
import server.quest.QuestRequirementType;
/**
 * @author Ronan
 */
public class InfoNumberRequirement extends AbstractQuestRequirement {

    private short infoNumber;
    private final int questID;

    public InfoNumberRequirement(Quest quest, Data data) {
        super(QuestRequirementType.INFO_NUMBER);
        questID = quest.getId();
        processData(data);
    }

    @Override
    public void processData(Data data) {
        infoNumber = (short) DataTool.getIntConvert(data, 0);
    }
    @Override
    public boolean check(Character chr, Integer npcid) {
        return true;
    }

    public short getInfoNumber() {
        return infoNumber;
    }
}
