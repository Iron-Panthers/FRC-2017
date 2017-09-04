package org.usfirst.frc.team5026.robot.commands.autonomous.sequences;

import org.usfirst.frc.team5026.robot.commands.autonomous.AutoBannerTurn;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistanceMotionProfiling;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRedDriveCarveLeftToPegFromBoilerBanner extends CommandGroup {

    public AutoRedDriveCarveLeftToPegFromBoilerBanner() {
    	addSequential(new AutoDriveDistanceMotionProfiling("Auto Boiler Far Red", "Auto Boiler Far Red", Constants.AUTO_BOILER_CARVE_COUNT_RED));
    	addSequential(new AutoBannerTurn());
    	addSequential(new AutoDriveDistanceMotionProfiling("Auto Boiler Extra Distance Red", "Auto Boiler Extra Distance Red", Constants.AUTO_BOILER_STRAIGHT_COUNT_RED));
    }
}