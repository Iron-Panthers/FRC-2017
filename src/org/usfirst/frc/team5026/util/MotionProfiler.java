package org.usfirst.frc.team5026.util;

public class MotionProfiler {
	/*
	setpoint = lookupSetpoint(time);
	error = setpoint.position - actualPosition;
	errorDeriv = (error - lastError) / dt - setpoint.velocity;
	setSpeed(Kp * error + Kd * errorDeriv);
	lastError = error;
	 */
	/*
	 * prerequisites:
	 * Run at full speed and graph x v t
	 * Record max V
	 * Record max A
	 * Kv = 1 / max V
	 * Create a motion profile with ONLY Kv
	 * Adjust Ka until it works!
	 * Kp first, usually no Kd
	 * Increase Kp until oscillation, then back off.
	 * To find Kp and Kd, graph the set points from the spline vs. actual points in reality. As they get closer and closer, Kp is better and better
	 * https://docs.google.com/presentation/d/1xjtQ5m3Ay4AYxS_SfloF2n_vWZnCU25aXZuu9A59xPY/pub?start=false&loop=false&delayms=3000&slide=id.g999d23777_0_33
	 * Library for creation of spline curves: https://github.com/Team254/TrajectoryLib
	 * NOTICE THAT THE OUTPUT FORMULAS FOR THIS IS NOT WHAT WE DESIRE, THIS IS MORE DEPENDENT ON OTHER THINGS!
	 */
	public static final double MAX_ACCELERATION = 1000; // tick/s/s
	public static final double MAX_VELOCITY = 1000; // tick/s
	public static final double Kv = 1 / MAX_VELOCITY; // s/tick
	public static double target; // ticks
	
	public static double getAccelTime() {
		return MAX_VELOCITY / MAX_ACCELERATION;
	}
	public static double getAccelDistance() {
		return 0.5 * MAX_ACCELERATION * getAccelTime() * getAccelTime();
	}
	public static double getDeccelDistance() {
		return MAX_VELOCITY * getAccelTime() - 0.5 * MAX_ACCELERATION * getAccelTime() * getAccelTime();
	}
	public static double getMaxVelocityTime() {
		return (target - getAccelDistance() - getDeccelDistance()) / MAX_VELOCITY;
	}
	public static double getAccelVelocity(double time) {
		return MAX_ACCELERATION * time;
	}
	public static double getDeccelVelocity(double time) {
		return MAX_VELOCITY - MAX_ACCELERATION * time;
	}
	public static double getVelocity(double time) {
		if (time <= getAccelTime()) {
			return getAccelVelocity(time);
		}
		else if (time <= getAccelTime() + getMaxVelocityTime()) {
			return MAX_VELOCITY;
		}
		else if (time <= getTotalTimeToDistance()) {
			return getDeccelVelocity(time - (getAccelTime() + getMaxVelocityTime()));
		}
		else {
			return 0;
		}
	}
	public static double getTotalTimeToDistance() {
		return 2 * getAccelTime() + getMaxVelocityTime();
	}
	public static double getVoltageToApply(double time) {
		return getVelocity(time) * Kv; // Kp and Kd
	}
}
