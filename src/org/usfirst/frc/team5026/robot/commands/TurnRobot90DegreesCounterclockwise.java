package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRobot90DegreesCounterclockwise extends Command{
	
	private double target;
	
	public TurnRobot90DegreesCounterclockwise(double target) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		this.target = target;
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drive.setRotate(-target);
	}

	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.rotateRobot(Constants.SPEED);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.drive.isTurnFinished();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drive.stopMotors();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.drive.stopMotors();
	}

}
