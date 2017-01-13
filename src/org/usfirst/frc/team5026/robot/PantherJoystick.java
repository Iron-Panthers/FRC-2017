package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class PantherJoystick extends Joystick{
	
	int joystickThrottleValue;
	
	public PantherJoystick(int port) {
		super(port);
	}
	
	
	public double getDeadzoneX() {
		

		double newX = 0;

		newX = this.getDeadzoneMagnitude(Constants.DEADZONE) * Math.cos(this.getDirectionDegrees());
		return newX;
	}
	
	public double getDeadzoneY() {
		
		double newY = 0;
		
		newY = this.getDeadzoneMagnitude(Constants.DEADZONE) * Math.sin(this.getDirectionDegrees());
		return newY;
	}
	
	public double getDeadzoneMagnitude(float deadzone) {
		double xVal = this.getX();
		double yVal = this.getY();
		double magnitude = Math.sqrt(xVal * xVal + yVal * yVal);
		
		if(magnitude < deadzone) {
			xVal = 0;
			yVal = 0;
		}
		else {
			magnitude = (magnitude - deadzone) / (1 - deadzone);
		}
		return magnitude;
	}
	/*
	public throttleMode() {
		joystickThrottlevalue = this.getThrottle();
	}
	*/

}
