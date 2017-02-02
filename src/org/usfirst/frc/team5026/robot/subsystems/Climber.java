package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.RobotMap;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	
	private Talon leftClimb;
	private Talon rightClimb;
	private PowerDistributionPanel pdp;
	
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
		rightClimb.set(speed);
	}
	
	public void slowClimb() { 
		//sets motors to a constant of 0.3.
		
		leftClimb.set(Constants.CLIMBER_SLOW);
		rightClimb.set(Constants.CLIMBER_SLOW);
	}
	
	public void repel() {
		//sets motors to a constant of -0.2.
		
		leftClimb.set(Constants.CLIMBER_REPEL);
		rightClimb.set(Constants.CLIMBER_REPEL);
	}
	public double climbScaling() {
		// A piecewise graph such that the motor and joystick values varies 
	    // linearly from -1 <= x <= 0 and varies quadratically from 0 <= x <= 1.
		
		double joystickY = Robot.oi.buttonBoard.getY();
	    double speed;
	    if (joystickY <= 0.0) {
	        speed = (Constants.CLIMBER_WRAP * joystickY) + Constants.CLIMBER_WRAP;
	    } else {
	        speed = Math.sqrt(Constants.CLIMB_CURVE * joystickY) + Constants.CLIMBER_WRAP;
	    }
	    return speed;
	}

	public void pollMotorOutput() {
		
		if(pdp.getCurrent(RobotMap.CLIMBER_MOTOR_RIGHT) > 100 || pdp.getCurrent(RobotMap.CLIMBER_MOTOR_LEFT) > 100) {
			stopClimb();
		}
	}

	@Override
	public void initDefaultCommand() {

	}
}
