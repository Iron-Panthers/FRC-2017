package org.usfirst.frc.team5026.util;

/* The following code contains the construction of a profile path, where points have known: position, velocity, and orientation
 * The path has a 'closest point' function, where given the current point, and the target point, finds the closest point to the curve based on current velocity.
 * Please see this CD thread for more details: https://www.chiefdelphi.com/forums/showthread.php?p=1675137
 */

public class MotionProfilePath {
  public MotionProfilePoint[] points; // Keeps a list of all points
  public int currentIndex = 0; // Keeps track of the current profile point targetted (saves processing speed long term)
  
  public MotionProfilePath (MotionProfilePoint... p) {
    points = p;
  }
  public MotionProfilePoint getClosestPointOnPath(double targetX, double targetY) {
    double lastDistance = 10000; // Sets the last distance to a large number initially.
    MotionProfilePoint targetPoint; // Uses MotionProfilePoint as a targetPoint
    for (int i = currentIndex; i < points.length; i++) {
      // Iterates over all points to determine closest point to the path.
      double dist = points[i].distance(targetX, targetY); // Distance formula
      if (dist > lastDistance) {
        // Our distance has INCREASED, not DECREASED from our target. Break and use lastDistance
        break;
      }
      lastDistance = dist;
      targetPoint = points[i];
    }
    return targetPoint; // Returns the targetPoint from shortest distance away
  }
  public void updateCurrentIndex() {
    currentIndex++; // Updates currentIndex after a point been successfully travelled to. In this way, by starting at 0 we include the current point as well.
  }
}
