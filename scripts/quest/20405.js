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
            qm.sendNext("There's a note on the wall: 'The source of the curse still goes missing, but a strange device, that I suppose has been used by #rthem#k was found here.'", 3);
        } else if (status == 1) {
            qm.sendNextPrev("'The machine was sent to #rEreve#k for avaliation, I'll now set out to continue my mission. Let the Empress bless me on my journey.'", 3);
        } else if (status == 2) {
            qm.forceCompleteQuest();
            qm.dispose();
        }
    }
}
