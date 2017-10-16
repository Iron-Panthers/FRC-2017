package org.usfirst.frc.team5026.util;

public class TrapezoidMotionProfilePath {
	
	double target;
	double velocity;
	double acceleration;
	
	double t1;
	double t2;
	
	double dx1;
	double dx2;
	double dx3;
	
	public TrapezoidMotionProfilePath(double velocity, double acceleration) {
		this.velocity = velocity;
		this.acceleration = acceleration;
	}
	public void set(double target) {
		this.target = target;
	}
	public void calculateCurve() {
		if (target != 0) {
			t1 = velocity / acceleration;
			dx1 = 0.5 * acceleration * Math.pow(t1, 2);
			if (dx1 > target / 2) {
				// This curve is too fast!
				dx1 = target / 2;
				t1 = Math.sqrt((2 * dx1) / acceleration); // using x = 0.5at^2
			}
			dx3 = 0.5 * acceleration * Math.pow(t1, 2);
			dx2 = target - dx3 - dx1;
			
			t2 = dx2 / velocity;
		}
	}
	public double getPos(double time) {
		// Time is in the same units as accel and vel
		double out = 0;
		if (0 <= time && time <= t1) {
			out = 0.5 * acceleration * Math.pow(time, 2);
		}
		if (t1 <= time && time <= t1 + t2) {
			out = velocity * time + dx1;
		}
		if (t1 + t2 <= time && time <= 2 * t1 + t2) {
			out = -0.5 * acceleration * Math.pow(time, 2) + dx1 + dx2;
		}
		return out;
	}
	public double getVel(double time) {
		double out = 0;
		if (0 <= time && time <= t1) {
			out = acceleration * time;
		}
		if (t1 <= time && time <= t1+ t2) {
			out = velocity;
		}
		if (t1 + t2 <= time && time <= 2 * t1 + t2) {
			out = -acceleration * time;
		}
		return out;
	}
	public String toString() {
		String out = "";
		out += "dX1: "+dx1+"\n";
		out += "dX2: "+dx2+"\n";
		out += "dX3: "+dx3+"\n";
		out += "t1: "+t1+"\n";
		out += "t2: "+t2+"\n";
		return out;
	}
}
// Examples:
/*
Target: 6, Vel: 3, A: 1
dX1: 3.0
dX2: 4.440892098500626E-16
dX3: 2.9999999999999996
t1: 2.449489742783178
t2: 1.4802973661668753E-16

Target: 13, Vel: 3, A: 1
dX1: 4.5
dX2: 4.0
dX3: 4.5
t1: 3.0
t2: 1.3333333333333333
*/