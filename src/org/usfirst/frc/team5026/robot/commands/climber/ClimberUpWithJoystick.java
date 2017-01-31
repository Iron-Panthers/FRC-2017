package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

public class ClimberUpWithJoystick extends Command {

	private Climber climber;
	
    public ClimberUpWithJoystick() {
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
        double joystickY = Robot.oi.buttonBoard.getY();
        double speed;
        if (joystickY <= 0.0) {
            speed = (0.41 * joystickY) + 0.71;
        } else {
            speed = Math.sqrt(0.0841 * joystickY) + 0.71;
        }
    	climber.setClimbMotors(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	climber.stopClimb();
    	System.out.println("CLIMBER END");
 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    		end();
    		System.out.println("CLIMBER INTERRUPTED");
    }
}
