package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GearState;
import org.usfirst.frc.team5026.util.GroundGearElevationState;
import org.usfirst.frc.team5026.util.GroundGearIntakeState;
import org.usfirst.frc.team5026.util.Hardware;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GroundGear extends Subsystem implements GearState {

	private Hardware hardware;
	public GroundGearElevationState elevationState = GroundGearElevationState.Legal; // Starts in a legal position
	public GroundGearIntakeState intakeState = GroundGearIntakeState.Neutral; // Not sucking in when starting
	public boolean isOpen = false;
	
	
	public GroundGear() {
		hardware.groundGearLiftgroup.resetPosition();
		hardware = Robot.hardware;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void intakeGear() {
		// Should this be allowed to happen when in other states?
		if (elevationState == GroundGearElevationState.Lowered) {
			hardware.groundGearIntake.set(Constants.GROUND_GEAR_INTAKE_SPEED);
			//intakes a gear
			intakeState = GroundGearIntakeState.Intake;
		}
	}
	
	public void outtakeGear() {
		// Should this be allowed to happen when in other states?
		if (elevationState == GroundGearElevationState.Lowered) {
			hardware.groundGearIntake.set(Constants.GROUND_GEAR_OUTTAKE_SPEED);
			//outtakes a gear
			intakeState = GroundGearIntakeState.Outtake;
		}
	}
	
	public void travelToState(GroundGearElevationState targetState) {
		if (targetState == elevationState) {
			// We are already at that state
			return;
		}
		hardware.groundGearLiftgroup.positionControl(targetState.ticks); // Target ticks
	}
	public void setElevationState(GroundGearElevationState setState) {
		// BE CAREFUL WITH THIS METHOD, ONLY CALL AFTER MOVEMENT
		elevationState = setState;
	}
	public void lift() {
		hardware.groundGearLift.set(1.0);
		//lifts the gear intake
		isOpen = false;
	}
	public void lift(double s) {
		hardware.groundGearLift.set(s);
		isOpen = false; // unknwon
	}
	public void drop() {
		hardware.groundGearLift.set(1.0);
		//lowers the gear intake
		isOpen = true;
	}
	public void stopLift() {
		hardware.groundGearLift.set(0);
	}

}
