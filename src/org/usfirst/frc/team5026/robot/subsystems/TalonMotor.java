package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.OI;
import org.usfirst.frc.team5026.robot.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TalonMotor extends Subsystem{
	private Hardware hardware;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public TalonMotor() {
		hardware = Robot.hardware;
	}
	
	public void stopMotor() {
		hardware.talonMotor.set(0);
	}
	
	public void setMotorPositive() {
		hardware.talonMotor.set(1.0);
	}
	
	public void setMotorNegative() {
		hardware.talonMotor.set(-1.0);
	}
	
	
}
