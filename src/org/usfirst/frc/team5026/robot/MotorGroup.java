package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.Spark;


public class MotorGroup {
	
	public Spark[] leftMotors;
	public Spark[] rightMotors;
	public boolean[] isInverted;
	
	public MotorGroup(Spark[] leftMotors, Spark[] rightMotors, boolean[] isInverted) {
		this.leftMotors = leftMotors;
		this.rightMotors = rightMotors;
		this.isInverted = isInverted;
	}
	
	public void pidWrite(double output) {
	}
	
	public double get() {
		return 0;
	}
	
	public void set(double speed) {
		// TODO Auto-generated method stub
		for (int i = 0; i < leftMotors.length; i++) {
			if (isInverted[i]) {
				leftMotors[i].set(-speed);
			}
			else {
				leftMotors[i].set(speed);
			}
		}
		for (int i = 0; i < rightMotors.length; i++) {
			if (isInverted[i]) {
				rightMotors[i].set(-speed);
			}
			else {
				rightMotors[i].set(speed);
			}
		}
	}
}