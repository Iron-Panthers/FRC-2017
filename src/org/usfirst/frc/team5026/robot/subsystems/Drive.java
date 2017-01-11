package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.RobotMap;
import org.usfirst.frc.team5026.robot.commands.DriveWithJoystick;

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
		drive.setLeftRightMotorOutputs(left, right);
	}
	
	public void useArcadeDrive(double xAxis, double yAxis) {
		drive.arcadeDrive(xAxis, yAxis);
	}
	
	public void stopMotors() {
		this.setLeftRightMotors(0, 0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveWithJoystick(joystick));
	}
	
}
