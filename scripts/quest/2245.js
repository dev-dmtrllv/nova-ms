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
            em = qm.getEventManager("BalrogQuest");
            if (em == null) {
                qm.sendOk("Sorry, but the BalrogQuest is closed.");
                return;
            }

            var em = qm.getEventManager("BalrogQuest");
            if (!em.startInstance(qm.getPlayer())) {
                qm.sendOk("There is currently someone in this map, come back later.");
            } else {
                qm.forceStartQuest();
                qm.dispose();
            }
        } else if (status == 1) {
            qm.dispose();
        }
    }
}
