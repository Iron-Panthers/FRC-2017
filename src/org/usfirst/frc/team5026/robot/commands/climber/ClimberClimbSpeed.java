package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberClimbSpeed extends Command {

    public ClimberClimbSpeed() {
        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.stopClimb();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climber.setClimbMotors(Math.sqrt(Constants.CLIMBER_SLOPE_WRAP) + Constants.CLIMBER_SPEED_WRAP);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climber.stopClimb();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climber.stopClimb();
    }
}
