package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.commands.drive.DriveCheckError;
import org.usfirst.frc.team5026.robot.commands.drive.DriveDrivebaseForTime;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class DriveSequenceCheckErrorInDistance extends CommandGroup {

    public DriveSequenceCheckErrorInDistance() {
//        addSequential(new DriveDistanceRampUp());
//        addSequential(new DriveDistanceRampDown());
//        addSequential(new DriveCheckError());
        
        addParallel(new DriveDrivebaseForTime(0.75, 0.75, 1));
        addSequential(new WaitCommand(1));
        addSequential(new AutoDoNothing());
    }
}
