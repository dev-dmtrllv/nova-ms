/* Andy
	Tera Forest Time Gate
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
            cm.sendOk("Hi, I am Andy, the time traveler from a not so distant future. I have come to avert the creation of machines by the greedy people of this time. They went berserk on my time and consumed everything to dust. I must stop it at any cost!");
            cm.dispose();
        }
    }
}
