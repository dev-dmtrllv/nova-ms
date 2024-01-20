/*
    NPC ID: 1052012 
    NPC NAME: Mong from Kong
    @author Ronan
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
            cm.sendYesNo("So, are you going to use the Internet Cafe? There is a fee to use the spaces there, that is #b5,000 mesos#k. Are you going to enter the Cafe?");
        } else if (status == 1) {
            if (cm.getMeso() < 5000) {
                cm.sendOk("Oh, you don't have the money, right? Sorry, I can't let you in.");
            } else {
                cm.gainMeso(-5000);
                cm.warp(193000000, "out00");
            }

            cm.dispose();
        }
    }
}
