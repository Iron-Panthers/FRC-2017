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
    	Robot.hardware.gyro.reset();
    }

    
    protected void execute() {
    	double angle = Robot.hardware.gyro.getAngle();
    	Robot.drive.setLeftRightMotors(Constants.AUTO_TURN_SPEED * -(degrees - angle) * Constants.AUTO_TURN_P, Constants.AUTO_TURN_SPEED * (degrees -  angle) * Constants.AUTO_TURN_P);
    	System.out.println(angle);
    }

    
    protected boolean isFinished() {
    	return false;
        //return Math.abs(Robot.hardware.gyro.getAngle() - degrees) <= Constants.AUTO_TURN_ANGLE_TOLERANCE;
    }


    protected void end() {
    	Robot.drive.stopMotors();
    }


    protected void interrupted() {
    	Robot.drive.stopMotors();
    }
}
