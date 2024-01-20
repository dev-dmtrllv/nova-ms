var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
    } else {
        if (mode == 0 && type > 0) {
            qm.dispose();
            return;
        }

        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            qm.sendNext("Thank you for defeating #rFaust#k. That will finally settle my spirit to rest.");
        } else if (status == 1) {
            qm.forceCompleteQuest();
            qm.gainFame(8);
            qm.dispose();
        }
    }
}
