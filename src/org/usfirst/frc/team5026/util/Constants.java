package org.usfirst.frc.team5026.util;

public class Constants { 
	// Joystick Constants 
	// RED 
	public static final float X_SCALING_RED = 0.7f; 
	public static final float Y_SCALING_RED = 0.85f;  
	public static final float DEADZONE_X_RED = 0.0563f; 
	public static final float DEADZONE_Y_RED = 0.0512f; 
	public static final float DEADZONE_Y_MAX_RED = 0.829f; 

	// BLUE 
	public static final float X_SCALING_BLUE = 0.7f; 
	public static final float Y_SCALING_BLUE = 0.85f;  
	public static final float DEADZONE_X_BLUE = 0.0563f; 
	public static final float DEADZONE_Y_BLUE = 0.0512f; 
	public static final float DEADZONE_Y_MAX_BLUE = 0.829f; 

	// SPINNY 
	public static final float X_SCALING_SPINNY = 0.7f; 
	public static final float Y_SCALING_SPINNY = 0.85f;  
	public static final float DEADZONE_X_SPINNY = 0.0563f; 
	public static final float DEADZONE_Y_SPINNY = 0.0512f; 
	public static final float DEADZONE_Y_MAX_SPINNY = 0.829f; 

	// Gyro Constants 
	public static final double PERCENTAGE = 0.05;  
	public static final double SPEED = 0.5; 
	
	// Gear Constants
	public static final double CLAMP_WAIT_TIME = 0.5; 
	   
	//Climber Constants
	public static final double CLIMBER_SPEED_RAPPEL = -0.2;	//quality of unloader's life
	public static final double CLIMBER_SPEED_LATCH = 0.3;
	public static final double CLIMBER_SPEED_WRAP = 0.71;
	public static final double CLIMBER_SLOPE_SWAP = 0.0;	//The value in which the joystick switches from linear to quadratic
	public static final double CLIMBER_SLOPE_WRAP = 0.0152;	//caps motors at 83.33% V (10/12 V), previously set at 0.0841 (capped at 100% V)
	public static final double CLIMBER_SLOPE_LATCH = 0.04;	//allows for slower climbing speeds
	
	public static final double CLIMBER_STALL_LIMIT = 5.0;	//=[NEED TESTING]= The current(A) at which the climber motors stall.
	
	//Intake Constants
	public static final double INTAKE_INTAKE_SPEED = 0.5;
	public static final double INTAKE_OUTTAKE_SPEED = -0.5;
	
	//Encoder constants
	public static final double WHEEL_DIAMETER = 4;	//in inches
	public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
	public static final int ENCODER_TICKS_PER_ROTATION = 1024;
	
	//Auto Constants
	public static final String DRIVE_DISTANCE_RAMP_SMD_NAME_LEFT = "DriveRampDistance(inches) Left"; 
	public static final String DRIVE_DISTANCE_RAMP_SMD_NAME_RIGHT = "DriveRampDistance(inches) Right";
	public static final double STRAIGHT_DRIVE_SPEED = 0.5;
	public static final double GEAR_RATIO = 5.36;
	public static final double STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE = 0.90; // 0 < x < 1, At what point in the journey do we begin to slowdown?
	public static final double MOTOR_DEADZONE = 0.2; // Slowest possible motor speed that still moves the robot
	
} 
