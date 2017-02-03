package org.usfirst.frc.team5026.robot;

public class Constants {
	// Joystick Constants
	public static final float X_SCALING = 0.7f;
	public static final float Y_SCALING = 0.85f; 
	public static final float DEADZONE_X = 0.0563f;
	public static final float DEADZONE_Y = 0.0512f;
	
	// Gyro Constants
	public static final double PERCENTAGE = 0.05; 
	public static final double SPEED = 0.5;
	public static final double CLAMP_WAIT_TIME = 0.5;
	
	//Climber Constants
	public static final double CLIMBER_RAPPEL_SPEED = -0.2;	//quality of unloader's life
	public static final double CLIMBER_SLOW_SPEED = 0.3;	
	public static final double CLIMBER_LATCH_SPEED = 0.3;	//Tested multiple times with driver, comfortable speed
	public static final double CLIMBER_WRAP_SPEED = 0.71;	//Soft average, tested once, will need to be optimized a bit further
	public static final double CLIMBER_CLIMB_SPEED = 1.0;
	public static final double CLIMBER_CURVE_SWAP = 0.0;	//The value in which the joystick switches from linear to quadratic
	public static final double CLIMBER_CURVE = 0.0841;		//Graphed and tested to generously comply to secondary driver
	
	public static final double CLIMBER_STALL_LIMIT = 75.0;	//The current(A) at which the climber motors stall.
}
