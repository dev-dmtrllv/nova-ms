// NPC - Picture frame
// Location: Magatia - Home of the Missing Alchemist
// Used to handle quest 3322 - Phyllia's Pendant

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

        if (!cm.isQuestStarted(3322) || cm.haveItem(4031697, 1)) {
            cm.dispose();
            return;
        }

        if (status == 0) {
            cm.sendOk("The hook behind the frame was unhooked, revealing a secret space within the frame. There inside, a silver pendant was found. After carefully removing the pendant, the frame was closed and placed back onto the table.");
        }
        else if (status == 1) {
            if (cm.canHold(4031697, 1)) {
                cm.gainItem(4031697);
            }
            else {
                cm.sendNext("Your inventory is full, please make sure you have an ETC slot available.");
            }
        }
        else {
            cm.dispose();
        }
    }
}
