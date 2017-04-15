package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GearState;
import org.usfirst.frc.team5026.util.Hardware;

import edu.wpi.first.wpilibj.command.Subsystem;

public class GroundGear extends Subsystem implements GearState{

	private Hardware hardware;
	public boolean isOpen;
	
	public GroundGear() {
		hardware = Robot.hardware;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void intakeGear() {
		hardware.groundGearIntake.set(1.0);
		//intakes a gear
	}
	
	public void outtakeGear() {
		hardware.groundGearIntake.set(0.5);
		//outtakes a gear
	}
	
	public void lift() {
		hardware.groundGearLift.set(1.0);
		//lifts the gear intake
		isOpen = false;
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
