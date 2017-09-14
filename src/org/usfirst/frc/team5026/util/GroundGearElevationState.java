package org.usfirst.frc.team5026.util;

public enum GroundGearElevationState {
	Legal(Constants.GROUND_GEAR_LEGAL_TICKS),
	Scoring(Constants.GROUND_GEAR_SCORING_TICKS),
	DriveMode(Constants.GROUND_GEAR_DRIVEMODE_TICKS),
	Lowered(Constants.GROUND_GEAR_LOWERED_TICKS);
	
	public int ticks;
	public double rotations;
	private GroundGearElevationState (int t) {
		ticks = t;
		rotations = ticks / (Constants.ENCODER_TICKS_PER_ROTATION * 4.0); // 4.0 if quadature
	}
}
