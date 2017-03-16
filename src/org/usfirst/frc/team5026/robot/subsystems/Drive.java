package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.drive.DriveWithJoystick;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.DriveMotorGroup;
import org.usfirst.frc.team5026.util.GearPosition;
import org.usfirst.frc.team5026.util.Hardware;
import org.usfirst.frc.team5026.util.LEDDisplay;
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
	private LEDDisplay led;
	private GearPosition pos = GearPosition.LOW;
	public Gyro gyro;
	Hardware hardware;
	
	public DriveMotorGroup left;
	public DriveMotorGroup right;
	
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
		drive.setSafetyEnabled(false);
		left = hardware.leftMotor;
		right = hardware.rightMotor;
		led = hardware.led;
	}
	
	public void setLeftRightMotors(double left, double right) {
		drive.setLeftRightMotorOutputs(left, right);
	}
	
	public void setBrakeMode(boolean brake)
	{
		left.setBrakeMode(brake);
		right.setBrakeMode(brake);
	}
	
	public void setGear() {
		if (pos== GearPosition.LOW) {
			pos = GearPosition.HIGH;
			shifter.set(Value.kReverse);
		} else {
			pos = GearPosition.LOW;
			shifter.set(Value.kForward);
		}
		// Flip flops gear position
	}
	
	public void useArcadeDrive(double yAxis, double xAxis) {
		drive.arcadeDrive(yAxis, xAxis);
	}
	
	public void stopMotors() {
		this.endPositionDrive();
		this.setLeftRightMotors(0, 0);
	}
	
	//one spark controller is backwards, testing if values need to be opposite
	public void rotateRobot(double speed) {
		if (turningRight) {
    		Robot.drive.setLeftRightMotors(-speed, speed); 
    	} else {
    		Robot.drive.setLeftRightMotors(speed, -speed);
    	}
	}
	
	public void setRotate(double angle) {
		targetAngle = angle;
		stopMotors();
		try {
			gyro.reset();
		} catch (NullPointerException e) {
			System.out.println("No Gyro!");
		}
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
		startingLeftEncoderPos = left.getEncPosition(); //"leftMotor" cringe
		targetLeftEncoderPos = (startingLeftEncoderPos + (Constants.GEAR_RATIO * (inches / Constants.WHEEL_CIRCUMFERENCE) * Constants.ENCODER_TICKS_PER_ROTATION));
		
		startingRightEncoderPos = right.getEncPosition();
		targetRightEncoderPos = (startingRightEncoderPos + (Constants.GEAR_RATIO * (inches / Constants.WHEEL_CIRCUMFERENCE) * Constants.ENCODER_TICKS_PER_ROTATION));
	}
	public double getLeftEnc() {
		return left.getEncPosition();
	}
	public double getRightEnc() {
		return right.getEncPosition();
	}
	
	public double getDistanceError() {
		// In inches
		// Make sure to use the correct ratio
		return ((left.getEncPosition() - targetLeftEncoderPos) * Constants.WHEEL_CIRCUMFERENCE) / (Constants.GEAR_RATIO * Constants.ENCODER_TICKS_PER_ROTATION);
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
		this.left.set(backwards ? -1: 1 * speed);
	}
	
	public boolean isFinishedDrivingDistance(DriveMotorGroup encMotor) {	//i'm sure there's a better way to do this
		if(backwards) {
			return Math.abs(encMotor.getEncPosition() - startingLeftEncoderPos) < targetLeftEncoderPos; 
		}
		return Math.abs(encMotor.getEncPosition() - startingRightEncoderPos) > targetLeftEncoderPos;
	} 
	
	public void autoDriveDistance() {
		if(!isFinishedDrivingDistance(left)) {
			this.left.set((backwards ? -1 : 1) * Constants.STRAIGHT_DRIVE_SPEED);
		} else {
			this.left.stopMotor();
		}
		if(!isFinishedDrivingDistance(right)) {
			this.right.set((backwards ? -1 : 1) * Constants.STRAIGHT_DRIVE_SPEED);
		} else {
			this.right.stopMotor();
		}
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick(joystick));
	}
	
	public void positionDrive(double targetLeft, double targetRight)
	{
		left.positionControl(targetLeft);
		right.positionControl(targetRight);
	}
	
	public void endPositionDrive()
	{
		left.stopPositionControl();
		right.stopPositionControl();
	}
}