package org.usfirst.frc.team5026.util;
// Provides an interface for a KinematicMode of a robot. Forces the robot to provide implementation of getCenter() and getRotation()
public interface KinematicModel {
	public double[] getCenter(); // Method for the center of the robot
	public double getRotation(); // Method for the rotation of the robot
}
