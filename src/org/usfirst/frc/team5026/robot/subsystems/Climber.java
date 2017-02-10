package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.RobotMap;
import org.usfirst.frc.team5026.robot.commands.climber.ClimberStop;
import org.usfirst.frc.team5026.util.ClimberSpeedType;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	
	private Talon leftClimb;
	private Talon rightClimb;
	
	private PowerDistributionPanel pdp;
	private double leftMotorOutput;
	private double rightMotorOutput;
	
	private int currentSpeedIndex; //0: LATCH, 1: WRAP, //2: FLOOR
	private double cycledSpeed;
	
	public Climber() {
		
		leftClimb = Robot.hardware.climberLeftMotor;
		rightClimb = Robot.hardware.climberRightMotor;
		
		pdp = new PowerDistributionPanel();
		rightMotorOutput = pdp.getCurrent(RobotMap.CLIMBER_MOTOR_RIGHT);
		leftMotorOutput = pdp.getCurrent(RobotMap.CLIMBER_MOTOR_LEFT);
		
		currentSpeedIndex = 0;
	}
	
	public void setClimbMotors(double speed) {
		postMotorOutput();
		leftClimb.set(speed);
		rightClimb.set(speed);
	}
	
	public void setClimbMotors() {
		//new method that uses an instance variable that is 'speed' from cycle method
		postMotorOutput();
		setClimbMotors(cycledSpeed);
	}
	
	
	public void stopClimb() {
		leftClimb.stopMotor();
		rightClimb.stopMotor();
	}
	/*	
	public void scaledClimb() {
		//sets motors to joystick control with curve from climbScaling().
		setClimbMotors(climbScaling());
	}
	*/
	
	public double climbScaling(ClimberSpeedType scaleType) {
		// A piecewise graph such that the motor and joystick values varies 
	    // linearly from -1 <= x <= 0 and varies quadratically from 0 <= x <= 1.
		double speed;
		double joystickY = Robot.oi.buttonBoard.getY();		//currently accesses raw Y-values, will implement Daniel's adjustments
		
	    if (joystickY <= Constants.CLIMBER_SLOPE_SWAP) {	//linear joystick curve
	        speed = (scaleType.speed * joystickY) + scaleType.speed;
	    } else {	//square root joystick curve
	        speed = Math.sqrt(scaleType.curve * joystickY) + scaleType.speed;
	    }
	    return speed;
	}
	
	public boolean hasResistance() {
		if((leftMotorOutput > Constants.CLIMBER_STALL_LIMIT || 
			rightMotorOutput > Constants.CLIMBER_STALL_LIMIT)) {
			System.out.println("RESISTANCE DETECTED");
			return true;
		}
		return false;
	}
	
	public void cycleClimberSpeedType(boolean cycleUp) {
		if(cycleUp) {	//cycles speed type up, resets when it increases past amount of speed types
			if(currentSpeedIndex < ClimberSpeedType.values().length - 1) {
				cycledSpeed = ClimberSpeedType.values()[currentSpeedIndex + 1].speed;
			} else {
				cycledSpeed = ClimberSpeedType.values()[0].speed;
			}
		} else {		//cycles speed type down, resets when it decreases past the first index
			if(currentSpeedIndex != 0) {
				cycledSpeed = ClimberSpeedType.values()[currentSpeedIndex - 1].speed;
			} else {
				cycledSpeed = ClimberSpeedType.values()[ClimberSpeedType.values().length - 1].speed;
			}
		}
	}

	public void postMotorOutput() {
		SmartDashboard.putNumber("Left", leftMotorOutput);
		SmartDashboard.putNumber("right", rightMotorOutput);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ClimberStop());
	}
}
