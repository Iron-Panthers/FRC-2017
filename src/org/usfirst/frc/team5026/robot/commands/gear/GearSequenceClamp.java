package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GearSequenceClamp extends CommandGroup {

    public GearSequenceClamp() {
    	addSequential(new WaitForGear());
    	addSequential(new WaitCommand(Constants.CLAMP_WAIT_TIME));
    	addSequential(new GearClampCommand());
    }
}
