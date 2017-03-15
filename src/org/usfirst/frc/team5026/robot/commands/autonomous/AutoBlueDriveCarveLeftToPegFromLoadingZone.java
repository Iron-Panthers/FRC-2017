package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueDriveCarveLeftToPegFromLoadingZone extends CommandGroup {

    public AutoBlueDriveCarveLeftToPegFromLoadingZone() {
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_LOADING_TARGET_FAR, Constants.AUTO_LOADING_TARGET_CLOSE));
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_LOADING_AFTER_TURN_TO_PEG, Constants.AUTO_LOADING_AFTER_TURN_TO_PEG));
    }
}
