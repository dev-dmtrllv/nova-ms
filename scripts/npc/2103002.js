/* Queen's treasure chest
 */

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
            if (cm.isQuestStarted(3923) && !cm.haveItem(4031578, 1)) {
                if (cm.canHold(4031578, 1)) {
                    cm.sendOk("You have just swiped the ring. Clear the area asap!", 2);
                    cm.gainItem(4031578, 1);
                } else {
                    cm.sendOk("You don't have a ETC slot available.", 2);
                }
            }

            cm.dispose();
        }
    }
}
