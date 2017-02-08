package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ResistanceSequence extends CommandGroup {
	//runs climber motors and with a built in time
    public ResistanceSequence(double speed) {
        addSequential(new WaitCommand(1));
        addSequential(new ClimberUpClimb(speed));
        addSequential(new ClimberStop());
    }
}
