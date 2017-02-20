package org.usfirst.frc.team5026.util;

public class AutoAlign {
	public AutoAlign() {
		
	}
	public double getDistanceFromPeg() {
		// TODO
		/*
		 * Returns the distance from the peg
		 * May or may not take into account the angle of the peg relative to the robot
		 */
		return 0;
	}
	public boolean checkIfPossible() {
		// TODO
		/*
		 * if target is too close, return false
		 * else return true.
		 * Requires testing
		 */
		return false;
	}
	public double getAngleOfPeg() {
		// TODO
		/*
		 * Returns the angle of the peg based off of the two targets.
		 * Should take into account the area difference, and the size difference.
		 */
		return 0;
	}
	public double getAngleToTurn() {
		// TODO
		/*
		 * assuming the target is NOT too close:
		 * return the angle at which to turn, based off of the target.
		 * This is for L shaped auto aligning.
		 * Requires the angle of the peg, relative to the robot.
		 */
		return 0;
	}
	public void isStraight() {
		// TODO
		/*
		 * Returns true if the target is directly straight ahead.
		 * Otherwise, return false
		 */
	}
	public double getDistanceToTravel() {
		// TODO
		/*
		 * Return the distance to travel based off of how far to the left or the right the target is
		 * If straight, give the distance to travel straight
		 * 
		 */
		return 0;
	}
	// TODO
	/*
	 * AT SOME POINT THE PLAN WILL BE TO RETURN A POINT IN 2D SPACE WHERE THE ROBOT SHOULD END UP
	 * AND THE ORIENTATION FOR IT
	 * EX: {10, 5, 45} WOULD BE THE ENDING POSITION FOR THE ROBOT, RELATIVE TO THE CURRENT POSITION
	 * {INCHES, INCHES, DEGREES}
	 * {0,0,0} IS CURRENT POS
	 * WITH THAT, CONVERT IT TO BE EASY TO MANUEVER TO
	 */
	public double[] mysteryFunction() {
		double[] out = new double[3];
		// TODO
		/*
		 * Do the above!
		 * Convert a target into {inches X, inches Y, final angle}
		 * Y			TRGT
		 * |				theta:final angle
		 * RBOT ---------X
		 */
		return out;
	}
}
