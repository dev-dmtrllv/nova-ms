package net.server.task;

import net.server.world.World;
/**
 * @author Ronan
 */
public class PetFullnessTask extends BaseTask {

    @Override
    public void run() {
        wserv.runPetSchedule();
    }

    public PetFullnessTask(World world) {
        super(world);
    }
}
