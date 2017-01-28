package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

/* 
 * @param magnitude is the magnitude of the vector of the joystick from the center. 
 * @param xy is a X or Y value usually obtained from getX() or getY()
 * 
 * The math for the radial deadzone is found at this website: 
 * http://www.third-helix.com/2013/04/12/doing-thumbstick-dead-zones-right.html
 * 
 * One thing to take notice is the (xy / magnitude) or normalized vector also known as the unit vector.
 * 
 * */

public class PantherJoystick extends Joystick{
	
	int joystickThrottleValue;
	public boolean goingForward;
	private JoystickType joystickType;
	
	public PantherJoystick(int port) {
		super(port);
		goingForward = true;
	}
	
	public double getScaledDeadzoneX() {
		return this.getAdjustedJoystickValue(this.getMagnitude(), this.getX() * Constants.X_SCALING, joystickType.deadzoneX);
	}
	
	public double getScaledDeadzoneY() {		
		double val = this.getAdjustedJoystickValue(this.getMagnitude(), this.getY() * Constants.Y_SCALING, Constants.DEADZONE_Y);
		if (goingForward) return val;
		return -val;
	}
	
	public double getMagnitude() {
		double xVal = this.getX();
		double yVal = this.getY();
		double magnitude = Math.sqrt(xVal * xVal + yVal * yVal);

		return magnitude;
	}
	
	public double getAdjustedJoystickValue(double magnitude, double xy, float deadzone) {
		if (magnitude < deadzone) {
			return 0;
		}
		else if(magnitude < 1){
			return (xy / magnitude) * ((magnitude - deadzone) / (1 - deadzone));
		}else{
			return magnitude;
		}
	}
	
	public void setJoystickType(JoystickType joystickType) {
		this.joystickType = joystickType;
	}
	/*
	public throttleMode() {
		joystickThrottlevalue = this.getThrottle();
	}
	*/

}
