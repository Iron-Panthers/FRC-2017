package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.SpeedController;


public class MotorGroup {
	
	public SpeedController[] motors;
	public boolean[] isInverted;
	
	public MotorGroup(SpeedController[] motors, boolean[] isInverted) {
		this.motors = motors;
		this.isInverted = isInverted;
	}
	
	public void pidWrite(double output) {
	}
	
	public double get() {
		return 0;
	}
	
	public void set(double speed) {
		// TODO Auto-generated method stub
		for (int i = 0; i < motors.length; i++) {
			if (isInverted[i]) {
				motors[i].set(-speed);
			}
			else {
				motors[i].set(speed);
			}
		}
	}
}