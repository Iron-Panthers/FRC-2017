package org.usfirst.frc.team5026.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

public class Climber extends Subsystem {
	
	private Talon leftClimb;
	private Talon rightClimb;
	
	public Climber() {
		leftClimb = Robot.hardware.climberLeftMotor;
		rightClimb = Robot.hardware.climberRightMotor;
		
	}
	
	public void stopClimb() {
		leftClimb.stopMotor();
		rightClimb.stopMotor();
	}
	
	public void setClimbMotors(double speed) {
		leftClimb.set(speed);
		rightClimb.set(speed); //Should not be negative?? -- used to be rightClimb.set(-speed);
	}
	
	public void slowClimb() { 
		leftClimb.set(Constants.CLIMBER_SLOW);
		rightClimb.set(Constants.CLIMBER_SLOW);
	}

	//public double getMotorOutput() {}

	@Override
	public void initDefaultCommand() {

	}
}
