var status = -1;

function end(mode, type, selection) {
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
            qm.sendNext("Aran, you came back home safe! So, how fared the mission in Mu Lung? #rGentleman#k ambushed and stole the seal rock again? That's unfortunate. At least you are unharmed, I'm glad.");
        } else if (status == 1) {
            qm.sendNext("I've researched some skill books, trying to trace any lost skills of yours. Good news I found one of them: it's the #rFinal Charge#k! With it you will be able to draw closer opposing monsters at each swipe. It's a fine improvement for your arsenal, isn't it?");
        } else if (status == 2) {
            qm.gainExp(20000);
            qm.teachSkill(21100002, 0, 30, -1); // final charge

            qm.forceCompleteQuest();

            qm.dispose();
        }
    }
}
