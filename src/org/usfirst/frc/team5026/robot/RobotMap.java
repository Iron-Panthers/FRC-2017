package org.usfirst.frc.team5026.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final int CLIMB_MOTOR_RIGHT = 0;
	public static final int CLIMB_MOTOR_LEFT = 1;

	
	public static final int DRIVE_MOTOR_LEFT_ENCODER = 1;
	public static final int DRIVE_MOTOR_LEFT_2 = 2;
	public static final int DRIVE_MOTOR_LEFT_3 = 3;
	public static final int DRIVE_MOTOR_RIGHT_ENCODER = 4;
	public static final int DRIVE_MOTOR_RIGHT_2 = 5;
	public static final int DRIVE_MOTOR_RIGHT_3 = 6;
	
	public static final int DRIVE_JOYSTICK = 0;
	public static final int BUTTON_JOYSTICK = 1;
	
	public static final int SOLENOID_SHIFTER_FORWARD = 0;
	public static final int SOLENOID_SHIFTER_REVERSE = 1;
	
	public static final int BOARD_BUTTON_1 = 1;
	public static final int BOARD_BUTTON_2 = 2;
	public static final int BOARD_BUTTON_3 = 3;
	public static final int BOARD_BUTTON_4 = 4;
	public static final int BOARD_BUTTON_5 = 5;
	public static final int BOARD_BUTTON_6 = 6;
	public static final int BOARD_BUTTON_7 = 7;
	
	public static final int gearPistonForward = 2;
	public static final int gearPistonReverse = 3;
}
	

