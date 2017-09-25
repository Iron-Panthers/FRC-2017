package org.usfirst.frc.team5026.robot.commands.misc;

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
	private double lastPos;
	private double lastVel;
	private double distance;
	
    public AutoDriveStraightWithGyro(double distance, int errorAngle) {
        requires(Robot.drive);
        this.errorAngle = errorAngle;
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        distanceTicks = distance / Constants.WHEEL_CIRCUMFERENCE * Constants.GEAR_RATIO * Constants.ENCODER_TICKS_PER_ROTATION;
        initLeftTicks = Robot.drive.getLeftEnc();
    	Robot.drive.setRotate(errorAngle);
    	lastPos = 0;
    	lastVel = 0;
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
    	double vel = (Robot.drive.getLeftEnc() - lastPos) / 2;
    	double accel = (vel - lastVel) / 2;
    	SmartDashboard.putNumber("Velocity", vel);
    	SmartDashboard.putNumber("Acceleration", accel);
    	
    	lastPos = Robot.drive.getLeftEnc();
    	lastVel = vel;
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
