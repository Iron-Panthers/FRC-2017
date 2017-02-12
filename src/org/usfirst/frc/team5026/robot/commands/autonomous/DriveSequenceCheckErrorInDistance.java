package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.commands.drive.DriveCheckError;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSequenceCheckErrorInDistance extends CommandGroup {

    public DriveSequenceCheckErrorInDistance() {
        addSequential(new DriveDistanceRampUp(SmartDashboard.getNumber(Constants.DRIVE_DISTANCE_RAMP_SMD_NAME, 50)));
        addSequential(new DriveDistanceRampDown());
        addSequential(new DriveCheckError());
    }
}
