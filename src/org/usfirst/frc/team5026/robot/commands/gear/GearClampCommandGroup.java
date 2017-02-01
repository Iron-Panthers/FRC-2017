package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GearClampCommandGroup extends CommandGroup {

    public GearClampCommandGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new WaitForGear());
    	addSequential(new WaitCommand(Constants.CLAMP_WAIT_TIME));
    	addSequential(new GearClampCommand());
    }
}
