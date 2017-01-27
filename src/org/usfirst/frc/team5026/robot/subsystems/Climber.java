package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	private Talon leftClimb;
	private Talon rightClimb;
	
	public Climber() {
		leftClimb = Robot.hardware.climbLeftMotor;
		rightClimb = Robot.hardware.climbRightMotor;
		
	}
	
	public void stopClimb() {
		leftClimb.stopMotor();
		rightClimb.stopMotor();
	}
	
	public void setClimbMotors(double speed) {
		leftClimb.set(speed);
		rightClimb.set(-speed);
	}
	
	public void slowClimb() { 
		leftClimb.set(Constants.SLOW_CLIMB);
		rightClimb.set(Constants.SLOW_CLIMB);
	}
	
	//public double getMotorOutput() {}
	
	public void initDefaultCommand(){	
	}
}
