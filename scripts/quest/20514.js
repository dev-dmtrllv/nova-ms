// @Author Ronan

function raiseOpen() {
    var chr = qm.getPlayer();
    var questStatus = chr.getQuestStatus(qm.getQuest());

    if (questStatus == 0) {
        qm.setQuestProgress(20515, 0, chr.getLevel());
        qm.setQuestProgress(20515, 1, chr.getExp());
    } else if (questStatus == 1) {  // update mimiana progress...
        var diffExp = chr.getExp() - qm.getQuestProgressInt(20515, 1);

        var initLevel = qm.getQuestProgressInt(20515, 0);
        const ExpTable = Java.type('constants.game.ExpTable');
        for (var i = initLevel; i < chr.getLevel(); i++) {
            diffExp += ExpTable.getExpNeededForLevel(i);
        }

        if (diffExp > 0) {  // thanks IxianMace for noticing Mimiana egg not following progress by EXP
            const ItemInformationProvider = Java.type('server.ItemInformationProvider');
            var consItem = ItemInformationProvider.getInstance().getQuestConsumablesInfo(4220137);
            var exp = consItem.exp;
            var grade = consItem.grade;

            qm.setQuestProgress(20514, 0, Math.min(diffExp, exp * grade));
        }
    }

    qm.dispose();
}
