package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Code used from WaitForStabilize for the Shooter for 2016
 */
public class AutoDriveDistancePosition extends Command {
	
	private double targetLeft;
	private double targetRight;
	private boolean finished;

	private int count;
	
    public AutoDriveDistancePosition(double targetLeft, double targetRight) {
        requires(Robot.drive);
        this.targetLeft = targetLeft;
        this.targetRight = targetRight;
    }

    protected void initialize() {
    	finished = false;
    	count = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.positionDrive(targetLeft, targetRight);
    	
    	double leftOut = Robot.drive.encLeftMotor.getEncMotor().getOutputVoltage() / Robot.drive.encLeftMotor.getEncMotor().getBusVoltage();
    	double rightOut = Robot.drive.encRightMotor.getEncMotor().getOutputVoltage() / Robot.drive.encRightMotor.getEncMotor().getBusVoltage();
    	
    	SmartDashboard.putNumber("LeftError", Robot.drive.encLeftMotor.getEncMotor().getClosedLoopError());
        SmartDashboard.putNumber("LeftOutput", leftOut);
        SmartDashboard.putNumber("LeftPosition", Robot.drive.encLeftMotor.getEncMotor().getPosition());
        
        SmartDashboard.putNumber("RightError", Robot.drive.encRightMotor.getEncMotor().getClosedLoopError());
        SmartDashboard.putNumber("RightOutput", rightOut);
        SmartDashboard.putNumber("RightPosition", Robot.drive.encRightMotor.getEncMotor().getPosition());
//        System.out.println("expErrorL:"+currentLeftExponentialError+"\tRangeL:"+leftRange+"\texpErrorR:"+currentRightExponentialError+"\tRangeR:"+rightRange);
        if (Math.abs(leftOut) <= Constants.DRIVE_STABILIZATION_TOLERANCE && Math.abs(rightOut) <= Constants.DRIVE_STABILIZATION_TOLERANCE) {
        	count++;
        }
        if (count >= 500) // SIMPLE STUPID CHECKS! TODO FIX
        	finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.endPositionDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
