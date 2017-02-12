package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistanceRampUp extends Command {
	
	private double distance;	//in inches
	private double ramp = 0.1;
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
	
	@Override
	protected void initialize() {
		Robot.drive.stopMotors();
		Robot.drive.startDriveDistance(distance);
	}

	
	@Override
	protected void execute() {
		Robot.drive.driveStraight(speed * ramp);
		if (ramp < 1) {
			ramp += 0.01;
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
