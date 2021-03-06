package org.usfirst.frc.team5026.robot.commands.autonomous.sequences;

import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistanceMotionProfilingInches;
import org.usfirst.frc.team5026.robot.commands.drive.DriveTurnXDegrees;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedDriveCarveLeftToPegFromBoilerWithGyro extends CommandGroup {

    public AutoRedDriveCarveLeftToPegFromBoilerWithGyro() {
    	addSequential(new AutoDriveDistanceMotionProfilingInches("Auto Boiler Far Red", "Auto Boiler Close Red", Constants.AUTO_BOILER_CARVE_COUNT_RED));
    	addSequential(new DriveTurnXDegrees(Constants.AUTO_BOILER_ANGLE_RED, true)); // CONSTANT
    	addSequential(new AutoDriveDistanceMotionProfilingInches("Auto Boiler Extra Distance Red", "Auto Boiler Extra Distance Red", Constants.AUTO_BOILER_STRAIGHT_COUNT_RED));
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
    }
}