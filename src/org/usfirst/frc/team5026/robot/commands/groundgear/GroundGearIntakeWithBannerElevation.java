package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.util.GroundGearElevationState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroundGearIntakeWithBannerElevation extends CommandGroup {

    public GroundGearIntakeWithBannerElevation() {
        addSequential(new GroundGearIntake());
        addSequential(new GroundGearIntake(false)); // GroundGearIntake now runs without stopping
        addSequential(new GroundGearElevationControlSequence(GroundGearElevationState.Scoring));
        addSequential(new GroundGearStop()); // Stops intake
    }
}
