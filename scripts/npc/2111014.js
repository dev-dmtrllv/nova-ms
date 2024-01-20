var status;

function start() {
    if (cm.isQuestStarted(3311)) {
        cm.sendOk("The diary of Dr. De Lang. A lot of formulas and pompous scientific texts can be found all way through the pages.", 2);
    }
    cm.dispose();
}
