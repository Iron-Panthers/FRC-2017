package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBlueDriveCarveRightToPegFromBoiler extends CommandGroup {

    public AutoBlueDriveCarveRightToPegFromBoiler() {
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_BOILER_TARGET_CLOSE, Constants.AUTO_BOILER_TARGET_FAR));
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_BOILER_AFTER_TURN_TO_PEG, Constants.AUTO_BOILER_AFTER_TURN_TO_PEG));
    }
}
