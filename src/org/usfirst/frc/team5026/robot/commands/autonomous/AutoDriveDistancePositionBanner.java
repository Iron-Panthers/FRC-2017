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
	private int countMax;
	private int leftCount;
	private int rightCount;
	
	boolean turnLeft;
	boolean finished;
	boolean hit;
	
    public AutoDriveDistancePositionBanner(double targetLeft, double targetRight) {
        requires(Robot.drive);
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
        left = Robot.drive.left.getEncMotor();
        right = Robot.drive.right.getEncMotor();
        if(Math.abs(targetLeft) > Math.abs(targetRight))
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
        countMax = count;
        if(Math.abs(targetLeft) > Math.abs(targetRight))
        {
        	turnLeft = false;
        }
        else
        {
        	turnLeft = true;
        }
    }

    protected void initialize() {
    	count = 0;
    	leftCount = 0;
    	rightCount = 0;
    	hit = false;
    	//Robot.drive.setGear(GearPosition.LOW);
    	SmartDashboard.putNumber("IsFinished", 0); //0: not done, 1: ended normally, 2: interrupted
    	Robot.drive.left.resetPosition();
    	Robot.drive.right.resetPosition();
    	Robot.drive.left.setupPositionMode();
    	Robot.drive.right.setupPositionMode();
    	finished = false;
    	if (left1 != null && right1 != null) {
    		targetLeft = SmartDashboard.getNumber(left1, 0);
    		targetRight = SmartDashboard.getNumber(right1, 0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean leftBanner = Robot.hardware.driveLeftBanner.get();
    	boolean rightBanner = !Robot.hardware.driveRightBanner.get(); // Right inverted
    	SmartDashboard.putBoolean("Left banner", leftBanner);
    	SmartDashboard.putBoolean("Right banner", rightBanner);
    	
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
        SmartDashboard.putBoolean("Turning Left", turnLeft);
        
        if(turnLeft)
        {
        	leftCount = changeVoltage(leftBanner, leftCount);
        	SmartDashboard.putNumber("Left count", leftCount);
        }
        else
        {
        	rightCount = changeVoltage(rightBanner, rightCount);
        	SmartDashboard.putNumber("Right count", rightCount);
        }
    }
    private int changeVoltage(boolean banner, int counter) {
    	// Sensor counts when not triggered, must have already been triggered
    	if (counter > SmartDashboard.getNumber("Banner Buffer", 0) && banner && hit) {
    		finished = true;
    	}
    	if(banner && !hit)
    	{
    		hit = true;
    		// Resets counter, counts for nonbanner sensor triggers
    		counter = 0;
    		// Slows down position control after hitting a banner once
    		Robot.drive.left.configPeakOutputVoltage(+6f, -6f);
    		Robot.drive.right.configPeakOutputVoltage(+6f, -6f);
    	}
    	if (hit)
    		counter++;
    	return counter;
    }

    protected boolean isFinished() {
    	return finished || count > countMax;
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
