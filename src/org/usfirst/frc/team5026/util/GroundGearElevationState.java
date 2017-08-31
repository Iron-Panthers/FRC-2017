package org.usfirst.frc.team5026.util;

public enum GroundGearElevationState {
	Legal(Constants.GROUND_GEAR_LEGAL_TICKS),
	Scoring(Constants.GROUND_GEAR_SCORING_TICKS),
	Lowered(Constants.GROUND_GEAR_LOWERED_TICKS);
	
	public int ticks;
	private GroundGearElevationState (int t) {
		ticks = t;
	}
}
