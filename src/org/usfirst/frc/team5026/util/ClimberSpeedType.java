package org.usfirst.frc.team5026.util;

public enum ClimberSpeedType {
	LATCH(Constants.CLIMBER_SPEED_LATCH, Constants.CLIMBER_SLOPE_LATCH), 		//use dis speed in command group
	WRAP(Constants.CLIMBER_SPEED_WRAP, Constants.CLIMBER_SLOPE_WRAP);
	
	public  double speed;
	public double curve;
	
	ClimberSpeedType(double speed, double curve) {
		this.speed = speed;
		this.curve = curve;
	}
}
