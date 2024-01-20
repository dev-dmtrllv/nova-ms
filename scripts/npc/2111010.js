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
            if (cm.isQuestStarted(3309) && !cm.haveItem(4031708, 1)) {
                if (cm.canHold(4031708, 1)) {
                    cm.gainItem(4031708, 1);
                } else {
                    cm.sendOk("Have a ETC slot available to get the Alcadno's secret document.");
                }
            }

            cm.dispose();
        }
    }
}
