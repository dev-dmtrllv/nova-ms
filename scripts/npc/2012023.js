/* Maple Marble
	4th job skill - Night Lord
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
            if (cm.haveItem(4031476)) {
                cm.gainItem(4031476, -1);

                if (cm.canHold(4031456, 1)) {
                    cm.gainItem(4031456, 1);
                }
            }

            cm.dispose();
        }
    }
}
