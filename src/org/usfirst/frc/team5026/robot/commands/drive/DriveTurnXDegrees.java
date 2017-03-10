package org.usfirst.frc.team5026.robot.commands.drive;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnXDegrees extends Command {

	private double degrees;

	public DriveTurnXDegrees() {
		requires(Robot.drive);
	}
	
    public DriveTurnXDegrees(double degrees) {
        this.degrees = degrees;
    }


    protected void initialize() {
    	Robot.drive.stopMotors();
    	Robot.drive.setRotate(degrees);
    }

    
    protected void execute() {
    	Robot.drive.rotateRobot(Constants.AUTO_TURN_SPEED * (Robot.drive.getGyroError()));
    }

    
    protected boolean isFinished() {
        return Robot.drive.isTurnFinished();
    }


    protected void end() {
    	Robot.drive.stopMotors();
    }


    protected void interrupted() {
    	Robot.drive.stopMotors();
    }
}
