package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class CharacterHpDecreaseTask extends BaseTask {
    
    @Override
    public void run() {
        wserv.runPlayerHpDecreaseSchedule();
    }
    
    public CharacterHpDecreaseTask(World world) {
        super(world);
    }
}
