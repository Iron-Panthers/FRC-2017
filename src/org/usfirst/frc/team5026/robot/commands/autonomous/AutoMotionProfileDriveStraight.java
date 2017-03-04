package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.MotionProfiler;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoMotionProfileDriveStraight extends Command {

	double target;
	double time;
	double startTime;
	double startEncLeft;
	double startEncRight;
	boolean gyro;
	
    public AutoMotionProfileDriveStraight(double target) {
        requires(Robot.drive);
        this.target = target;
    }

    protected void initialize() {
    	MotionProfiler.target = target;
    	time = 0;
    	startTime = Timer.getFPGATimestamp();
    	startEncLeft = Robot.drive.getLeftEnc();
    	startEncRight = Robot.drive.getRightEnc();
    	try {
    		Robot.drive.gyro.reset();
    		gyro = true;
    	} catch (NullPointerException e) {
    		gyro = false;
    	}
    }

    protected void execute() {
    	double dt = Timer.getFPGATimestamp() - startTime;
    	double power = MotionProfiler.getVoltageToApply(time);
    	Robot.drive.setLeftRightMotors(power, power);
    	SmartDashboard.putNumber("Motion Profile Voltage To Apply", power);
    	SmartDashboard.putNumber("Enc Delta Left", Robot.drive.getLeftEnc() - startEncLeft);
    	SmartDashboard.putNumber("Enc Delta Right", Robot.drive.getRightEnc() - startEncRight);
    	if (gyro)
    	SmartDashboard.putNumber("Gyro Delta", Robot.drive.getGyro());
    	time += dt;
    }

    protected boolean isFinished() {
        return time >= MotionProfiler.getTotalTimeToDistance();
    }

    protected void end() {
    	Robot.drive.stopMotors();
    }

    protected void interrupted() {
    	Robot.drive.stopMotors();
    }
}
