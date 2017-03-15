package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedDriveCarveRightToPegFromLoadingZone extends CommandGroup {

    public AutoRedDriveCarveRightToPegFromLoadingZone() {
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_LOADING_TARGET_CLOSE, Constants.AUTO_LOADING_TARGET_FAR));
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_LOADING_AFTER_TURN_TO_PEG, Constants.AUTO_LOADING_AFTER_TURN_TO_PEG));
    }
}
