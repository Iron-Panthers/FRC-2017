package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Drive;

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
	}

	
	@Override
	protected void execute() {
		Robot.drive.driveStraightForDistance(distance, 0.7);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
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
