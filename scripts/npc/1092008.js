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
            if (!cm.isQuestStarted(6410)) {
                cm.sendOk("Hey, do you have any business with me?");
                cm.dispose();
            } else {
                cm.sendYesNo("Let's go save #r#p2095000##k?");
            }
        } else if (status == 1) {
            cm.warp(925010000, 0);
            cm.dispose();
        }
    }
}
