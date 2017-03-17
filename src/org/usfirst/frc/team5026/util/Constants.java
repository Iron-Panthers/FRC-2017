package org.usfirst.frc.team5026.util;

public class Constants { 
	// Joystick Constants 
	//RED 
	public static final float X_SCALING_RED = 0.7f; 
	public static final float Y_SCALING_RED = 0.85f;  
	public static final float DEADZONE_X_RED = 0.0563f; 
	public static final float DEADZONE_Y_RED = 0.0512f; 
	public static final float DEADZONE_Y_MAX_RED = 0.829f; 
	public static final float DEADZONE_BOWTIE_SCALING_RED = 0.03f; 

	//BLUE 
	public static final float X_SCALING_BLUE = 0.7f; 
	public static final float Y_SCALING_BLUE = 0.85f;  
	public static final float DEADZONE_X_BLUE = 0.0563f; 
	public static final float DEADZONE_Y_BLUE = 0.0512f; 
	public static final float DEADZONE_Y_MAX_BLUE = 0.829f; 
	public static final float DEADZONE_BOWTIE_SCALING_BLUE = 0.03f; 

	//SPINNY 
	public static final float X_SCALING_SPINNY = 0.7f; 
	public static final float Y_SCALING_SPINNY = 0.85f;  
	public static final float DEADZONE_X_SPINNY = 0.0563f; 
	public static final float DEADZONE_Y_SPINNY = 0.0512f; 
	public static final float DEADZONE_Y_MAX_SPINNY = 0.829f;
	public static final float DEADZONE_BOWTIE_SCALING_SPINNY = 0.03f; 

	//Gyro Constants 
	public static final double PERCENTAGE = 0.05;  
	public static final double SPEED = 0.5; 
	   
	//Climber Constants
	public static final double CLIMBER_SPEED_RAPPEL = -0.2;	//quality of unloader's life
	public static final double CLIMBER_SPEED_LATCH = 0.3;
	public static final double CLIMBER_SPEED_WRAP = 0.71;
	public static final double CLIMBER_SLOPE_SWAP = 0.0;	//The value in which the joystick switches from linear to quadratic
	public static final double CLIMBER_SLOPE_WRAP = 0.0152;	//caps motors at 83.33% V (10/12 V), previously set at 0.0841 (capped at 100% V)
	public static final double CLIMBER_SLOPE_LATCH = 0.04;	//allows for slower climbing speeds
	
	public static final double CLIMBER_STALL_LIMIT = 5.0;	//=[NEED TESTING]= The current(A) at which the climber motors stall.
	
	//Intake Constants
	public static final double INTAKE_INTAKE_SPEED = 1;
	public static final double INTAKE_OUTTAKE_SPEED = -1;
	
	//LED Constants
	public static final double LED_TIME_DEFAULT = 0.25;
	public static final int LED_SHIFT_INDEX = 0;
	public static final int LED_DRIVE_INDEX = 1;
	public static final Color LED_SHIFT_HIGH = Color.RED;
	public static final Color LED_SHIFT_LOW = Color.BLUE;
	public static final Color LED_DRIVE_FORWARD = Color.DEEP_MAGENTA;
	public static final Color LED_DRIVE_REVERSE = Color.GOLD;
	public static final Color LED_GEAR_RELEASE = Color.GREEN;
	
	// Gyro Constants
	public static final double PERCENTAGE_FOR_ERROR = 0.05; 
	public static final double AUTO_TURN_SPEED = 0.1;
	
	// Encoder constants
	public static final double WHEEL_DIAMETER = 4;	//in inches
	public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
	public static final int ENCODER_TICKS_PER_ROTATION = 1024;
	
	// Auto Constants
	public static final double STRAIGHT_DRIVE_SPEED = 0.5;
	public static final double GEAR_RATIO = 5.36;
	/*
	 * For auto distances:
	 * Line left laser (gear is front) with the far edge of the tape for both the loading zone and the boiler
	 * The angle for the banner sensor is the same.
	 * Line right laser with bottom right of airship
	 * 
	 * After doing this, and being sure the angles are correct, optimize auto modes to work from those positions.
	 */
	
	// Auto Distances
	public static double AUTO_MIDDLE_TARGET_LEFT = -9.1;
	public static double AUTO_MIDDLE_TARGET_RIGHT = -9.1;
	
	//DriveCarveToPegFromBoiler
	public static double AUTO_BOILER_TARGET_FAR = -12.9;
	public static double AUTO_BOILER_TARGET_CLOSE = -9.1;
	public static double AUTO_BOILER_AFTER_TURN_TO_PEG = -3.5;
	
	//DriveCarveToPegFromLoadingZone
	public static double AUTO_LOADING_TARGET_FAR = -12.8;
	public static double AUTO_LOADING_TARGET_CLOSE = -9.1;
	public static double AUTO_LOADING_AFTER_TURN_TO_PEG = -3.5;
	
	// Drive motion profile
	public static double DRIVE_STABILIZATION_TOLERANCE = 2000;
	public static double DRIVE_STABILIZATION_COUNT_TOLERANCE = 50;
	
	public static int PID_PROFILE_LEFT = 0;
	public static double P_LEFT = 0.1;
	public static double I_LEFT = 0;
	public static double D_LEFT = 65;
	public static double F_LEFT = 0;
	public static double RAMP_LEFT = 15;
	public static double[] PIDFR_LEFT = {P_LEFT,I_LEFT,D_LEFT,F_LEFT, RAMP_LEFT};
	
	public static int PID_PROFILE_RIGHT = 0;
	public static double P_RIGHT = 0.1;
	public static double I_RIGHT = 0;
	public static double D_RIGHT = 65;
	public static double F_RIGHT = 0;
	public static double RAMP_RIGHT = 15;
	public static double[] PIDFR_RIGHT = {P_RIGHT,I_RIGHT,D_RIGHT,F_RIGHT, RAMP_RIGHT};
	
	public static double TELEOP_RAMP_RIGHT = 0;
	public static double TELEOP_RAMP_LEFT = 0;
	
	public static final double MAX_ACCELERATION = 500;
	/*
	 * Unit is encoder rotations/sec/sec.
	 * measured (unloaded): 
	 * measured (loaded, on field): 
	 */
	public static final double MAX_VELOCITY = 1080; 
	/*
	 * Unit is encoder rotations/sec.
	 * measured (unloaded): 1834
	 * measured (loaded, on field): 
	 */
	
}