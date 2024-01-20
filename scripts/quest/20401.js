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
            qm.sendNext("Last time #bAdvanced Knight #p1103000##k was seen, he was investigating the surging increase on #rzombies#k lately on the #rhigh-grounds of El Nath#k. You should get yourself there to see if you can find any clue of what could have happened.");
        } else if (status == 1) {
            qm.forceCompleteQuest();
            qm.dispose();
        }
    }
}
