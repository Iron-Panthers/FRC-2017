package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberRappel extends Command {

	private Climber climber;
	
    public ClimberRappel() {
    	requires(Robot.climber);
    	climber = Robot.climber;
    }

    protected void initialize() {
    }

    protected void execute() {
    	climber.setClimbMotors(Constants.CLIMBER_SPEED_RAPPEL);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	climber.stopClimb();
    }

    protected void interrupted() {
    	end();
    }
}
