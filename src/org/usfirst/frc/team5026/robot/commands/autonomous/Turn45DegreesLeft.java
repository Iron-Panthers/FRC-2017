package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

public class Turn45DegreesLeft extends Command {

	private Drive drive;
	
	public Turn45DegreesLeft() {
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.drive.setRotate(-45);
	}
	
	protected void execute() {
		Robot.drive.rotateRobot(Constants.SPEED);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Robot.drive.isTurnFinished();
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
