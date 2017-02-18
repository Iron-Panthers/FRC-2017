package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightForSetDistance extends Command {
	
	private Drive drive;
	private double distance;	//in inches
	
	public DriveStraightForSetDistance(double distance) {
		requires(Robot.drive);
		this.distance = distance;
	}
	
	@Override
	protected void initialize() {
		Robot.drive.stopMotors();
		Robot.drive.startDriveDistance(distance);
	}

	
	@Override
	protected void execute() {
		Robot.drive.autoDriveDistance();
	}

	@Override
	protected boolean isFinished() {
		return Robot.drive.isFinishedDrivingDistance(Robot.drive.encLeftMotor) && Robot.drive.isFinishedDrivingDistance(Robot.drive.encRightMotor);
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
