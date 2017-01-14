package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDriveWithDrive extends Command{

	@Override
    // Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.stopMotors();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drive.useArcadeDrive(Robot.hardware.stick);
	}

	@Override
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	@Override
	// Called once after isFinished returns true
	protected void end() {
		
		Robot.drive.stopMotors();
	}

	@Override
	// Called when another command which requires one or more of the same
    // subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
