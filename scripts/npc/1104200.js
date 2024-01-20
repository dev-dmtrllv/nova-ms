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
            cm.sendNext("#b#p1104002##k... The black witch... Trapped me here... There's no time now, she's already on her way to #rattack Ereve#k!");
        } else if (status == 1) {
            cm.sendYesNo("Fellow Knight, you must reach to #rEreve#k right now, #rthe Empress is in danger#k!! Even in this condition, I can still Magic Warp you there. When you're ready talk to me. #bAre you ready to face Eleanor?#k");
        } else if (status == 2) {
            if (cm.getWarpMap(913030000).countPlayers() == 0) {
                cm.warp(913030000, 0);
            } else {
                cm.sendOk("There's someone already challenging her. Please wait awhile.");
            }

            cm.dispose();
        }
    }
}
