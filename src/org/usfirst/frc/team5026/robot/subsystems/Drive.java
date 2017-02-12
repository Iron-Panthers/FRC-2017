package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GearPosition;
import org.usfirst.frc.team5026.util.Hardware;
import org.usfirst.frc.team5026.util.MotorGroup;
import org.usfirst.frc.team5026.util.PantherJoystick;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Drive extends Subsystem {
	private RobotDrive drive;
	
	private PantherJoystick joystick;
	private DoubleSolenoid shifter;
	public Gyro gyro;
	Hardware hardware;
	
	public MotorGroup encMotor;
	
	public double targetAngle;
	
	public double startingEncoderPos;
	public double targetEncoderPos;
	private boolean backwards;
	
	public Drive() {
		joystick = Robot.oi.driveJoystick;
		hardware = Robot.hardware;
		drive = new RobotDrive(hardware.leftMotor, hardware.rightMotor);
		gyro = hardware.gyro;
		shifter = Robot.hardware.shifter;
		encMotor = hardware.leftMotor;
	}
	
	public void setLeftRightMotors(double left, double right) {
		drive.setLeftRightMotorOutputs(left, right);
	} 
	
	public void setGear(GearPosition pos) {
		switch (pos) {
		case HIGH:
			shifter.set(Value.kForward);
		case LOW:
			shifter.set(Value.kReverse);
		}
	}
	
	public void useArcadeDrive(double yAxis, double xAxis) {
		drive.arcadeDrive(yAxis, xAxis);
	}
	
	public void stopMotors() {
		this.setLeftRightMotors(0, 0);
	}
	
	//one spark controller is backwards, testing if values need to be opposite
	public void rotateRobot(double speed) {
		if (targetAngle - gyro.getAngle() <= targetAngle * Constants.PERCENTAGE) {
    		Robot.drive.setLeftRightMotors(speed, -speed);
    	} 
		else if(targetAngle - gyro.getAngle() >= targetAngle * Constants.PERCENTAGE){
    		Robot.drive.setLeftRightMotors(-speed, speed);
    	}
	}
	
	public void setRotate(double angle) {
		targetAngle = angle;
		stopMotors();
		gyro.reset();
//		gyro.calibrate();
	}
	public boolean isTurnFinished() {
		return Math.abs(targetAngle - gyro.getAngle()) <= targetAngle * Constants.PERCENTAGE;	
	}
	
	public void startDriveDistance(double inches) {
		try {
			gyro.reset();
		} catch (NullPointerException e) {
			// No gyro
		}
		
		backwards = false;
		if(inches < 0) {
			backwards = true; 
		}
		startingEncoderPos = encMotor.getEncPosition(); //"leftMotor" cringe
		//which gear ratio ????????????????????????????
		targetEncoderPos = (startingEncoderPos + (Constants.LOW_GEAR_RATIO * (inches / Constants.WHEEL_CIRCUMFERENCE) * Constants.ENCODER_TICKS_PER_ROTATION));
	}
	public int getEnc() {
		return encMotor.getEncPosition();
	}
	public double getDistanceError() {
		// In inches
		// Make sure to use the correct ratio
		return ((encMotor.getEncPosition() - targetEncoderPos) * Constants.WHEEL_CIRCUMFERENCE) / (Constants.LOW_GEAR_RATIO * Constants.ENCODER_TICKS_PER_ROTATION);
	}
	public double getGyroError() {
		// In degrees
		return gyro.getAngle() - targetAngle;
	}
		
	public void driveStraight(double speed) {
		//try using different motors, or just add a getEncPosition method in motorgroup
		if(backwards) {
			Robot.drive.setLeftRightMotors(-speed, -speed);
		} else {
			Robot.drive.setLeftRightMotors(speed, speed);
		}
	}
	
	public boolean isFinishedDrivingDistance() {
		if(backwards) {
			return encMotor.getEncPosition() < targetEncoderPos; 
		}
		return encMotor.getEncPosition() > targetEncoderPos; 
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick(joystick));
	}
	
}
