/* NPC: Donation Box (9000041)
	Victoria Road : Henesys
	
	NPC Bazaar:
        * @author Ronan Lana
*/

var options = ["EQUIP", "USE", "SET-UP", "ETC"];
var name;
var status;
var selectedType = 0;

function start() {
    status = -1;
    action(1, 0, 0);
}

function action(mode, type, selection) {
    status++;
    if (mode != 1) {
        cm.dispose();
        return;
    }

    if (status == 0) {
        const YamlConfig = Java.type('config.YamlConfig');
        if (!YamlConfig.config.server.USE_ENABLE_CUSTOM_NPC_SCRIPT) {
            cm.sendOk("The medal ranking system is currently unavailable...");
            cm.dispose();
            return;
        }

        var selStr = "Hello, I am the #bBazaar NPC#k! Sell to me any item on your inventory you don't need. #rWARNING#b: Make sure you have your items ready to sell at the slots #rAFTER#b the item you have selected to sell.#k Any items #bunder#k the item selected will be sold thoroughly.";
        for (var i = 0; i < options.length; i++) {
            selStr += "\r\n#L" + i + "# " + options[i] + "#l";
        }
        cm.sendSimple(selStr);
    } else if (status == 1) {
        selectedType = selection;
        cm.sendGetText("From what item on your #r" + options[selectedType] + "#k inventory do you want to start the transaction?");
    } else if (status == 2) {
        name = cm.getText();
        var res = cm.getPlayer().sellAllItemsFromName(selectedType + 1, name);

        if (res > -1) {
            cm.sendOk("Transaction complete! You received #r" + cm.numberWithCommas(res) + " mesos#k from this action.");
        } else {
            cm.sendOk("There is no #b'" + name + "'#k in your #b" + options[selectedType] + "#k inventory!");
        }

        cm.dispose();
    }
}
