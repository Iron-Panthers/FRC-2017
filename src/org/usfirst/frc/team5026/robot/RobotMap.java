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
	
	public static final int CLIMB_MOTOR_RIGHT = 3;
	public static final int CLIMB_MOTOR_LEFT = 4;

	
	public static final int DRIVE_MOTOR_LEFT = 0;
	public static final int DRIVE_MOTOR_RIGHT = 1;
	
	public static final int DRIVE_JOYSTICK = 1;
	public static final int BUTTON_JOYSTICK = 2;
	
	public static final int TALON_MOTOR = 2;
	
	public static final int BOARD_BUTTON_1 = 2;
	public static final int BOARD_BUTTON_2 = 3;
	public static final int BOARD_BUTTON_3 = 4;
	public static final int BOARD_BUTTON_4 = 5;
	public static final int BOARD_BUTTON_5 = 6;
	public static final int BOARD_BUTTON_6 = 7;
	public static final int BOARD_BUTTON_7 = 8;

	
	public static final int TALON_SWITCH = 1;
	
	public static int gearBannerSensor = 1;
	public static int rightPistonReverse = 5;
	public static int rightPistonForward = 4;
	public static int leftPistonReverse = 3;
	public static int leftPistonForward = 2;
}
	

