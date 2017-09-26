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
	
	public int currentSpeedIndex; //0: LATCH, 1: WRAP
	
	public Climber() {
		
		leftClimb = Robot.hardware.climberLeftMotor;
		rightClimb = Robot.hardware.climberRightMotor;
		leftClimb.setInverted(Robot.hardware.climberLeftInverted);
		rightClimb.setInverted(Robot.hardware.climberRightInverted);
		
		pdp = Robot.hardware.pdp;
		
		currentSpeedIndex = 0;
	}
	
	public void setClimbMotors(double speed) {
		postMotorOutput();
		leftClimb.set(speed);
		rightClimb.set(speed);		//use setInverted(true)
	}
	
	public void stopClimb() {
		leftClimb.stopMotor();
		rightClimb.stopMotor();
	}

	public void climbScaling() {
		// A piecewise graph such that the motor and joystick values varies 
	    // linearly from -1 <= x <= 0 and varies quadratically from 0 <= x <= 1.
		double joystickY = -Robot.oi.buttonBoard.getY();	//accesses raw Y-values
		
	    if (joystickY <= Constants.CLIMBER_SLOPE_SWAP) {	//linear joystick curve
	       setClimbMotors(ClimberSpeedType.values()[currentSpeedIndex].speed * joystickY + ClimberSpeedType.values()[currentSpeedIndex].speed);
	    } else {	//square root joystick curve
	       setClimbMotors(Math.sqrt(ClimberSpeedType.values()[currentSpeedIndex].curve * joystickY) + ClimberSpeedType.values()[currentSpeedIndex].speed);
	    }
	}
	
	public void climbFast() {
		// Follows an exponential curve for joystick
		double joystickY = -Robot.oi.buttonBoard.getY();
		setClimbMotors(Math.pow(3, joystickY) / 3.0); // exponential curve. See link at: https://www.desmos.com/calculator/t3ynilnywg
		SmartDashboard.putNumber("Climber outputttt", Math.pow(3, joystickY) / 3.0);
	}
	
	public boolean hasResistance() {
		rightMotorOutput = pdp.getCurrent(RobotMap.CLIMBER_PDPMOTOR_RIGHT);
		leftMotorOutput = pdp.getCurrent(RobotMap.CLIMBER_PDPMOTOR_LEFT);
		
		if((leftMotorOutput > Constants.CLIMBER_STALL_LIMIT || 
			rightMotorOutput > Constants.CLIMBER_STALL_LIMIT)) {
			System.out.println("RESISTANCE DETECTED");
			return true;
		}
		return false;
	}
	
	public void cycleClimberSpeedType() {
		if(currentSpeedIndex < ClimberSpeedType.values().length - 1) {
			currentSpeedIndex += 1;
		} else {
			currentSpeedIndex = 0;
		}
	}

	public void postMotorOutput() {
		SmartDashboard.putNumber("Climber Left Voltage", leftMotorOutput);
		SmartDashboard.putNumber("Climber Right Voltage", rightMotorOutput);
		SmartDashboard.putNumber("CLimber Left Speed", leftClimb.get());
		SmartDashboard.putNumber("Climber Right Speed", rightClimb.get());
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ClimberStop());
	}
}
