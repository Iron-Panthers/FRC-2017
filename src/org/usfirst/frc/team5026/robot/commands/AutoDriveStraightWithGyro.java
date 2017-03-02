package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	NOT FINISHED DO NOT USE
 */
public class AutoDriveStraightWithGyro extends Command {
	private int errorAngle;
	private double distanceTicks;
	private double initLeftTicks;
	
    public AutoDriveStraightWithGyro(double distance, int errorAngle) {
        requires(Robot.drive);
        this.errorAngle = errorAngle;
        distanceTicks = distance / Constants.WHEEL_CIRCUMFERENCE * Constants.GEAR_RATIO * Constants.ENCODER_TICKS_PER_ROTATION;
        initLeftTicks = Robot.drive.getLeftEnc();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setRotate(errorAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.drive.getGyroError() < errorAngle && Robot.drive.getGyroError() > -errorAngle)
    	{
        	Robot.drive.setLeftRightMotors(0.5, 0.5);
    	}
    	else if(Robot.drive.getGyro() < errorAngle)
    	{
        	Robot.drive.setLeftRightMotors(0.4, 0.5);
    	}
    	else
    	{
    		Robot.drive.setLeftRightMotors(0.5, 0.4);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("Left enc", Robot.drive.getLeftEnc());
        SmartDashboard.putNumber("Target enc", distanceTicks + initLeftTicks);
        SmartDashboard.putNumber("Encoder Delta", Robot.drive.getLeftEnc() - initLeftTicks);
        return Robot.drive.getLeftEnc() > (distanceTicks + initLeftTicks);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setLeftRightMotors(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
