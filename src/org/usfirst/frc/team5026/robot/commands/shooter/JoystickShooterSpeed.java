package org.usfirst.frc.team5026.robot.commands.shooter;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.RobotMap;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class JoystickShooterSpeed extends Command {

    public JoystickShooterSpeed() {
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setSetpoint(0);
    	Robot.shooter.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.buttonBoard.getRawButton(RobotMap.BOARD_BUTTON_2)) {
    		Robot.shooter.setSetpoint(joyYToRate( Robot.oi.buttonBoard.getScaledDeadzoneY() ));
    	}
    }

    private double joyYToRate(double joyY) {
    	double adjustedJoyY = (joyY + 1) / 2;
    	return adjustedJoyY * Constants.SHOOTER_JOYSTICK_TO_RATE;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.disable();
    }
}
