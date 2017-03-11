package org.usfirst.frc.team5026.util;

import org.usfirst.frc.team5026.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Hardware {
	public CANTalon leftMotor_1;
	public CANTalon leftMotor_2; 
	public CANTalon leftMotor_3;
	public CANTalon rightMotor_1;
	public CANTalon rightMotor_2;
	public CANTalon rightMotor_3;
	public MotorGroup leftMotor;
	public MotorGroup rightMotor;
	private boolean[] invertedLeft = {false, false, false};
	private boolean[] invertedRight = {false, false, false};
	
	public Talon climberRightMotor;
	public Talon climberLeftMotor;
	
	public boolean climberLeftInverted = true;
	public boolean climberRightInverted = true;//comp:true,practice:false
	
	public Talon intake;
	
	public PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public DoubleSolenoid shifter;
	
	public Gyro gyro;
	
	public DoubleSolenoid gearClampPiston;
	public DigitalInput gearClampSensor;
	
	public LEDDisplay led;

	public Hardware() {
		// Drive Motors
		leftMotor_1 = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT_1);
		leftMotor_2 = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT_2);
		leftMotor_3 = new CANTalon(RobotMap.DRIVE_MOTOR_LEFT_3);
		rightMotor_1 = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT_1);
		rightMotor_2 = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT_2);
		rightMotor_3 = new CANTalon(RobotMap.DRIVE_MOTOR_RIGHT_3);
		leftMotor = new MotorGroup(invertedLeft, leftMotor_1, leftMotor_2, leftMotor_3);
		rightMotor = new MotorGroup(invertedRight, rightMotor_1, rightMotor_2, rightMotor_3);
		// Drive Excess
		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		shifter = new DoubleSolenoid(1,RobotMap.SOLENOID_SHIFTER_FORWARD,RobotMap.SOLENOID_SHIFTER_REVERSE);
		// Climber, Gyro, Gear, Intake
		climberRightMotor = new Talon(RobotMap.CLIMBER_MOTOR_RIGHT);
		climberLeftMotor = new Talon(RobotMap.CLIMBER_MOTOR_LEFT);
		gyro = new ADXRS450_Gyro(Port.kOnboardCS0);
		gearClampPiston = new DoubleSolenoid(1, RobotMap.gearPistonForward, RobotMap.gearPistonReverse);
		gearClampSensor = new DigitalInput(RobotMap.gearClampSensor);
		intake = new Talon(RobotMap.INTAKE_MOTOR);
		
		led = new LEDDisplay(RobotMap.CAN_LED_PORT);
	}
}