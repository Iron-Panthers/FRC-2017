package org.usfirst.frc.team5026.util;

public class Constants {
	// Joystick Constants
	public static final float X_SCALING = 0.75f;
	public static final float Y_SCALING = 0.85f; 
	public static final float DEADZONE_X = 0.0563f;
	public static final float DEADZONE_Y = 0.0512f;
	
	// Gyro Constants
	public static final double PERCENTAGE = 0.05; 
	public static final double SPEED = 0.5;
	public static final double CLAMP_WAIT_TIME = 0.5;
	
	//Encoder constants
	public static final double WHEEL_DIAMETER = 4;	//in inches
	public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
	public static final int ENCODER_TICKS_PER_ROTATION = 1024;
	
	//Auto COnstants
	public static final String DRIVE_DISTANCE_RAMP_SMD_NAME = "DriveRampDistance(inches)"; 
	public static final double STRAIGHT_DRIVE_SPEED = 0.5;
	public static final double GEAR_RATIO = 5.36;
}
