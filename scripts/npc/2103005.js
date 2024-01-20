/* 
	Food depot on Ariant Residential area
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
            if (cm.isQuestStarted(3929)) {
                var progress = cm.getQuestProgress(3929);
                var slot = 1;

                var ch = progress[slot];
                if (ch == '2') {
                    var nextProgress = progress.substr(0, slot) + '3' + progress.substr(slot + 1);

                    cm.gainItem(4031580, -1);
                    cm.setQuestProgress(3929, nextProgress);
                }
            }

            cm.dispose();
        }
    }
}
