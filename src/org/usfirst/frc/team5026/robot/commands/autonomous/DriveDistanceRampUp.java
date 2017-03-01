package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveDistanceRampUp extends Command {
	
	private double distance = 0;	//in inches
	private double ramp = 1;
	private double inc = 0.01;
	private double speed;
	
	public DriveDistanceRampUp(double distance) {
		requires(Robot.drive);
		this.distance = distance;
		speed = Constants.STRAIGHT_DRIVE_SPEED;
	}
	public DriveDistanceRampUp(double distance, double speed) {
		requires(Robot.drive);
		this.distance = distance;
		this.speed = speed;
	}
	public DriveDistanceRampUp() {
		speed = Constants.STRAIGHT_DRIVE_SPEED;
	}
	
	@Override
	protected void initialize() {
		distance = SmartDashboard.getNumber(Constants.DRIVE_DISTANCE_RAMP_SMD_NAME, 50);
		Robot.drive.stopMotors();
		Robot.drive.startDriveDistance(distance);
	}

	
	@Override
	protected void execute() {
		SmartDashboard.putNumber("RAW FINISH ENCODER", Robot.drive.targetLeftEncoderPos);
    	SmartDashboard.putNumber("RAW CURRENT ENCODER", Robot.drive.getLeftEnc());
    	SmartDashboard.putNumber("RAW START ENCODER", Robot.drive.startingLeftEncoderPos);
		Robot.drive.driveStraight(speed * ramp);
		if (ramp < 1) {
			ramp += inc;
		} else if (ramp > 1) {
			ramp = 1;
		}
	}

	@Override
	protected boolean isFinished() {
		return Robot.drive.isFinishedDrivingDistance(Robot.drive.encLeftMotor);
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
