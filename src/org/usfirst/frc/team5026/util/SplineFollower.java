package org.usfirst.frc.team5026.util;

public class SplineFollower {
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
}
