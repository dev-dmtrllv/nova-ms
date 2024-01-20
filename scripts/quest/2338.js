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
            if (qm.haveItem(2430014, 1)) {
                qm.sendNext("It looks like you already have one #b#t2430014##k on your inventory.");
                status = 1;
                return;
            }

            qm.sendNext("You've used the #b#t2430014##k? Oh well, good thing I have a spare one right here.");
        } else if (status == 1) {
            if (!qm.canHold(2430014, 1)) {
                qm.sendNext("Please make a USE slot available to get it, alright?");
            } else {
                qm.gainItem(2430014, 1);
                qm.forceCompleteQuest();
                qm.dispose();
            }
        } else if (status == 2) {
            qm.dispose();
        }
    }
}
