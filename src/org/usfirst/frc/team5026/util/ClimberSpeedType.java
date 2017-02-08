package org.usfirst.frc.team5026.util;

public enum ClimberSpeedType {
	LATCH(Constants.CLIMBER_SPEED_LATCH), 
	WRAP(Constants.CLIMBER_SPEED_WRAP), 
	FLOOR(Constants.CLIMBER_SPEED_FLOOR);
	
	public double speed;
	
	ClimberSpeedType(double speed) {
		this.speed = speed;
	}
}
