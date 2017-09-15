package org.usfirst.frc.team5026.util.motionprofile;
/* The following class contains code for a MotionProfilePoint.
 * These points contain 3 parameters: x, y, theta, and velocity
 * 
 */
public class MotionProfileSegment {
	public double x;
	public double y;
	public double theta; // In radians!!!
	public double dt;
	public double vel;

//	public MotionProfileSegment(double x, double y, double theta) {
//		this.x = x;
//		this.y = y;
//		this.theta = theta;
////		this.dt = Constants.DELTA_TIME;
////		this.vel = 0;
//	}
	public MotionProfileSegment(double x, double y, double theta, double vel, double dt) {
		this.x = x;
		this.y = y;
		this.theta = theta;
		this.vel = vel;
		this.dt = dt;
	}
	public double distance(double x, double y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)); // Returns the distance between a point and a position
	}
	public String toString() {
		return "x:"+x+"y:"+y+"theta:"+theta+"dt:"+dt+"vel:"+vel;
	}
}
