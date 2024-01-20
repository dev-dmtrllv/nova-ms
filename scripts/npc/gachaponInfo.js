/**
 * @author: Ronan
 * @npc: Pio
 * @func: Gachapon Loot Announcer
 */

var status;
var gachaMessages;

function start() {
    const Gachapon = Java.type('server.gachapon.Gachapon');
    gachaMessages = Gachapon.GachaponType.getLootInfo();
    gachas = Gachapon.GachaponType.values();

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
            var sendStr = "Hi, #r#p" + cm.getNpc() + "##k here! I'm announcing all obtainable loots from the Gachapons. Which Gachapon machine would you like to look?\r\n\r\n#b" + gachaMessages[0] + "#k";
            cm.sendSimple(sendStr);
        } else if (status == 1) {
            var sendStr = "Loots from #b" + gachas[selection].name() + "#k:\r\n\r\n" + gachaMessages[selection + 1];
            cm.sendPrev(sendStr);
        } else if (status == 2) {
            cm.dispose();
        }
    }
}
