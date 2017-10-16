package org.usfirst.frc.team5026.util;

import edu.wpi.first.wpilibj.interfaces.Gyro;

public class GyroTrapezoidMotionProfile {
	
	// Variables:
	/*
	 * Gyro
	 * ACCEL
	 * CRUISE_VEL
	 * P
	 * I
	 * D
	 * F
	 */
	Gyro gyro;
	double ACCELERATION;
	double VELOCITY;
	double[] pidf;
	TrapezoidMotionProfilePath path;
	
	double target;
	
	
	private double lastPosError;
	private double totalPosError;
	
	public GyroTrapezoidMotionProfile(Gyro gyro, double a, double v, double... pidf) {
		this.gyro = gyro;
		ACCELERATION = a;
		VELOCITY = v;
		if (pidf.length == 4) {
			this.pidf = pidf;
		}
		path = new TrapezoidMotionProfilePath(v,a);
	}
	public void set(double target) {
		this.target = target;
		path.set(target);
		path.calculateCurve();
		lastPosError = 0; // default
		totalPosError = 0;
	}
	public double getOut(double time) {
		double realPos = gyro.getAngle(); // This is deg
		double realVel = gyro.getRate(); // This is deg/s
		double posHat = path.getPos(time);
		double velHat = path.getVel(time);
		double currentPosError = realPos - posHat;
		double currentVelError = realVel - velHat;
		double p = pidf[0];
		double i = pidf[1];
		double d = pidf[2];
		double f = pidf[3];
		
		double out = p * (currentPosError) + i * (totalPosError) + d * (currentPosError - lastPosError) + f * (currentVelError);
		
		lastPosError = currentPosError;
		totalPosError += currentPosError;
		return out;
	}
}
