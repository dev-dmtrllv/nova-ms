
/* Ames the Wise
	Wedding exit map
	Gives Onyx Chest to anyone completing the wedding event.
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
            cm.sendOk("Hey there, did you enjoy the wedding? I will head you back to #bAmoria#k now.");
        } else if (status == 1) {
            var eim = cm.getEventInstance();
            if (eim != null) {
                var boxId = (cm.getPlayer().getId() == eim.getIntProperty("groomId") || cm.getPlayer().getId() == eim.getIntProperty("brideId")) ? 4031424 : 4031423;

                if (cm.canHold(boxId, 1)) {
                    cm.gainItem(boxId, 1);
                    cm.warp(680000000);
                    cm.sendOk("You just received an Onyx Chest. Search for #b#p9201014##k, she is at the top of Amoria, she knows how to open these.");
                } else {
                    cm.sendOk("Please make room on your ETC inventory to receive the Onyx Chest.");
                    cm.dispose();
                    return;
                }
            } else {
                cm.warp(680000000);
            }

            cm.dispose();
        }
    }
}
