var status = -1;

function end(mode, type, selection) {
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
            qm.sendNext("Oh, a letter for the #rempress#k? From the #bheroes#k?!");
        } else if (status == 1) {
            qm.forceCompleteQuest();
            qm.gainExp(1000);
            qm.gainItem(4032330, -1);
            qm.dispose();
        }
    }
}
