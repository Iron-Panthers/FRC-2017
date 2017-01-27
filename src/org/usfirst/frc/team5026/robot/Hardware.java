package org.usfirst.frc.team5026.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Hardware {
	public CANTalon leftMotor_1;
	public CANTalon leftMotor_2; 
	public CANTalon leftMotor_3;
	public CANTalon rightMotor_4;
	public CANTalon rightMotor_5;
	public CANTalon rightMotor_6;
	public Talon climbRightMotor;
	public Talon climbLeftMotor;
	public DoubleSolenoid shifter;
	
	public Gyro gyro;
	
	public DoubleSolenoid leftClampPiston;
	public DoubleSolenoid rightClampPiston;
	public DigitalInput gearClampSensor;

	public Hardware() {
		leftMotor_1 = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT);
		leftMotor_2 = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT);
		leftMotor_3 = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT);
		rightMotor_4 = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT);
		rightMotor_5 = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT);
		rightMotor_6 = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT);
		climbRightMotor = new Talon(RobotMap.CLIMB_MOTOR_RIGHT);
		climbLeftMotor = new Talon(RobotMap.CLIMB_MOTOR_LEFT);
		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		shifter = new DoubleSolenoid(1,0,1);
	}
}