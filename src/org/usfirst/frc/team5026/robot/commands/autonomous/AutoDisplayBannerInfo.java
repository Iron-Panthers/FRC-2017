package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoDisplayBannerInfo extends Command {

    public AutoDisplayBannerInfo() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Left banner num", Robot.hardware.driveLeftBanner.get() ? 1 : -Math.random()/10.0);
    	SmartDashboard.putNumber("Right banner num", Robot.hardware.driveRightBanner.get() ? 1 : -Math.random()/10.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
