package org.usfirst.frc.team5026.robot.commands.drive;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GearPosition;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnXDegrees extends Command {

	private double degrees;
	private int count;
	double p;
	double i;
	double tol;
	boolean reset;
	double predicted;
	double totalError;
	double omega;

	public DriveTurnXDegrees() {
		requires(Robot.drive);
	}
	
    public DriveTurnXDegrees(double degrees, boolean reset) {
    	this.degrees = degrees;
    	this.reset = reset;
    }


    protected void initialize() {
    	Robot.drive.setGear(GearPosition.LOW);
    	p = SmartDashboard.getNumber("Auto Rotation P", Constants.AUTO_TURN_P);
    	i = SmartDashboard.getNumber("Auto Rotation I", Constants.AUTO_TURN_I);
    	tol = SmartDashboard.getNumber("Auto Angle Rotation Tolerance", 0);
    	omega = SmartDashboard.getNumber("Auto Turn Omega", Constants.OMEGA);
    	if (degrees < 0) omega = -omega;
    	count = 0;
    	Robot.drive.left.setupVoltageMode();
    	Robot.drive.right.setupVoltageMode();
    	predicted = 0;
    	totalError = 0;
    	if (reset)
    	Robot.hardware.gyro.reset();
    }

    
    protected void execute() {
//    	double omega = predicted >= 0 ? Constants.OMEGA : -Constants.OMEGA;
    	predicted = predicted + omega * Constants.DELTA_TIME;
    	if (Math.abs(predicted) > Math.abs(degrees)) {
    		predicted = degrees;
    	}
    	double current = Robot.hardware.gyro.getAngle();
    	
    	double left = -((predicted - current) * p + (totalError) * i); // forwards is actually negative
    	double right = ((predicted - current) * p + (totalError) * i);
    	
    	totalError += (predicted - current);
    	Robot.drive.setLeftRightMotors(left,right);
    	if (Math.abs(Robot.hardware.gyro.getAngle() - degrees) <= tol) {
    		count++;
    	}
    	SmartDashboard.putNumber("Gyro error", (predicted - current));
    	SmartDashboard.putNumber("Gyro prediction", predicted);
    	SmartDashboard.putNumber("Gyro angle", Robot.hardware.gyro.getAngle());
    }

    
    protected boolean isFinished() {
//    	return false;
        return count >= Constants.AUTO_TURN_COUNT;
    }


    protected void end() {
    	Robot.drive.stopMotors();
    }


    protected void interrupted() {
    	Robot.drive.stopMotors();
    }
}
