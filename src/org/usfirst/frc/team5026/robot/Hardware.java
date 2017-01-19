package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

public class Hardware {
	public Talon leftMotor;
	public Talon rightMotor;
	public Talon climbLeft;
	public Talon climbRight;

	public Hardware() {
		leftMotor = new Talon(RobotMap.driveMotorLeft);
		rightMotor = new Talon(RobotMap.driveMotorRight);
		
		climbLeft = new Talon(RobotMap.CLIMB_MOTOR_LEFT);
		climbRight = new Talon(RobotMap.CLIMB_MOTOR_RIGHT);
	}
}