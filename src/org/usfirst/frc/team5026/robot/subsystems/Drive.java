package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	private RobotDrive drive;
	
	public PantherJoystick joystick;
	private Hardware hardware;
	
	public Drive() {
		joystick = Robot.oi.buttonBoard;
		hardware = Robot.hardware;
		drive = new RobotDrive(hardware.leftMotor, hardware.rightMotor);
	}
	
	public void setLeftRightMotors(double left, double right) {
		drive.setLeftRightMotorOutputs(left, right);
	}
	
	public void useArcadeDrive(double yAxis, double xAxis) {
		drive.arcadeDrive(yAxis, xAxis);
	}
	
	public void stopMotors() {
		this.setLeftRightMotors(0, 0);
	}
	
	//one spark controller is backwards, testing if values need to be opposite
	public void rotateRobot(double speed) {
		if (Constants.DEGREE - hardware.gyro.getAngle() <= Constants.DEGREE * Constants.PERCENTAGE) {
    		Robot.drive.setLeftRightMotors(speed, speed);
    	} 
		else if(Constants.DEGREE - hardware.gyro.getAngle() >= 1){
    		Robot.drive.setLeftRightMotors(-speed, -speed);
    	}
	}
	
	public void setRotate() {
		stopMotors();
		hardware.gyro.calibrate();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand(new DriveWithJoystick(joystick));
	}
	
}
