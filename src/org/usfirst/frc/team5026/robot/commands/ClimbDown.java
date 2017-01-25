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

    protected void initialize() {
    	Robot.climb.stop();
    }

    protected void execute() {
    	double climbSpeed = 0.4;
    	Robot.climb.climbDown(climbSpeed);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.climb.stop();
    }

    protected void interrupted() {
    	end();
    }
}
