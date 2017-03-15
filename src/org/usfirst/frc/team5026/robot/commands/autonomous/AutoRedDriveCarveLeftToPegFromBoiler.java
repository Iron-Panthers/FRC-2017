package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedDriveCarveLeftToPegFromBoiler extends CommandGroup {

    public AutoRedDriveCarveLeftToPegFromBoiler() {
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_BOILER_TARGET_FAR, Constants.AUTO_BOILER_TARGET_CLOSE));
    	addSequential(new AutoDriveDistancePosition(Constants.AUTO_BOILER_AFTER_TURN_TO_PEG, Constants.AUTO_BOILER_AFTER_TURN_TO_PEG));
    }
}