package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class PantherJoystick extends Joystick{
	
	int joystickThrottleValue;
	
	public PantherJoystick(int port) {
		super(port);
	}
	
	
	public double getDeadzoneX() {
		return this.getAdjustedJoystickValue(this.getMagnitude(), this.getX());
	}
	
	public double getDeadzoneY() {		
		return this.getAdjustedJoystickValue(this.getMagnitude(), this.getY());
	}
	
	public double getMagnitude() {
		double xVal = this.getX();
		double yVal = this.getY();
		double magnitude = Math.sqrt(xVal * xVal + yVal * yVal);

		return magnitude;
	}
	
	public double getAdjustedJoystickValue(double magnitude, double xy) {
		if (magnitude < Constants.DEADZONE) {
			return 0;
		}
		else {
			return (xy / magnitude) * ((magnitude - Constants.DEADZONE) / (1 - Constants.DEADZONE));
		}
	}
	/*
	public throttleMode() {
		joystickThrottlevalue = this.getThrottle();
	}
	*/

}
