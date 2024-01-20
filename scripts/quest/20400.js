/* 
	Chasing the Knight's Target
	
 */

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
            qm.sendNext("Not long ago, we received a distress signal of #bAdvanced Knight #p1103000##k, currently stationed somewhere in #rEl Nath#k. His Your job is to find him, first go talk to #b#p1101002##k and receive further instructions on your mission.");
        } else if (status == 1) {
            qm.forceCompleteQuest();
            qm.dispose();
        }
    }
}
