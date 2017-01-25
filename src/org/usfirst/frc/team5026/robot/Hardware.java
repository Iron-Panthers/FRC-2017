package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class Hardware {
	public Spark leftMotor;
	public Spark rightMotor;
	public DoubleSolenoid leftClampPiston;
	public DoubleSolenoid rightClampPiston;
	public DigitalInput gearClampSensor;

	public Hardware() {
		leftMotor = new Spark(RobotMap.driveMotorLeft);
		rightMotor = new Spark(RobotMap.driveMotorRight);
		leftClampPiston = new DoubleSolenoid(RobotMap.leftPistonForward, RobotMap.leftPistonReverse);
		rightClampPiston = new DoubleSolenoid(RobotMap.rightPistonForward, RobotMap.rightPistonReverse);
		gearClampSensor = new DigitalInput(RobotMap.gearBannerSensor);
	}
}