package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.SPI.Port;

public class Hardware {
	public Spark leftMotor;
	public Spark rightMotor;
	public Talon talonMotor;
	public Gyro gyro;

	public Hardware() {
		leftMotor = new Spark(RobotMap.DRIVE_MOTOR_LEFT);
		rightMotor = new Spark(RobotMap.DRIVE_MOTOR_RIGHT);
		talonMotor = new Talon(RobotMap.TALON_MOTOR);
		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);

	}
}