package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoystick extends Command {
	
	private PantherJoystick joystick;
	private RobotDrive drive;
	
	public DriveWithJoystick(PantherJoystick joystick) {
		requires(Robot.drive);
		this.joystick = joystick;
	}

	@Override
	protected void initialize() {
		Robot.drive.stopMotors();
		
	}

	@Override
	protected void execute() {
//		SmartDashboard.putNumber("JoyY",joystick.getScaledDeadzoneY());
		Robot.drive.useArcadeDrive(joystick.getScaledDeadzoneY(), -joystick.getScaledDeadzoneX());
	}

	@Override
	protected boolean isFinished() {
		return false;
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
