package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.groundgear.GroundGearStayElevation;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GearOpenable;
import org.usfirst.frc.team5026.util.GroundGearElevationState;
import org.usfirst.frc.team5026.util.GroundGearIntakeState;
import org.usfirst.frc.team5026.util.Hardware;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GroundGear extends GearOpenable {

	private Hardware hardware;
	public GroundGearElevationState elevationState; 
	public GroundGearIntakeState intakeState; 
	
	
	public GroundGear() {
		hardware = Robot.hardware;
		setup();
		
		setIntakeState(GroundGearIntakeState.Neutral); // Not sucking in when starting
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new GroundGearStayElevation());
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
		hardware.groundGearLift.set(0);	
	}
	
	public void setLiftPower(double power) {
		hardware.groundGearLift.set(power);
	}

	public void stopIntake() {
		hardware.groundGearIntake.set(0);
		intakeState = GroundGearIntakeState.Neutral;
	}
	
	public void slowScore() {
		if (elevationState == GroundGearElevationState.Scoring) {
			//hardware.groundGearLiftgroup.setProfile(1);
			outtakeGear(); // Might be too fast, is not controlled seperately from standard outtake speed
			//hardware.groundGearLiftgroup.profileControl(GroundGearElevationState.Lowered.ticks);
		}
	}
	
	public boolean hasGear() {
		return hardware.groundGearBanner.get();
	}

}
