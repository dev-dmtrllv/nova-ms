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
            if (!cm.isEventLeader()) {
                cm.sendNext("Please let your party leader talk to me for further instructions to proceed to the next stage.");
                cm.dispose();
                return;
            }

            var eim = cm.getEventInstance();
            if (eim.getIntProperty("statusStg1") == 1) {
                cm.sendNext("Go through this tunnel for the boss battle.");
            } else {
                if (cm.haveItem(4032118, 15)) {
                    cm.gainItem(4032118, -15);

                    eim.setIntProperty("statusStg1", 1);
                    eim.showClearEffect();
                    eim.giveEventPlayersStageReward(1);

                    cm.sendNext("You got the letters, great! Now, you can proceed to the room MV is through this tunnel. Be prepared!");
                } else {
                    cm.sendNext("Please hand me #r15 secret letters#k.");
                }
            }

            cm.dispose();
        }
    }
}
