package org.usfirst.frc.team5026.util.motionprofile;

public class MotionProfileSmallCurve {
	// TODO: Fix KNUMSAMPLES

	double a_; // ax^5
	double b_; // + bx^4
	double c_; // + cx^3
	double d_; // + dx^2
	double e_; // + ex
	// f is always 0 for the spline formulation we support.
	// The offset from the world frame to the spline frame.
	// Add these to the output of the spline to obtain world coordinates.
	double y_offset_;
	double x_offset_;
	double knot_distance_;
	double theta_offset_;
	double arc_length_ = -1;
	
	public boolean constructSplines(MotionProfileSegment p1, MotionProfileSegment p2) {
		return reticulateSplines(p1.x, p1.y, p1.theta, p2.x, p2.y, p2.theta);
	}

	public boolean reticulateSplines(double x0, double y0, double theta0, double x1, double y1, double theta1) {
		System.out.println("Reticulating splines...");

		// Transform x to the origin
		x_offset_ = x0;
		y_offset_ = y0;
		double x1_hat = Math.sqrt((x1 - x0) * (x1 - x0) + (y1 - y0) * (y1 - y0));
		if (x1_hat == 0) { // If too close
			return false;
		}
		knot_distance_ = x1_hat;
		theta_offset_ = Math.atan2(y1 - y0, x1 - x0);
		double theta0_hat = getAngleDifferenceInRadians(theta_offset_, theta0);
		double theta1_hat = getAngleDifferenceInRadians(theta_offset_, theta1);
		// We cannot handle vertical slopes in our rotated, translated basis.
		// This would mean the user wants to end up 90 degrees off of the
		// straight
		// line between p0 and p1.
		if (almostEqual(Math.abs(theta0_hat), Math.PI / 2) || almostEqual(Math.abs(theta1_hat), Math.PI / 2)) {
			return false;
		}
		// We also cannot handle the case that the end angle is facing towards
		// the
		// start angle (total turn > 90 degrees).
		if (Math.abs(getAngleDifferenceInRadians(theta0_hat, theta1_hat)) >= Math.PI / 2) {
			return false;
		}
		// Turn angles into derivatives (slopes)
		double yp0_hat = Math.tan(theta0_hat);
		double yp1_hat = Math.tan(theta1_hat);

		// Calculate the cubic spline coefficients
		a_ = 0;
		b_ = 0;
		c_ = (yp1_hat + yp0_hat) / (x1_hat * x1_hat);
		d_ = -(2 * yp0_hat + yp1_hat) / x1_hat;
		e_ = yp0_hat;

		return true;
	}

	private boolean almostEqual(double l, double r) {
		return Math.abs(r - l) < 1E-6;
	}

	public double calculateLength() {
		if (arc_length_ >= 0) {
			return arc_length_;
		}
		final int kNumSamples = 100000;
		double arc_length = 0;
		double t, dydt;
		double integrand, last_integrand = Math.sqrt(1 + derivativeAt(0) * derivativeAt(0)) / kNumSamples;
		for (int i = 1; i <= kNumSamples; ++i) {
			t = ((double) i) / kNumSamples;
			dydt = derivativeAt(t);
			integrand = Math.sqrt(1 + dydt * dydt) / kNumSamples;
			arc_length += (integrand + last_integrand) / 2;
			last_integrand = integrand;
		}
		arc_length_ = this.knot_distance_ * arc_length;
		return arc_length_;
	}

	public double getPercentageFromDistance(double distance) {
		final int kNumSamples = 100000;
		double arc_length = 0;
		double t = 0;
		double last_arc_length = 0;
		double dydt;
		double integrand, last_integrand = Math.sqrt(1 + derivativeAt(0) * derivativeAt(0)) / kNumSamples;
		distance /= knot_distance_;
		for (int i = 1; i <= kNumSamples; ++i) {
			t = ((double) i) / kNumSamples;
			dydt = derivativeAt(t);
			integrand = Math.sqrt(1 + dydt * dydt) / kNumSamples;
			arc_length += (integrand + last_integrand) / 2;
			if (arc_length > distance) {
				break;
			}
			last_integrand = integrand;
			last_arc_length = arc_length;
		}

		// Interpolate between samples.
		double interpolated = t;
		if (arc_length != last_arc_length) {
			interpolated += ((distance - last_arc_length) / (arc_length - last_arc_length) - 1) / (double) kNumSamples;
		}
		return interpolated;
	}

	public double[] getXandY(double percentage) {
		double[] result = new double[2];

		percentage = Math.max(Math.min(percentage, 1), 0);
		double x_hat = percentage * knot_distance_;
		double y_hat = (a_ * x_hat + b_) * x_hat * x_hat * x_hat * x_hat + c_ * x_hat * x_hat * x_hat
				+ d_ * x_hat * x_hat + e_ * x_hat;

		double cos_theta = Math.cos(theta_offset_);
		double sin_theta = Math.sin(theta_offset_);

		result[0] = x_hat * cos_theta - y_hat * sin_theta + x_offset_;
		result[1] = x_hat * sin_theta + y_hat * cos_theta + y_offset_;
		return result;
	}

	public double valueAt(double percentage) {
		percentage = Math.max(Math.min(percentage, 1), 0);
		double x_hat = percentage * knot_distance_;
		double y_hat = (a_ * x_hat + b_) * x_hat * x_hat * x_hat * x_hat + c_ * x_hat * x_hat * x_hat
				+ d_ * x_hat * x_hat + e_ * x_hat;

		double cos_theta = Math.cos(theta_offset_);
		double sin_theta = Math.sin(theta_offset_);

		double value = x_hat * sin_theta + y_hat * cos_theta + y_offset_;
		return value;
	}

	public double derivativeAt(double percentage) {
		percentage = Math.max(Math.min(percentage, 1), 0);

		double x_hat = percentage * knot_distance_;
		double yp_hat = (5 * a_ * x_hat + 4 * b_) * x_hat * x_hat * x_hat + 3 * c_ * x_hat * x_hat + 2 * d_ * x_hat
				+ e_;

		return yp_hat;
	}
	
	public double[] leftAndRightAt(double percentage) {
		// Theta prediction should be used here to do this properly... Hard to determine perp. velocity vector for left and right positions
		double[] vals = getXandY(percentage);
		for (int i = 0; i < vals.length; i++) {
			vals[i] += i % 2 == 0 ? 1: -1 * derivativeAt(percentage);
		}
		// This actually DOESN'T work!!! TODO
		return vals;
	}

	private double secondDerivativeAt(double percentage) {
		percentage = Math.max(Math.min(percentage, 1), 0);

		double x_hat = percentage * knot_distance_;
		double ypp_hat = (20 * a_ * x_hat + 12 * b_) * x_hat * x_hat + 6 * c_ * x_hat + 2 * d_;

		return ypp_hat;
	}

	public String toString() {
		return "a=" + a_ + "; b=" + b_ + "; c=" + c_ + "; d=" + d_ + "; e=" + e_;
	}

	public double getAngleDifferenceInRadians(double angle1, double angle2) {
		double angle = angle1 - angle2;
		angle = angle / 180.0 * Math.PI;
		// Naive algorithm
		while (angle >= Math.PI) {
			angle -= 2.0 * Math.PI;
		}
		while (angle < -Math.PI) {
			angle += 2.0 * Math.PI;
		}
		return angle;
	}

}
