package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.SPI.Port;

public class Hardware {
	public Talon leftMotor;
	public Talon rightMotor;
	public Talon talonMotor;
	public Talon climbRightMotor;
	public Talon climbLeftMotor;
	public Gyro gyro;

	public Hardware() {
		leftMotor = new Talon(RobotMap.DRIVE_MOTOR_LEFT);
		rightMotor = new Talon(RobotMap.DRIVE_MOTOR_RIGHT);
		talonMotor = new Talon(RobotMap.TALON_MOTOR);
		climbRightMotor = new Talon(RobotMap.CLIMB_MOTOR_RIGHT);
		climbLeftMotor = new Talon(RobotMap.CLIMB_MOTOR_LEFT);
		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);

	}
}