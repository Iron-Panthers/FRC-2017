package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.ArcadeDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {

	private RobotDrive drive;
	public Drive() {
		drive = new RobotDrive(Robot.hardware.leftMotor, Robot.hardware.rightMotor);
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new ArcadeDrive());
	}
	
	private void setDefaultCommand(ArcadeDrive arcadeDrive) {
		// TODO Auto-generated method stub
		
	}

	public void useArcadeDrive(Joystick stick) {
		drive.arcadeDrive(Robot.hardware.stick.getY(), Robot.hardware.stick.getX());
	}
	
	public void setLeftRightMotors(double leftMotor, double rightMotor) {
		drive.setLeftRightMotorOutputs(leftMotor, rightMotor);
	}
	
	public void stopDriveMotors() {
		setLeftRightMotors(0, 0);
	}
}

