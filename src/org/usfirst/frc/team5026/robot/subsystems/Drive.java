package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.drive.DriveWithJoystick;
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
	
	public MotorGroup encLeftMotor;
	public MotorGroup encRightMotor;
	
	public double targetAngle;
	private boolean turningRight = true;
	
	public double startingLeftEncoderPos;
	public double startingRightEncoderPos;
	public double targetLeftEncoderPos;
	public double targetRightEncoderPos;
	private boolean backwards;
	
	public Drive() {
		joystick = Robot.oi.driveJoystick;
		hardware = Robot.hardware;
		drive = new RobotDrive(hardware.leftMotor, hardware.rightMotor);
		gyro = hardware.gyro;
		shifter = Robot.hardware.shifter;
		
		encLeftMotor = hardware.leftMotor;
		encRightMotor = hardware.rightMotor;
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
		if (turningRight) {
    		Robot.drive.setLeftRightMotors(speed, -speed); 
    	} else {
    		Robot.drive.setLeftRightMotors(-speed, speed);
    	}
	}
	
	public void setRotate(double angle) {
		targetAngle = angle;
		stopMotors();
		gyro.reset();
		if(targetAngle > 0) {
			turningRight = true;
		} else {
			turningRight = false;
		}
//		gyro.calibrate();
	}
	public boolean isTurnFinished() {
		if(turningRight) {
			return Math.abs(gyro.getAngle() - targetAngle) <= targetAngle * Constants.PERCENTAGE_FOR_ERROR;
		} else {
			return Math.abs(gyro.getAngle() - targetAngle) > targetAngle * Constants.PERCENTAGE_FOR_ERROR;
		}
	}
	
	public void startDriveDistance(double inches) {
		backwards = false;
		if(inches < 0) {
			backwards = true; 
		}
		startingLeftEncoderPos = encLeftMotor.getEncPosition(); //"leftMotor" cringe
		targetLeftEncoderPos = (startingLeftEncoderPos + (Constants.GEAR_RATIO * (inches / Constants.WHEEL_CIRCUMFERENCE) * Constants.ENCODER_TICKS_PER_ROTATION));
		
		startingRightEncoderPos = encRightMotor.getEncPosition();
		targetRightEncoderPos = (startingRightEncoderPos + (Constants.GEAR_RATIO * (inches / Constants.WHEEL_CIRCUMFERENCE) * Constants.ENCODER_TICKS_PER_ROTATION));
	}
	public double getLeftEnc() {
		return encLeftMotor.getEncPosition();
	}
	public double getRightEnc() {
		return encRightMotor.getEncPosition();
	}
	
	public double getDistanceError() {
		// In inches
		// Make sure to use the correct ratio
		return ((encLeftMotor.getEncPosition() - targetLeftEncoderPos) * Constants.WHEEL_CIRCUMFERENCE) / (Constants.GEAR_RATIO * Constants.ENCODER_TICKS_PER_ROTATION);
	}
	public double getGyroError() {
		// In degrees
		return gyro.getAngle() - targetAngle;
	}
	public double getGyro()
	{
		return gyro.getAngle();
	}
	public void driveStraightWithTicks(double speed) {
		/*while(encMotor.get() <= 57344){
			
		}*/
	}
	
	public void driveStraight(double speed) {
		//try using different motors, or just add a getEncPosition method in motorgroup
		this.encLeftMotor.set(backwards ? -1: 1 * speed);
	}
	
	public boolean isFinishedDrivingDistance(MotorGroup encMotor) {	//i'm sure there's a better way to do this
		if(backwards) {
			return Math.abs(encMotor.getEncPosition() - startingLeftEncoderPos) < targetLeftEncoderPos; 
		}
		return Math.abs(encMotor.getEncPosition() - startingRightEncoderPos) > targetLeftEncoderPos;
	} 
	
	public void autoDriveDistance() {
		if(!isFinishedDrivingDistance(encLeftMotor)) {
			this.encLeftMotor.set((backwards ? -1 : 1) * Constants.STRAIGHT_DRIVE_SPEED);
		} else {
			this.encLeftMotor.stopMotor();
		}
		if(!isFinishedDrivingDistance(encRightMotor)) {
			this.encRightMotor.set((backwards ? -1 : 1) * Constants.STRAIGHT_DRIVE_SPEED);
		} else {
			this.encRightMotor.stopMotor();
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick(joystick));
	}
	
}
