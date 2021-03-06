package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ClimberSequence extends CommandGroup {
	//runs climber motors and with a built in time delay
    public ClimberSequence() {
        addParallel(new ClimberUpClimbInitial());
        addSequential(new WaitCommand(1));
        addSequential(new ClimberUpClimb());
        addSequential(new ClimberStop());
        
    }
}
