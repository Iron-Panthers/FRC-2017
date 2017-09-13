package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.groundgear.GroundGearStop;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GearOpenable;
import org.usfirst.frc.team5026.util.GroundGearElevationState;
import org.usfirst.frc.team5026.util.GroundGearIntakeState;
import org.usfirst.frc.team5026.util.Hardware;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GroundGear extends GearOpenable {

	private Hardware hardware;
	public GroundGearElevationState elevationState; 
	public GroundGearIntakeState intakeState; 
	
	
	public GroundGear() {
		hardware = Robot.hardware;
		hardware.groundGearLiftgroup.resetPosition();
		hardware.groundGearLiftgroup.setProfile(1, Constants.GROUND_GEAR_SCORING_PIDFRNAV); // Sets up scoring profile
		hardware.groundGearLiftgroup.setProfile(0); // Makes sure we are on the correct profile as to not override scoring profile
		setup();
		
		setElevationState(GroundGearElevationState.Legal); // Starts in a legal position
		setIntakeState(GroundGearIntakeState.Neutral); // Not sucking in when starting
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// This might be too redundant
//		setDefaultCommand(new GroundGearStop());
	}
	
	public void intakeGear() {
		// Should this be allowed to happen when in other states?
		if (elevationState == GroundGearElevationState.Lowered) {
			hardware.groundGearIntake.set(Constants.GROUND_GEAR_INTAKE_SPEED);
			//intakes a gear
			setIntakeState(GroundGearIntakeState.Intake);
		}
	}
	
	public void outtakeGear() {
		// Should this be allowed to happen when in other states?
		if (elevationState == GroundGearElevationState.Lowered || elevationState == GroundGearElevationState.Scoring) {
			hardware.groundGearIntake.set(Constants.GROUND_GEAR_OUTTAKE_SPEED);
			//outtakes a gear
			setIntakeState(GroundGearIntakeState.Outtake);
		}
	}
	
	public void setIntakeState(GroundGearIntakeState set) {
		intakeState = set;
		SmartDashboard.putString("Ground Gear Intake State", set.toString());
	}

	public void setup() {
		hardware.groundGearLift.enable();
		hardware.groundGearLiftgroup.setProfile(0);
		hardware.groundGearLiftgroup.setupProfileMode();
		hardware.groundGearLiftgroup.setpidfrnav(Constants.GROUND_GEAR_PIDFRNAV);
		
	}
	public void travelToState(GroundGearElevationState targetState) {
		if (targetState == elevationState) {
			// We are already at that state
			return;
		}
//		hardware.groundGearLiftgroup.positionControl(targetState.ticks); // Target ticks
		hardware.groundGearLiftgroup.profileControl(targetState.rotations);
//		hardware.groundGearLiftgroup.setupVoltageMode();
//		hardware.groundGearLiftgroup.set(1);
	}
	public void setElevationState(GroundGearElevationState setState) {
		// BE CAREFUL WITH THIS METHOD, ONLY CALL AFTER MOVEMENT
		isOpen = true;
		if (setState == GroundGearElevationState.Legal) {
			isOpen = false;
		}
		elevationState = setState;
		SmartDashboard.putString("Ground Gear Elevation State", elevationState.toString());
	}
	public void stopLift() {
		hardware.groundGearLift.disable();
	}
	public void stopIntake() {
		hardware.groundGearIntake.set(0);
		intakeState = GroundGearIntakeState.Neutral;
	}
	public void slowScore() {
		if (elevationState == GroundGearElevationState.Scoring) {
			hardware.groundGearLiftgroup.setProfile(1);
			outtakeGear(); // Might be too fast, is not controlled seperately from standard outtake speed
			hardware.groundGearLiftgroup.profileControl(GroundGearElevationState.Lowered.ticks);
		}
	}
	public boolean hasGear() {
		return hardware.groundGearBanner.get();
	}

	public CANTalon getElevationMotor() {
		return hardware.groundGearLift;
	}

}
