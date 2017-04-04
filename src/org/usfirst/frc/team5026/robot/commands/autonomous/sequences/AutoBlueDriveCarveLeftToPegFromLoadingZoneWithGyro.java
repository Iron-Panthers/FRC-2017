package org.usfirst.frc.team5026.robot.commands.autonomous.sequences;

import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistancePosition;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistancePositionGyro;
import org.usfirst.frc.team5026.robot.commands.drive.DriveTurnXDegrees;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoBlueDriveCarveLeftToPegFromLoadingZoneWithGyro extends CommandGroup {

    public AutoBlueDriveCarveLeftToPegFromLoadingZoneWithGyro() {
    	addSequential(new AutoDriveDistancePositionGyro("Auto Loading Far Blue", "Auto Loading Close Blue", Constants.AUTO_LOADING_CARVE_COUNT_BLUE, Constants.AUTO_LOADING_ANGLE_BLUE));
    	addSequential(new DriveTurnXDegrees(Constants.AUTO_LOADING_ANGLE_BLUE, false)); // CONSTANT
    	addSequential(new AutoDriveDistancePosition("Auto Loading Extra Distance Blue", "Auto Loading Extra Distance Blue", Constants.AUTO_LOADING_STRAIGHT_COUNT_BLUE));
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
