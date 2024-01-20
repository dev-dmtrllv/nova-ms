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
            var qs = cm.getQuestStatus(2162);

            if ((qs == 0 || qs == 1) && !cm.haveItem(4031839, 1)) {
                if (cm.canHold(4031839, 1)) {
                    cm.gainItem(4031839, 1);
                    cm.sendNext("(You retrieved a Crumpled Paper standing out of the trash can. It's content seems important.)", 2);
                } else {
                    cm.sendNext("(You see a Crumpled Paper standing out of the trash can. It's content seems important, but you can't retrieve it since your inventory is full.)", 2);
                }
            }

            cm.dispose();
        }
    }
}
