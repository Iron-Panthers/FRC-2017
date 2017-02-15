package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberStop extends Command {

	private Climber climber;
	
    public ClimberStop() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
    	climber.stopClimb();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    	climber.stopClimb();
    }

    protected void interrupted() {
    		end();
    }
}

