package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

public class Hardware {
	
	public Spark leftMotor;
	public Spark rightMotor;
	public Joystick stick;
	
	public Hardware() {
		
		leftMotor = new Spark(RobotMap.leftMotor);
		rightMotor = new Spark(RobotMap.rightMotor);
		stick = new Joystick(RobotMap.stick);
	}
}
