var status = -1;

function start(mode, type, selection) {
    if (mode == -1) {
        qm.dispose();
    } else {
        if (mode == 0 && type > 0) {
            qm.dispose();
            return;
        }

        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            qm.sendNext("Aaaargh... Yeti's #b#t4032339##k has just been stolen! How frustrating, Yeti worked hard to get it, just to have it stolen by that #rThief Crow#k...", 9);
        } else if (status == 1) {
            qm.sendNextPrev("Hey, I was just passing by and could not refrain from hearing you just now. I can lend you my strength, where did the thief go?", 3);
        } else if (status == 2) {
            qm.sendNextPrev("Oh, how nice of you... Thief has passed #rthrough the gate at west#k. Bring back the #b#t4032339##k, Yeti needs it to give to beloved one.", 9);
        } else if (status == 3) {
            qm.sendNextPrev("Ok, wait there. I will return it back to you in no time!", 3);
        } else if (status == 4) {
            qm.forceStartQuest();
            qm.dispose();
        }
    }
}
