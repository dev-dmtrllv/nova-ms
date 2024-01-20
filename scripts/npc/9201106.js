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
            cm.sendOk("I came from far-away places looking for people powerful enough to join my expedition against the evil that lays waste on this land. Are you, by any chance, one of those people?");
            cm.dispose();
        }
    }
}
