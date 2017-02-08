package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.RobotMap;
import org.usfirst.frc.team5026.robot.commands.climber.ClimberStop;

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
	
	public Climber() {
		
		leftClimb = Robot.hardware.climberLeftMotor;
		rightClimb = Robot.hardware.climberRightMotor;
		
		pdp = new PowerDistributionPanel();
		rightMotorOutput = pdp.getCurrent(RobotMap.CLIMBER_MOTOR_RIGHT);
		leftMotorOutput = pdp.getCurrent(RobotMap.CLIMBER_MOTOR_LEFT);
	}
	
	public void setClimbMotors(double speed) {
		postMotorOutput();
		leftClimb.set(speed);
		rightClimb.set(speed);
	}
	
	public void stopClimb() {
		leftClimb.stopMotor();
		rightClimb.stopMotor();
	}
		
	public void scaledClimb() {
		//sets motors to joystick control with curve from climbScaling().
		setClimbMotors(climbScaling());
	}
	
	public double climbScaling() {
		// A piecewise graph such that the motor and joystick values varies 
	    // linearly from -1 <= x <= 0 and varies quadratically from 0 <= x <= 1.
		double speed;
		double joystickY = Robot.oi.buttonBoard.getY();		//currently accesses raw Y-values, will implement Daniel's adjustments
		
	    if (joystickY <= Constants.CLIMBER_CURVE_SWAP) {	//linear joystick curve
	        speed = (Constants.CLIMBER_SPEED_WRAP * joystickY) + Constants.CLIMBER_SPEED_WRAP;
	    } else {	//quadratic joystick curve
	        speed = Math.sqrt(Constants.CLIMBER_CURVE * joystickY) + Constants.CLIMBER_SPEED_WRAP;
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

	public void postMotorOutput() {
		SmartDashboard.putNumber("Left", leftMotorOutput);
		SmartDashboard.putNumber("right", rightMotorOutput);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ClimberStop());
	}
}
