package org.usfirst.frc.team5026.robot.commands.misc;

import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistanceMotionProfilingInches;
import org.usfirst.frc.team5026.robot.commands.groundgear.GroundGearElevationControlSequence;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GroundGearElevationState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroundGearScoreGearWhileDrivingBackward extends CommandGroup {

    public GroundGearScoreGearWhileDrivingBackward() {
        addParallel(new GroundGearElevationControlSequence(GroundGearElevationState.Legal));
        addParallel(new AutoDriveDistanceMotionProfilingInches(Constants.GROUND_GEAR_SCORING_DISTANCE, Constants.GROUND_GEAR_SCORING_DISTANCE)); // negative numbers because it goes towards the gear clamp side
    }
}
