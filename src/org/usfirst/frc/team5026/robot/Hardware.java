package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class Hardware {
	public Spark leftMotor;
	public Spark rightMotor;
	public Talon talonMotor;

	public Hardware() {
		leftMotor = new Spark(RobotMap.DRIVE_MOTOR_LEFT);
		rightMotor = new Spark(RobotMap.DRIVE_MOTOR_RIGHT);
		talonMotor = new Talon(RobotMap.TALON_MOTOR);

	}
}