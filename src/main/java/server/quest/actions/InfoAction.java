package server.quest.actions;

import client.Character;
import provider.Data;
import provider.DataTool;
import server.quest.Quest;
import server.quest.QuestActionType;
/**
 * @author Ronan
 */
public class InfoAction extends AbstractQuestAction {

    private String info;
    private final int questID;

    public InfoAction(Quest quest, Data data) {
        super(QuestActionType.INFO, quest);
        questID = quest.getId();
        processData(data);
    }

    @Override
    public void processData(Data data) {
        info = DataTool.getString(data, "");
    }
    @Override
    public void run(Character chr, Integer extSelection) {
        chr.getAbstractPlayerInteraction().setQuestProgress(questID, info);
    }

}
