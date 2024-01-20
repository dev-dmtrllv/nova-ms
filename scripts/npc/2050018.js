/* Meteorite
	@Author RonanLana (Ronan)
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
            if (cm.isQuestStarted(3421)) {
                var meteoriteId = cm.getNpc() - 2050014;

                var progress = cm.getQuestProgressInt(3421, 1);
                if ((progress >> meteoriteId) % 2 == 0 || (progress == 63 && !cm.haveItem(4031117, 6))) {
                    if (cm.canHold(4031117, 1)) {
                        progress |= (1 << meteoriteId);

                        cm.gainItem(4031117, 1);
                        cm.setQuestProgress(3421, 1, progress);
                    } else {
                        cm.getPlayer().dropMessage(1, "Have a ETC slot available for this item.");
                    }
                }
            }

            cm.dispose();
        }
    }
}
