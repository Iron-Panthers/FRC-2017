package org.usfirst.frc.team5026.util.motionprofile;

import org.usfirst.frc.team5026.util.Constants;

/*
 * Overall goal:
 * A planned series of points have been created. They each have three parameters: x,y,theta
 * The overall goal is to drive the robot using a MotionProfile through all of those points.
 * Right now, this is to be accomplished through the Talon SRX built-in PID/MP.
 * A useful source can be found here: https://www.chiefdelphi.com/forums/showthread.php?p=1675137
 * After constructing this curve, while attempting to drive along this curve, we begin by using our current velocity and position (avg of left and right) and we look ahead a fixed 'large' distance.
 * The distance should be chosen so that even when the robot is at maximum speed, the distance is reached in more than 500ms.
 * This allows for corrections to occur on the profile.
 * Next, the closest path point (from the original planned points) to the point found by the fixed distance in front of the robo is chosen.
 * Then, between the robot's current position and the waypoint (which has an x,y,theta) a continuous curve is created.
 * The curve can be constructed using the known start and end points, as well as the known start and end point velocity and orientation.
 * From there, a numerical arc length of the constructed curve can be calculated, allowing us to parameterize the complex curve (with three parameters) into 2: Position and time
 * Given the known positions of the start and end, and the known start and end velocity we can find the number of dt time segments along the curve.
 * For each time segment, we must find the left and right relatives (as all of these calculations used the center of the robot).
 * To do this, we extrapolate the distance between two points, or the current orientation, and perpendicularize it.
 * We then travel along the perpendicular curve for half the robot width in either direction, thus bringing us to the left and right points.
 * All of the left and right points are placed into left and right arrays of TrajectoryPoints, and using the known arc length of the curve, we can convert all points from x,y,t to simply: x,t.
 * The Talon SRX takes in parameters of type x,t. We fill the Talon SRX MP buffer with these points, then follow the curve until we reach our final point, or our fixed distance finds a new path point to target, which will then be added to the buffer.
 */
public class MotionProfileProcessor {
	public KinematicModel robot;
	public MotionProfilePath currentPath;
	public MotionProfileSmallCurve smallPath;
//	public MotionProfileSegment targetPoint;
	
	public MotionProfileProcessor(KinematicModel r) {
		robot = r;
	}
	public void setpath(MotionProfilePath path) {
		currentPath = path;
	}
	public MotionProfileSegment getPointClosestToDistance(double distance) {
		double[] c = robot.getCenter();
		double r = robot.getRotationInRadians();
//		double x = c[0] + distance * Math.sin(r * Math.PI / 180.0); // Converts to radians, finds distance forwards (using current robot heading)
//		double y = c[1] + distance * Math.cos(r * Math.PI / 180.0);
		double x = c[0] + distance * Math.sin(r);
		double y = c[1] + distance * Math.cos(r);
		return currentPath.getClosestPointOnPath(x, y);
	}
//	public void setTarget(MotionProfileSegment p) {
//		targetPoint = p;
//	}
	public MotionProfileSegment findClosestPoint() {
		double[] c = robot.getCenter();
		return currentPath.getClosestPointOnPath(c[0], c[1]);
	}
	public MotionProfileSegment findActualPosition() {
		double[] c = robot.getCenter();
		return new MotionProfileSegment(c[0], c[1], robot.getRotationInRadians(), robot.getVelocity(), Constants.DELTA_TIME);
	}
	public MotionProfileSmallCurve getPathForPoint(double distance) {
		MotionProfileSmallCurve spline = new MotionProfileSmallCurve();
		spline.constructSplines(getPointClosestToDistance(distance), findActualPosition());
		return spline;
	}
	public MotionProfileSmallCurve getPathForPoint(MotionProfileSegment s) {
		MotionProfileSmallCurve spline = new MotionProfileSmallCurve();
		spline.constructSplines(findActualPosition(), s);
		return spline;
	}
	public void setsmallpath(MotionProfileSmallCurve spline) {
		smallPath = spline;
	}
	
}
