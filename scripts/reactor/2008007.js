/* @Author Ronan
 * 
 * 2008007.js: OrbisPQ jail obstacle trigger
*/

function hit() {
    var map = rm.getMap();
    map.moveEnvironment("trap" + rm.getReactor().getName()[5], 1);
}

function act() {}
