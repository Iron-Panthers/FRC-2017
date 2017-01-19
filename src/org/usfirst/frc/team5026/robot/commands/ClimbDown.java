package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbDown extends Command {

    public ClimbDown() {
    	requires(Robot.climb);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climb.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climb.climbDown(0.4);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climb.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}