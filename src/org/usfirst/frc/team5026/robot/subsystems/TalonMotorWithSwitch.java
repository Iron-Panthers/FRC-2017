package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TalonMotorWithSwitch extends Subsystem {
	private Hardware hardware;
	private DigitalInput talonSwitch;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public TalonMotorWithSwitch() {
		hardware = Robot.hardware;
		talonSwitch = Robot.hardware.talonSwitch;
	}
	
	public void stopMotor() {
		hardware.talonMotor.set(0);
	}
	
	public void setMotorPositive() {
		if (talonSwitch.get() == false) {
			hardware.talonMotor.set(1.0);
		}
		
	}
	
	public void setMotorNegative() {
		if (talonSwitch.get() == true) {
			hardware.talonMotor.set(-1.0);
		}
		
	}
	
}
