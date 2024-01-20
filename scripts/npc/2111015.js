var status;

function isPillUsed(ch) {
    const BuffStat = Java.type('client.BuffStat');
    return ch.getBuffSource(BuffStat.HPREC) == 2022198;
}

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
            if (cm.isQuestStarted(3314) && !cm.haveItem(2022198, 1) && !isPillUsed(cm.getPlayer())) {
                if (cm.canHold(2022198, 1)) {
                    cm.gainItem(2022198, 1);
                    cm.sendOk("You took the pills that were laying on the desk.", 2);
                } else {
                    cm.sendOk("You don't have a USE slot available to get Russellon's pills.", 2);
                }
            }

            cm.dispose();
        }
    }
}
