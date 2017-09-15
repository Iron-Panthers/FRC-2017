package org.usfirst.frc.team5026.util.motionprofile;

import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.DriveMotorGroup;

import com.ctre.CANTalon.TrajectoryPoint;

/*
 * In charge of pushing all of the points of the control to the Talon SRX
 */
public class MotionProfileBufferer {
	DriveMotorGroup left;
	DriveMotorGroup right;
	double fixedDistance;
	
//	public double[][] smallLeftPath; // ex: (2,4),(3,4); params are: (x,velocity) along the constructed curve to the target point
//	public double[][] smallCenterPath;
//	public double[][] smallRightPath;
	
	public TrajectoryPoint[] leftTickArray;
	public TrajectoryPoint[] rightTickArray;
	
	public MotionProfileBufferer(DriveMotorGroup l, DriveMotorGroup r, double distance) {
		left = l;
		right = r;
		fixedDistance = distance;
	}
	public void main(MotionProfileProcessor proc) {
		/*
		 * Steps:
		 * 1. Find the point
		 * 2. Construct the spline
		 * 3. Find Left and Right for splines
		 * 4. FEED TO TALON SRX
		 */
		
		
	}
	public void fillTalon(MotionProfileProcessor proc, DriveMotorGroup talon) {
		if (proc.currentPath == null) {
			// There is no path! Bad things are happenning!
			// TODO Add it so that multiple PATHS can be passed in
//			proc.setpath(new MotionProfilePath());
			return;
		}
		MotionProfileSegment end = proc.getPointClosestToDistance(fixedDistance);
		MotionProfileSegment start = proc.findActualPosition();
		MotionProfileSmallCurve spline = proc.getPathForPoint(end);
		spline.calculateLength();
		double velAvg = (start.vel + end.vel) / 2;
		double deltaV = (end.vel - start.vel) / 2;
		double time = spline.arc_length_ / velAvg; // Time unit is same unit as velocity unit
		int numSegments = (int)(time / Constants.DELTA_TIME);
		leftTickArray = new TrajectoryPoint[numSegments];
		rightTickArray = new TrajectoryPoint[numSegments];
		for (int i = 1; i <= numSegments; i++) {
			TrajectoryPoint LtPoint = new TrajectoryPoint();
			TrajectoryPoint RtPoint = new TrajectoryPoint();
			LtPoint.velocity = start.vel + deltaV * i;
			RtPoint.velocity = start.vel + deltaV * i; // Convert to rp100ms
			LtPoint.position = spline.valueAt((double)(i)/numSegments) - proc.robot.getWidth() / 2 + LtPoint.velocity * Math.sin(proc.robot.getRotationInRadians()); // Convert to rot
			RtPoint.position = spline.valueAt((double)(i)/numSegments) + proc.robot.getWidth() / 2 + RtPoint.velocity * Math.cos(proc.robot.getRotationInRadians());
			LtPoint.timeDurMs = (int)(Constants.DELTA_TIME * 1000); // Conversion from seconds to ms
			RtPoint.timeDurMs = (int)(Constants.DELTA_TIME * 1000);
			if (i == numSegments) {
				LtPoint.isLastPoint = true;
				RtPoint.isLastPoint = true;
			}
			leftTickArray[i-1] = LtPoint;
			rightTickArray[i-1] = RtPoint;
			left.getEncMotor().pushMotionProfileTrajectory(LtPoint);
			right.getEncMotor().pushMotionProfileTrajectory(RtPoint);
		}
		proc.setsmallpath(spline);
	}
	
}
