package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.drive.DriveWithJoystick;
import org.usfirst.frc.team5026.util.Constants;
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
	Gyro gyro;
	Hardware hardware;
	
	private double targetAngle;
	
	public Drive() {
		joystick = Robot.oi.driveJoystick;
		hardware = Robot.hardware;
		drive = new RobotDrive(hardware.leftMotor, hardware.rightMotor);
		gyro = hardware.gyro;
		shifter = hardware.shifter;
		led = hardware.led;
	}
	
	public void setLeftRightMotors(double left, double right) {
		drive.setLeftRightMotorOutputs(left, right);
	}
	
	public void setGear() {
		if (pos== GearPosition.LOW) {
			pos = GearPosition.HIGH;
			shifter.set(Value.kReverse);
			led.writeRegister(Constants.LED_SHIFT_INDEX, Constants.LED_SHIFT_HIGH);
		} else {
			pos = GearPosition.LOW;
			shifter.set(Value.kForward);
			led.writeRegister(Constants.LED_SHIFT_INDEX, Constants.LED_SHIFT_LOW);
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
		if (targetAngle - gyro.getAngle() <= targetAngle * Constants.PERCENTAGE) {
    		Robot.drive.setLeftRightMotors(speed, speed);
    	} 
		else if(targetAngle - gyro.getAngle() >= targetAngle * Constants.PERCENTAGE){
    		Robot.drive.setLeftRightMotors(-speed, -speed);
    	}
	}
	
	public void setRotate(double angle) {
		targetAngle = angle;
		stopMotors();
		gyro.calibrate();
	}
	public boolean isTurnFinished() {
		return Math.abs(targetAngle - gyro.getAngle()) <= targetAngle * Constants.PERCENTAGE;	
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick(joystick));
	}
	
}
