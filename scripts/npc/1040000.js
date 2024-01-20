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
            if (cm.isQuestStarted(28177) && !cm.haveItem(4032479)) {
                if (cm.canHold(4032479)) {
                    cm.gainItem(4032479, 1);
                    cm.sendOk("Huh, are you looking for me? Chief Stan sent you here, right? But hey, I am not the suspect you seek. If I have some proof? Here, take this and return it to #b#p1012003##k.");
                } else {
                    cm.sendOk("Hey, make a slot available before talking to me.");
                }
            } else {
                cm.sendOk("Zzzzzz...");
            }

            cm.dispose();
        }
    }
}
