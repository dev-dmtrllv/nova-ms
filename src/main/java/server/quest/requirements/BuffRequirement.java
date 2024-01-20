package server.quest.requirements;

import client.Character;
import provider.Data;
import provider.DataTool;
import server.quest.Quest;
import server.quest.QuestRequirementType;
/**
 * @author Ronan
 */
public class BuffRequirement extends AbstractQuestRequirement {
    private int buffId = 1;

    public BuffRequirement(Quest quest, Data data) {
        super(QuestRequirementType.BUFF);
        processData(data);
    }

    @Override
    public void processData(Data data) {
        // item buffs are negative
        buffId = -1 * Integer.parseInt(DataTool.getString(data));
    }

    @Override
    public boolean check(Character chr, Integer npcid) {
        return chr.hasBuffFromSourceid(buffId);
    }
}
