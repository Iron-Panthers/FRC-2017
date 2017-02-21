package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistanceRampUp extends Command {
	
	private double distanceLeft = 0;	//in inches
	private double distanceRight = 0;
	private double ramp = 1; // TODO THIS SHOULD BE FIXED IF RAMPING DOESN'T WORK, CHANGE TO 1!
	private double inc = 0.01;
	private double speed;
	
	public DriveDistanceRampUp(double distanceLeft, double distanceRight) {
		requires(Robot.drive);
		this.distanceLeft = distanceLeft;
		this.distanceRight = distanceRight;
		speed = Constants.STRAIGHT_DRIVE_SPEED;
	}
	public DriveDistanceRampUp(double distanceLeft, double distanceRight, double speed) {
		requires(Robot.drive);
		this.distanceLeft = distanceLeft;
		this.distanceRight = distanceRight;
		this.speed = speed;
	}
	public DriveDistanceRampUp() {
		speed = Constants.STRAIGHT_DRIVE_SPEED;
	}
	
	@Override
	protected void initialize() {
		if (distanceLeft == 0) distanceLeft = SmartDashboard.getNumber(Constants.DRIVE_DISTANCE_RAMP_SMD_NAME_LEFT, 20);
		if (distanceRight == 0) distanceRight = SmartDashboard.getNumber(Constants.DRIVE_DISTANCE_RAMP_SMD_NAME_RIGHT, 20);
		//TODO REMOVE ABOVE
		Robot.drive.stopMotors();
		Robot.drive.startDriveDistance(distanceLeft, distanceRight);
	}

	
	@Override
	protected void execute() {
		SmartDashboard.putNumber("Encoder Finish Left", Robot.drive.targetEncoderPosLeft);
		SmartDashboard.putNumber("Encoder Finish Right", Robot.drive.targetEncoderPosRight);
    	SmartDashboard.putNumber("Current Encoder Left", Robot.drive.getEnc()[0]);
    	SmartDashboard.putNumber("Current Encoder Right", Robot.drive.getEnc()[1]);
    	SmartDashboard.putNumber("Encoder Start Left", Robot.drive.startingEncoderPosLeft);
    	SmartDashboard.putNumber("Encoder Start Right", Robot.drive.startingEncoderPosRight);
    	// TODO REMOVE ABOVE
		Robot.drive.driveStraight(speed * ramp);
		if (ramp < 1) {
			ramp += inc;
		} else {
			ramp = 1;
		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.drive.isFinishedDrivingDistance();
	}
	
	@Override
	protected void end() {
		Robot.drive.stopMotors();
	}

	@Override
	protected void interrupted() {
		Robot.drive.stopMotors();
	}

}
