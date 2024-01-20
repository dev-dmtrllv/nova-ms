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
            qm.sendNext("Hey did you see how strange #rLakelis#k has been acting these days? We should see what's going on aabout her, her actions have been so weird lately...");
        } else if (status == 1) {
            qm.forceCompleteQuest();
            qm.gainExp(7000);

            if (isAllSubquestsDone() && qm.haveItem(4031894)) {
                qm.gainItem(4031894, -1);
            }

            qm.dispose();
        }
    }
}

function isAllSubquestsDone() {
    for (var i = 2216; i <= 2219; i++) {
        if (!qm.isQuestCompleted(i)) {
            return false;
        }
    }

    return true;
}
