package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	private RobotDrive drive;
	
	private Joystick joystick;
	private Hardware hardware;
	
	public Drive() {
		joystick = new Joystick(RobotMap.driveJoystick);
		hardware = Robot.hardware;
		drive = new RobotDrive(hardware.leftMotor, hardware.rightMotor);
	}
	
	public void setLeftRightMotors(double left, double right) {
		hardware.leftMotor.set(left);
		hardware.rightMotor.set(right);
	}
	
	public void useArcadeDrive(double yAxis, double xAxis) {
		drive.arcadeDrive(yAxis, xAxis);
	}
	
	public void setJoystick(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}
	
	public void stopMotors() {
		hardware.leftMotor.set(0);
		hardware.rightMotor.set(0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
}
