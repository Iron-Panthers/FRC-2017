package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.commands.drive.DriveTurnXDegrees;
import org.usfirst.frc.team5026.robot.commands.gear.GearUnClampCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearSequence_MidPos extends CommandGroup {
	
	//
	//	middle position is basically (with another skilled picture)
	//
	//								| <---retrieval zone
	//PEG		robot is not here	|
	//			here is the robot	| <---wall
	//								|drivers here
	//								|
	//

    public AutoGearSequence_MidPos() {
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
    	addSequential(new DriveStraightForSetDistance(93));		//drive towards airship/peg (7 ft 9 in)
    	addSequential(new GearUnClampCommand());				//release gear clamp
    	addSequential(new DriveStraightForSetDistance(-36));	//drive back ~3 feet
    	addSequential(new DriveTurnXDegrees(45));				//rotate right(maybe left) to face baseline
    	addSequential(new DriveStraightForSetDistance(24));		//drive foward ~2 ft to cross baseline
    	addSequential(new DriveTurnXDegrees(135));				//rotate right again(maybe left) for front to face alliance wall
    	addSequential(new DriveStraightForSetDistance(84));		//drive foward ~7 ft to reach alliance wall		
    }
}
