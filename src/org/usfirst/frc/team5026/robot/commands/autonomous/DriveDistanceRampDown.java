package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class DriveDistanceRampDown extends Command {
	
	private double ramp = 0;
	private double speed;
	private double inc = 0.01;
	
	public DriveDistanceRampDown() {
		requires(Robot.drive);
		speed = Constants.STRAIGHT_DRIVE_SPEED;
	}
	public DriveDistanceRampDown(double speed) {
		requires(Robot.drive);
		this.speed = speed;
	}
	
	@Override
	protected void initialize() {
	}

	
	@Override
	protected void execute() {
		Robot.drive.driveStraight(speed * ramp);
		ramp -= inc;
	}

	@Override
	protected boolean isFinished() {
		return ramp <= 0;
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
