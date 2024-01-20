/*	
	Author : 		Ronan
	NPC Name: 		Knocked Trash Can
	Map(s): 		Hut in the Swamp
	Description: 		Quest - The Run-down Huts in the Swamp
	Quest ID: 		2214
*/

var status = -1;
var canComplete;

function end(mode, type, selection) {
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
            var hourDay = qm.getHourOfDay();
            if (!(hourDay >= 17 && hourDay < 20)) {
                qm.sendNext("(Hmm, I'm searching the trash can but can't find the #t4031894# JM was talking about, maybe it's not time yet...)");
                canComplete = false;
                return;
            }

            if (!qm.canHold(4031894, 1)) {
                qm.sendNext("(Eh, I can't hold the #t4031894# right now, I need an ETC slot available.)");
                canComplete = false;
                return;
            }

            canComplete = true;
            qm.sendNext("(Ah, there is a crumbled note here... Hm, it contains details about some scheme that is about to happen, that must be what #r#p1052002##k was talking about.)");
        } else if (status == 1) {
            if (canComplete) {
                qm.forceCompleteQuest();
                qm.gainItem(4031894, 1);
                qm.gainExp(20000);
            }

            qm.dispose();
        }
    }
}
