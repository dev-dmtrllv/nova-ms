/* Ghosthunter Bob
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
            if (cm.haveItem(4220046, 1)) {
                // quest completing here when "forfeiting Timer's Egg", as well as reporting missing quests on M. Shrine are thanks to drmdsr & Thora

                cm.gainItem(4220046, -1);
                cm.sendOk("You want to hand the #r#t4220046##k to me, right? Alright, I'll take it for you.");
            } else {
                cm.sendOk("Hello there! I'm #b#p2041026##k, in charge of watching and reporting any paranormal activities in this area.");
            }

            cm.dispose();
        }
    }
}
