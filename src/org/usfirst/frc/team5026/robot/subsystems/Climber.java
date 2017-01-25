package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.OI;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	private OI oi;
	private Hardware hardware;
	private Talon leftClimb;
	private Talon rightClimb;
	
	public Climber() {
		oi = new OI();
		hardware = new Hardware();
		leftClimb = Robot.hardware.climbLeftMotor;
		rightClimb = Robot.hardware.climbRightMotor;
		
	}
	
	public void stopClimb() {
		leftClimb.set(0.0);
		rightClimb.set(0.0);
	}
	
	public void setClimbMotors(double speed) {
		leftClimb.set(speed);
		rightClimb.set(speed);
	}
	
	public void initDefaultCommand(){	
	}
}
