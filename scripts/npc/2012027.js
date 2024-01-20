/* Harp String C
	Hidden Street - Eliza’s Garden (200010303)
 */

var status;
var harpNote = 'C';
var harpSounds = ["do", "re", "mi", "pa", "sol", "la", "si"];   // musical order detected thanks to Arufonsu
var harpSong = "CCGGAAGFFEEDDC|GGFFEED|GGFFEED|CCGGAAGFFEEDDC|";

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
            const PacketCreator = Java.type('tools.PacketCreator');
            cm.getMap().broadcastMessage(PacketCreator.playSound("orbis/" + harpSounds[cm.getNpc() - 2012027]));

            if (cm.isQuestStarted(3114)) {
                var idx = -1 * cm.getQuestProgressInt(3114);  // infoEx without infoNumber, must use one progress only, critical hit!

                if (idx > -1) {
                    var nextNote = harpSong[idx];

                    if (harpNote != nextNote) {
                        cm.setQuestProgress(3114, 0);

                        cm.getPlayer().sendPacket(PacketCreator.showEffect("quest/party/wrong_kor"));
                        cm.getPlayer().sendPacket(PacketCreator.playSound("Party1/Failed"));

                        cm.message("You've missed the note... Start over again.");
                    } else {
                        nextNote = harpSong[idx + 1];

                        if (nextNote == '|') {
                            idx++;

                            if (idx == 45) {     // finished lullaby
                                cm.message("Twinkle, twinkle, little star, how I wonder what you are.");
                                cm.setQuestProgress(3114, 42);

                                cm.getPlayer().sendPacket(PacketCreator.showEffect("quest/party/clear"));
                                cm.getPlayer().sendPacket(PacketCreator.playSound("Party1/Clear"));

                                cm.dispose();
                                return;
                            } else {
                                if (idx == 14) {
                                    cm.message("Twinkle, twinkle, little star, how I wonder what you are!");
                                } else if (idx == 22) {
                                    cm.message("Up above the world so high,");
                                } else if (idx == 30) {
                                    cm.message("like a diamond in the sky.");
                                }
                            }
                        }

                        cm.setQuestProgress(3114, -1 * (idx + 1));
                    }
                }
            }

            cm.dispose();
        }
    }
}
