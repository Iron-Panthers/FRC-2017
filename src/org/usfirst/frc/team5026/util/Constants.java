package org.usfirst.frc.team5026.util;

public class Constants {
	// Joystick Constants
	public static final float X_SCALING = 0.75f;
	public static final float Y_SCALING = 0.85f; 
	public static final float DEADZONE_X = 0.0563f;
	public static final float DEADZONE_Y = 0.0512f;
	
	// Gyro Constants
	public static final double PERCENTAGE_FOR_ERROR = 0.05; 
	public static final double AUTO_TURN_SPEED = 0.1;
	public static final double CLAMP_WAIT_TIME = 0.5;
	
	// Encoder constants
	public static final double WHEEL_DIAMETER = 4;	//in inches
	public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
	public static final int ENCODER_TICKS_PER_ROTATION = 1024;
	
	// Auto Constants
	public static final double STRAIGHT_DRIVE_SPEED = 0.5;
	public static final double GEAR_RATIO = 5.36;
	// Auto Distances
	public static final double AUTO_MIDDLE_TARGET_LEFT = -6;
	public static final double AUTO_MIDDLE_TARGET_RIGHT = -6;
	
	// Drive motion profile
	public static final double DRIVE_STABILIZATION_TOLERANCE = 2000;
	public static final double DRIVE_STABILIZATION_COUNT_TOLERANCE = 50;
	
	public static final int PID_PROFILE_LEFT = 0;
	public static final double P_LEFT = 0.1;
	public static final double I_LEFT = 0;
	public static final double D_LEFT = 50;
	public static final double F_LEFT = 0;
	public static final double RAMP_LEFT = 15;
	public static final double[] PIDFR_LEFT = {P_LEFT,I_LEFT,D_LEFT,F_LEFT, RAMP_LEFT};
	
	public static final int PID_PROFILE_RIGHT = 0;
	public static final double P_RIGHT = 0.1;
	public static final double I_RIGHT = 0;
	public static final double D_RIGHT = 50;
	public static final double F_RIGHT = 0;
	public static final double RAMP_RIGHT = 15;
	public static final double[] PIDFR_RIGHT = {P_RIGHT,I_RIGHT,D_RIGHT,F_RIGHT, RAMP_RIGHT};
	
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
