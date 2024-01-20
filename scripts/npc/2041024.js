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
            cm.sendOk("For those capable of great feats and bearers of an unwavering resolve, the #bfinal destination#k lies ahead past the gate. The Machine Room accepts only #rone party at a time#k, so make sure your party is ready when crossing the gate.");
            cm.dispose();
        }
    }
}
