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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem {
	private RobotDrive drive;
	
	private PantherJoystick joystick;
	private DoubleSolenoid shifter;
	private GearPosition pos = GearPosition.LOW;
	public Gyro gyro;
	Hardware hardware;
	
	public MotorGroup encMotorLeft;
	public MotorGroup encMotorRight;
	
	public double targetAngle;
	private boolean turningRight = true;
	
	public double startingEncoderPosLeft;
	public double startingEncoderPosRight;
	public double targetEncoderPosLeft;
	public double targetEncoderPosRight;
	
	private boolean backwardsLeft;
	private boolean backwardsRight;
	
	public Drive() {
		joystick = Robot.oi.driveJoystick;
		hardware = Robot.hardware;
		drive = new RobotDrive(hardware.leftMotor, hardware.rightMotor);
		gyro = hardware.gyro;
		shifter = Robot.hardware.shifter;
		encMotorLeft = hardware.leftMotor;
		encMotorRight = hardware.rightMotor;
	}
	
	public void setLeftRightMotors(double left, double right) {
		drive.setLeftRightMotorOutputs(left, right);
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
		turningRight = angle > 0 ? true: false;
	}
	public boolean isTurnFinished() {
		if(turningRight) {
			return Math.abs(gyro.getAngle() - targetAngle) <= targetAngle * Constants.PERCENTAGE;
		} else {
			return Math.abs(gyro.getAngle() - targetAngle) > targetAngle * Constants.PERCENTAGE;
		}
	}
	
	public void startDriveDistance(double inchesLeft, double inchesRight) {
		try {
			gyro.reset();
		} catch (NullPointerException e) {/*No gyro*/}
		
		backwardsLeft = inchesLeft < 0 ? true : false;
		backwardsRight = inchesRight < 0 ? false: true;
		encMotorLeft.encoderMotor.reset();
		encMotorRight.encoderMotor.reset();
		startingEncoderPosLeft = encMotorLeft.getEncPosition();
		startingEncoderPosRight = encMotorRight.getEncPosition();
		
		targetEncoderPosLeft = (startingEncoderPosLeft + (Constants.GEAR_RATIO * (inchesLeft / Constants.WHEEL_CIRCUMFERENCE) * Constants.ENCODER_TICKS_PER_ROTATION));
		targetEncoderPosRight = (startingEncoderPosRight + (Constants.GEAR_RATIO * (inchesRight / Constants.WHEEL_CIRCUMFERENCE) * Constants.ENCODER_TICKS_PER_ROTATION));
	}
	public double[] getEnc() {
		double[] encs = {encMotorLeft.getEncPosition(), encMotorRight.getEncPosition()};
		return encs;
	}
	
	public double getDistanceError() {
		// In inches
		// Make sure to use the correct ratio
		return ((encMotorLeft.getEncPosition() - targetEncoderPosLeft) * Constants.WHEEL_CIRCUMFERENCE) / (Constants.GEAR_RATIO * Constants.ENCODER_TICKS_PER_ROTATION);
	}
	public double getGyroError() {
		// In degrees
		return gyro.getAngle() - targetAngle;
	}
	public void driveStraightWithTicks(double speed) {
		/*while(encMotor.get() <= 57344){
			
		}*/
	}
	private boolean isDoneSide(double current, double target) {
		if (Math.abs(current) > Math.abs(target * (1 - Constants.PERCENTAGE)) && Math.abs(current) < Math.abs(target * (1 + Constants.PERCENTAGE))) {
			// If we have reached our target on one side
			return true;
		}
		return false;
	}
	private double speedCalculations(double speed, boolean backwards, double target, double current) {
		speed = backwards ? -speed : speed;
		double spd = speed;
		if (backwards) {
			// Speed is negative here
			// Ex: 100 current; 40 target
			// -40 current; -200 target
			// 0 current; -40 target
			if (isDoneSide(current, target)) return 0;
			// If target is positive
			if (target > 0) {
				if (current < target * (2 - Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE)) { // Changes 95% into 105%. Should be 1-percentage + 1, simplifies to 2-percentage
					spd = speed * (current - target) / (target * (1-Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE));
					// Because spd is negative:
					if (spd - Constants.MOTOR_DEADZONE > speed) {
						spd -= Constants.MOTOR_DEADZONE;
					}
				}
			}
			else {
				if (current < target * Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE) {
					spd = speed * (target - current) / (target * (1-Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE));
					// Because spd is negative:
					if (spd - Constants.MOTOR_DEADZONE > speed) {
						spd -= Constants.MOTOR_DEADZONE;
					} else {
						spd = speed;
					}
				}
			}
		} 
		else {
			// Speed is always positive here
			// Ex: 40 current; 100 target
			// -200 current; -40 target
			if (isDoneSide(current, target)) return 0;
			if (target > 0) {
				if (current > target * Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE) {
					spd = speed * (target - current) / (target * (1-Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE));
					// Because spd is positive
					if (spd + Constants.MOTOR_DEADZONE < speed) {
						spd += Constants.MOTOR_DEADZONE;
					}
				}
			} else {
				if (current > target * (2-Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE)) {
					spd = speed * (current - target) / (target * (1 - Constants.STRAIGHT_DRIVE_SLOWDOWN_TARGET_PERCENTAGE));
					// Speed is still positive
					if (spd + Constants.MOTOR_DEADZONE < speed) {
						spd += Constants.MOTOR_DEADZONE;
					}
				}
			}
		}
		return spd;
	}
	public void driveStraight(double speed) {
		double[] encs = getEnc();
		// TODO RESOLVE -
		double speedLeft = speedCalculations(speed, backwardsLeft, targetEncoderPosLeft, encs[0]);
		double speedRight = speedCalculations(speed, backwardsRight, targetEncoderPosRight, encs[1]);
		
		SmartDashboard.putNumber("SpeedLeft", speedLeft);
		SmartDashboard.putNumber("SpeedRight", speedRight);
		
		setLeftRightMotors(speedLeft, speedRight);
	}
	
	public boolean isFinishedDrivingDistance() {
		double[] encs = getEnc();
		if (isDoneSide(encs[0], targetEncoderPosLeft) && isDoneSide(encs[1], targetEncoderPosRight)) {
			SmartDashboard.putBoolean("Is Driving Complete?", true);
			return true;
		}
		SmartDashboard.putBoolean("Is Driving Complete?", false);
		return false;
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick(joystick));
	}
	
}
