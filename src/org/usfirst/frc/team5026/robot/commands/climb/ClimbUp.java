package org.usfirst.frc.team5026.robot.commands.climb;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbUp extends Command {

	private Climber climber;
	
    public ClimbUp() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.climber);
        climber = Robot.climber;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	climber.stopClimb();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//run joystick to climb
    	climber.setClimbMotors(Robot.oi.buttonBoard.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	climber.stopClimb();
    }
}
