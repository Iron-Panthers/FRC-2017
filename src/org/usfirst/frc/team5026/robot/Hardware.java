package org.usfirst.frc.team5026.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

public class Hardware {

	public SpeedController leftDrive;
	public SpeedController rightDrive;
	
	public Talon climbLeft;
	public Talon climbRight;

	public Hardware() {
		
		CANTalon[] leftArray = {new CANTalon(1), new CANTalon(2), new CANTalon(3)};
		CANTalon[] rightArray = {new CANTalon(4), new CANTalon(5), new CANTalon(6)};
		boolean[] inverted = {true, false, false};
		
		leftDrive = new MotorGroup(leftArray, inverted);
		rightDrive = new MotorGroup(rightArray, inverted);
		
		climbLeft = new Talon(RobotMap.CLIMB_MOTOR_LEFT);
		climbRight = new Talon(RobotMap.CLIMB_MOTOR_RIGHT);
	}
}