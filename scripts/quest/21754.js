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
            if (!qm.canHold(4032328, 1)) {
                qm.sendNext("Hm, I will need you to prepare a ETC slot for a letter I need to give you.");
                qm.dispose();
                return;
            }

            qm.sendNext("Here, take this. Send it to #r#p1002104##k, it contains a relevant matter for protecting this world. Please comply to this request.");
        } else if (status == 1) {
            qm.forceStartQuest();

            qm.gainItem(4032328, 1);
            qm.dispose();
        }
    }
}
