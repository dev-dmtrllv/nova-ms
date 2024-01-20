var status;
var area;

function start() {
    area = cm.getMapId() % 10;
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
            if (area > 0) {
                cm.sendYesNo("Do you wish to leave this place?");
            } else {
                cm.sendYesNo("Do you wish to return to #bHappyville#k?");
            }
        } else {
            if (area > 0) {
                cm.warp(cm.getMapId() + 1, 0);
            } else {
                cm.warp(209000000);
            }

            cm.dispose();
        }
    }
}
