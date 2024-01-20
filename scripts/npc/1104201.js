var status;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (mode == 0 && type > 0) {
            cm.dispose();
            return;
        }
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            if (!(cm.isQuestCompleted(20407) || cm.isQuestStarted(20407) && cm.getQuestProgressInt(20407, 9001010) != 0) && cm.getMap().countMonster(9001010) == 0 && cm.getMap().getNPCById(1104002) == null) {
                cm.sendOk("... Hnngh... #b#h0##k, is that you...? #r#p1104002##k... She's already here... #b#h0##k, I'm truly sorry I can't help you right now in this state, just when a bigger threat appeared I could do nothing for my people.... Please I beg you, please defeat her, #b#h0##k!! ....");
                cm.spawnNpc(1104002, new java.awt.Point(850, 0), cm.getMap());
            } else {
                cm.sendOk("...");
            }

            cm.dispose();
        }
    }
}
