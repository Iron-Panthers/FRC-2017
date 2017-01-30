package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.OI;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Hardware;

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
		leftClimb.set(0.0);
		rightClimb.set(0.0);
	}
	
	public void setClimbMotors(double speed) {
		leftClimb.set(speed);
		rightClimb.set(-speed);
	}
	
	public void initDefaultCommand(){	
	}
}
