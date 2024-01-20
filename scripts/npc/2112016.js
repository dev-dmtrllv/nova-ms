/**
 * @author: Ronan
 * @npc: Hidden Documents
 * @func: Yulete lab 2 quest
 */

function start() {
    if (cm.isQuestStarted(3367)) {
        var c = cm.getQuestProgressInt(3367, 30);
        if (c >= 30) {
            cm.sendNext("(All files have been organized. Report the found files to Yulete.)", 2);
            cm.dispose();
            return;
        }

        var book = (cm.getNpcObjectId() % 30);
        var prog = cm.getQuestProgressInt(3367, book);
        if (prog == 0) {
            c++;

            if (book < 20) {
                if (!cm.canHold(4031797, 1)) {
                    cm.sendNext("(You found a report file, but since your ETC is full you choose to put the file in the place you've found.)");
                    cm.dispose();
                    return;
                } else {
                    cm.gainItem(4031797, 1);
                    cm.setQuestProgress(3367, 31, cm.getQuestProgressInt(3367, 31) + 1);
                }
            }

            cm.sendNext("(Organized file. #r" + (30 - c) + "#k left.)", 2);

            cm.setQuestProgress(3367, book, 1);
            cm.setQuestProgress(3367, 30, c);
        }
    }

    cm.dispose();
}
