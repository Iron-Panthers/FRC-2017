package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;

public class Hardware {
	public Spark leftMotor;
	public Spark rightMotor;
	
	public DoubleSolenoid shifter; // pls init pls i duno hao

	public Hardware() {
		leftMotor = new Spark(RobotMap.DRIVE_MOTOR_LEFT);
		rightMotor = new Spark(RobotMap.DRIVE_MOTOR_RIGHT);
		
		// something something new doublesolenoid
	}
}