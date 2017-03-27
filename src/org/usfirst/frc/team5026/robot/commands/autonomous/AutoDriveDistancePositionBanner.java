package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GearPosition;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Code used from WaitForStabilize for the Shooter for 2016
 */
public class AutoDriveDistancePositionBanner extends Command {
	
	private double targetLeft;
	private double targetRight;
	
	String left1;
	String right1;
	
	CANTalon left;
	CANTalon right;

	private int count;
	private int leftCount;
	private int rightCount;
	
	boolean turnLeft;
	boolean finished;
	
    public AutoDriveDistancePositionBanner(double targetLeft, double targetRight) {
        requires(Robot.drive);
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
        left = Robot.drive.left.getEncMotor();
        right = Robot.drive.right.getEncMotor();
        if(targetLeft > targetRight)
        {
        	turnLeft = false;
        }
        else
        {
        	turnLeft = true;
        }
    }
    
    public AutoDriveDistancePositionBanner(String s1, String s2, int count) {
    	requires(Robot.drive);
    	left1 = s1;
    	right1 = s2;
    	left = Robot.drive.left.getEncMotor();
        right = Robot.drive.right.getEncMotor();
    }

    protected void initialize() {
    	count = 0;
    	leftCount = 0;
    	rightCount = 0;
    	//Robot.drive.setGear(GearPosition.LOW);
    	SmartDashboard.putNumber("IsFinished", 0); //0: not done, 1: ended normally, 2: interrupted
    	Robot.drive.left.resetPosition();
    	Robot.drive.right.resetPosition();
    	Robot.drive.left.setupPositionMode();
    	Robot.drive.right.setupPositionMode();
    	finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putBoolean("Left banner", Robot.hardware.driveLeftBanner.get());
    	SmartDashboard.putBoolean("Right banner", Robot.hardware.driveRightBanner.get());
    	
    	double leftOut = left.getOutputVoltage() / left.getBusVoltage();
    	double rightOut = right.getOutputVoltage() / right.getBusVoltage();
    	
    	Robot.drive.setGear(GearPosition.LOW);
    	
    	SmartDashboard.putNumber("LeftError", left.getClosedLoopError());
        SmartDashboard.putNumber("LeftOutput", leftOut);
        SmartDashboard.putNumber("LeftPosition", left.getPosition());
        
        SmartDashboard.putNumber("RightError", right.getClosedLoopError());
        SmartDashboard.putNumber("RightOutput", rightOut);
        SmartDashboard.putNumber("RightPosition", right.getPosition());
        
        // TODO Adjust speed driving based off of delta between encoders! Ex: Left-Right, move left faster if negative, right faster if positive; maybe change peak voltages...
        Robot.drive.positionDrive(targetLeft, targetRight);
        
        if(Math.abs(left.getClosedLoopError()) < SmartDashboard.getNumber("Auto Drive Stabilization Tolerance (Ticks)", 0) && Math.abs(right.getClosedLoopError()) < SmartDashboard.getNumber("Auto Drive Stabilization Tolerance (Ticks)", 0)) {
        	count++;
        }
        SmartDashboard.putNumber("Count", count);
        
        if(turnLeft)
        {
        	leftCount = changeVoltage(Robot.hardware.driveLeftBanner, leftCount);
        }
        else
        {
        	rightCount = changeVoltage(Robot.hardware.driveRightBanner, rightCount);
        }
    }
    private int changeVoltage(DigitalInput banner, int counter) {
    	if (counter > 50 && banner.get()) {
    		finished = true;
    	}
    	if(banner.get())
    	{
    		counter = 0;
    		Robot.drive.left.configPeakOutputVoltage(+6f, -6f);
    		Robot.drive.right.configPeakOutputVoltage(+6f, -6f);
    	}
    	counter++;
    	return counter;
    }

    protected boolean isFinished() {
    	return finished || count > 500;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putNumber("IsFinished", 1);
    	Robot.drive.endPositionDrive();
    	Robot.drive.left.configPeakOutputVoltage(+12f, -12f);
		Robot.drive.right.configPeakOutputVoltage(+12f, -12f);
    }

    protected void interrupted() {
    	SmartDashboard.putNumber("IsFinished", 2);
    }
}
