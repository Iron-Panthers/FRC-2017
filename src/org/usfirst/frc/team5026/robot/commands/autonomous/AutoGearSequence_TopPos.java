package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.commands.drive.DriveTurnXDegrees;
import org.usfirst.frc.team5026.robot.commands.gear.GearUnClampCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGearSequence_TopPos extends CommandGroup {
	
	//
	//	top position is basically (with skilled picture)
	//
	//						| <---retrieval zone
	//	here is the robot	|
	//						| <---wall
	//						|drivers here
	//						|
	//

    public AutoGearSequence_TopPos() {
    	//maybe wait 1.5 seconds
    	addSequential(new DriveStraightForSetDistance(93.5));	//drive towards airship (7 ft 9.5 in)
    	addSequential(new DriveTurnXDegrees(-45));				//turn towards side peg
    	//vision process?
    	//drive foward until in front of clamp (distance unknown)
    	addSequential(new GearUnClampCommand());				//release gear clamp
    	addSequential(new DriveStraightForSetDistance(-60));	//drive back 5 feet
    	addSequential(new DriveTurnXDegrees(225));				//rotate front towards alliance wall
    	addSequential(new DriveStraightForSetDistance(93.5));	//drive foward back to alliance wall
    }
}
