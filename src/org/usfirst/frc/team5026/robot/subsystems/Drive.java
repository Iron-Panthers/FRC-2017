package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.ArcadeDriveWithDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {

	private RobotDrive drive;

	public Drive() {
		drive = new RobotDrive(Robot.hardware.leftSide, Robot.hardware.rightSide);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ArcadeDriveWithDrive());
	}
	
	public void setMotorSpeed(double leftSpeed, double rightSpeed) {
		drive.setLeftRightMotorOutputs(leftSpeed, rightSpeed);
	}
	
	public void useArcadeDrive(Joystick stick) {
		drive.arcadeDrive(stick);
	}

	public void stopMotors() {
		setMotorSpeed(0,0);
	}
}