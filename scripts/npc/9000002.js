var status = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else if (mode == 0) {
        cm.dispose();
    } else {
        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            cm.sendNext("Bam bam bam bam!! You have won the game from the \r\n#bEVENT#k. Congratulations on making it this far!");
        } else if (status == 1) {
            cm.sendNext("You'll be awarded the #bScroll of Secrets#k as the winning prize. On the scroll, it has secret information written in ancient characters.");
        } else if (status == 2) {
            cm.sendNext("The Scroll of Secrets can be deciphered by #rChun Ji#k or \r\n#rGeanie#k at Ludibrium. Bring it with you and something good's bound to happen.");
        } else if (status == 3) {
            if (cm.canHold(4031019)) {
                cm.gainItem(4031019);
                cm.warp(cm.getPlayer().getSavedLocation("EVENT"));
                cm.dispose();
            } else {
                cm.sendNext("I think your Etc window is full. Please make room, then talk to me.");
            }
        } else if (status == 4) {
            cm.dispose();
        }
    }
}  
