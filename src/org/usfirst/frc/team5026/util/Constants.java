package org.usfirst.frc.team5026.util;

public class Constants { 
	// Joystick Constants 
	//RED 
	public static final float X_SCALING_RED = 0.7f; 
	public static final float Y_SCALING_RED = 0.85f;  
	public static final float DEADZONE_X_RED = 0.0563f; 
	public static final float DEADZONE_Y_RED = 0.0512f; 
	public static final float DEADZONE_Y_MAX_RED = 0.829f; 

	//BLUE 
	public static final float X_SCALING_BLUE = 0.7f; 
	public static final float Y_SCALING_BLUE = 0.85f;  
	public static final float DEADZONE_X_BLUE = 0.0563f; 
	public static final float DEADZONE_Y_BLUE = 0.0512f; 
	public static final float DEADZONE_Y_MAX_BLUE = 0.829f; 

	//SPINNY 
	public static final float X_SCALING_SPINNY = 0.7f; 
	public static final float Y_SCALING_SPINNY = 0.85f;  
	public static final float DEADZONE_X_SPINNY = 0.0563f; 
	public static final float DEADZONE_Y_SPINNY = 0.0512f; 
	public static final float DEADZONE_Y_MAX_SPINNY = 0.829f; 

	// Gyro Constants 
	public static final double PERCENTAGE = 0.05;  
	public static final double SPEED = 0.5; 
	public static final double CLAMP_WAIT_TIME = 0.5; 
	   
	//Climber Constants
	public static final double CLIMBER_SPEED_RAPPEL = -0.2;	//quality of unloader's life
	public static final double CLIMBER_SPEED_LATCH = 0.3;	//Tested multiple times with driver, comfortable speed NEED INCREASE !!!!!TEST FRIDAY!!!!!!!
	public static final double CLIMBER_SPEED_WRAP = 0.71;	//Soft average, tested once, will need to be optimized a bit further
	public static final double CLIMBER_SLOPE_SWAP = 0.0;	//The value in which the joystick switches from linear to quadratic
	public static final double CLIMBER_SLOPE_WRAP = 0.0152;	//caps motors at 83.33% V (10/12 V), previously set at 0.0841 (capped at 100% V)
	public static final double CLIMBER_SLOPE_LATCH = 0.04;	//Graphed, not tested, to allow for slower climbing speeds
	
	public static final double CLIMBER_STALL_LIMIT = 5.0;	//The current(A) at which the climber motors stall.
} 