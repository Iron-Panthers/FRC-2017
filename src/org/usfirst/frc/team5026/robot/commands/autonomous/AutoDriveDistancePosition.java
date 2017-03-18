package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GearPosition;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Code used from WaitForStabilize for the Shooter for 2016
 */
public class AutoDriveDistancePosition extends Command {
	
	private double targetLeft;
	private double targetRight;
	
	String left1;
	String right1;
	
	CANTalon left;
	CANTalon right;

	private int count;
	
    public AutoDriveDistancePosition(double targetLeft, double targetRight) {
        requires(Robot.drive); // I believe this is the issue, it allows this to be interrupted
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
        left = Robot.drive.left.getEncMotor();
        right = Robot.drive.right.getEncMotor();
    }
    public AutoDriveDistancePosition(String s1, String s2) {
    	requires(Robot.drive);
    	left1 = s1;
    	right1 = s2;
    	left = Robot.drive.left.getEncMotor();
        right = Robot.drive.right.getEncMotor();
    }

    protected void initialize() {
    	count = 0;
//    	Robot.drive.setGear(GearPosition.LOW);
    	SmartDashboard.putNumber("IsFinished", 0); //0: not done, 1: ended normally, 2: interrupted
    	Robot.drive.left.resetPosition();
    	Robot.drive.right.resetPosition();
    	Robot.drive.left.setupPositionMode();
    	Robot.drive.right.setupPositionMode();
    	if (left1 != null && right1 != null) {
    		targetLeft = SmartDashboard.getNumber(left1, 0);
    		targetRight = SmartDashboard.getNumber(right1, 0);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	double leftOut = left.getOutputVoltage() / left.getBusVoltage();
    	double rightOut = right.getOutputVoltage() / right.getBusVoltage();
    	
    	SmartDashboard.putNumber("LeftError", left.getClosedLoopError());
        SmartDashboard.putNumber("LeftOutput", leftOut);
        SmartDashboard.putNumber("LeftPosition", left.getPosition());
        
        SmartDashboard.putNumber("RightError", right.getClosedLoopError());
        SmartDashboard.putNumber("RightOutput", rightOut);
        SmartDashboard.putNumber("RightPosition", right.getPosition());
        
        Robot.drive.positionDrive(targetLeft, targetRight);
        
        if(Math.abs(left.getClosedLoopError()) < SmartDashboard.getNumber("Auto Drive Stabilization Tolerance (Ticks)", 0) && Math.abs(right.getClosedLoopError()) < SmartDashboard.getNumber("Auto Drive Stabilization Tolerance (Ticks)", 0)) {
        	count++;
        }
        SmartDashboard.putNumber("Count", count);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return count >= SmartDashboard.getNumber("Auto Drive Stabilization Tolerance (Count)", 0);
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putNumber("IsFinished", 1);
    	Robot.drive.endPositionDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putNumber("IsFinished", 2);
    }
}
