package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoBannerTurn extends Command {

    public AutoBannerTurn() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.hardware.driveLeftBanner.get())
    	{
    		Robot.drive.left.voltageControl(0.2);
    		Robot.drive.right.voltageControl(0.2);
    	}
    	else
    	{
        	Robot.drive.left.voltageControl(0.3);
        	Robot.drive.right.voltageControl(0.3);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.hardware.driveLeftBanner.get() || !Robot.hardware.driveRightBanner.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
