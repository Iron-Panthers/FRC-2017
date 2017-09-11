package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.util.GroundGearElevationState;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GroundGearElevationControlSequence extends CommandGroup {

    public GroundGearElevationControlSequence(GroundGearElevationState target) {
        addSequential(new GroundGearElevationControl(target));
        addSequential(new GroundGearWaitForStabilize(target));
    }
}
