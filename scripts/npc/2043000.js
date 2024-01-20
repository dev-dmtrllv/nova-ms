/* Papulatus
	Origin of Clock Tower (922020300)
	Time Leap quest NPC.
 */

var status;

function start() {  // thanks iPunchEm for NPC visibility
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
            cm.sendNext("You don't belong to this world... Return now.");
        } else if (status == 1) {
            cm.warp(220080000);
            cm.dispose();
        }
    }
}
