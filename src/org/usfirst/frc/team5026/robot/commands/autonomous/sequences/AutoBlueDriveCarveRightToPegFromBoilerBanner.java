package org.usfirst.frc.team5026.robot.commands.autonomous.sequences;

import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistanceMotionProfiling;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistancePositionBanner;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueDriveCarveRightToPegFromBoilerBanner extends CommandGroup {

    public AutoBlueDriveCarveRightToPegFromBoilerBanner() {
    	addSequential(new AutoDriveDistancePositionBanner("Auto Boiler Close Blue", "Auto Boiler Far Blue", Constants.AUTO_BOILER_CARVE_COUNT_BLUE));
    	addSequential(new AutoDriveDistanceMotionProfiling("Auto Boiler Extra Distance Blue", "Auto Boiler Extra Distance Blue", Constants.AUTO_BOILER_STRAIGHT_COUNT_BLUE));
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
