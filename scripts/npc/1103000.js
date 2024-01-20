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
            cm.sendOk("I am Advanced Knight #b#p1103000##k. Thanks to your bravery I and all of Ereve have been rescued from the grasps of Eleanor. By the kindness of our Empress, well battled!");
            cm.dispose();
        }
    }
}
