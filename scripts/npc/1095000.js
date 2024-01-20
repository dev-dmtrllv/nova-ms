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
            cm.sendYesNo("#b#p2095000##k must be some way up this cliff, according to our latest reports... Or are you saying you want to #rleave here#k?");
        } else if (status == 1) {
            cm.warp(120000104);
            cm.dispose();
        }
    }
}
