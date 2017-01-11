package org.usfirst.frc.team5026.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Drive;

/**
 *
 */
public class DriveWithJoystick extends Command {
	
	private Joystick joystick;
	
	public DriveWithJoystick(Joystick joystick) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drive);
		this.joystick = joystick;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drive.stopMotors();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drive.useArcadeDrive(joystick.getY(), joystick.getX());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
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
