/*
-- HeavenMS 083 Script ----------------------------------------------------------------------------
    NPC - Simon
-- By ---------------------------------------------------------------------------------------------
    Jayd
-- Version Info -----------------------------------------------------------------------------------
    1.0 - First Version by Jayd
---------------------------------------------------------------------------------------------------    
 */

var status;
var smap = 681000000;
var hv = 209000000;
var tst, b2h;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    if (mode == -1) {
        cm.dispose();
    } else {
        if (status == 0 && mode == 0) {
            cm.sendNext("Let me know if you've changed your mind!");
            cm.dispose();
        }

        if (mode == 1) {
            status++;
        } else {
            status--;
        }

        if (status == 0) {
            if (cm.getMapId() == hv) {
                tst = 1; //to shalom temple
                cm.sendYesNo("The Shalom Temple is unlike any other place in Happyville, would you like to head to #bShalom Temple#k?"); //not GMS lol
            } else if (cm.getMapId() == smap) {
                b2h = 1; //back to happyville
                cm.sendYesNo("Would you like to head back to Happyville?");
            }
        } else if (status == 1) {
            if (tst == 1) {
                cm.warp(smap, 0);
                cm.dispose();
            } else if (b2h == 1) {
                cm.warp(hv, 0);
                cm.dispose();
            }
        }
    }
}
