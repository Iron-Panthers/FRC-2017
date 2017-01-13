package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoystick extends Command {
	
	private PantherJoystick joystick;
	
	public DriveWithJoystick(PantherJoystick joystick) {
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
		SmartDashboard.putNumber("JoyX",joystick.getX());
		Robot.drive.useArcadeDrive(joystick.getDeadzoneY() * Constants.Y_SCALING, -joystick.getDeadzoneX() * Constants.X_SCALING);
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
