package org.usfirst.frc.team5026.util;
/* The following class contains code for a MotionProfilePoint.
 * These points contain 3 parameters: x, y, and theta
 * 
 */
public class MotionProfilePoint {
  public double x;
  public double y;
  public double theta;
  
  public MotionProfilePoint(double x, double y, double theta) {
    this.x = x;
    this.y = y;
    this.theta = theta;
  }
  public double distance(double x, double y) {
    return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2)); // Returns the distance between a point and a position
  }
}
