package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class ClimbUp extends Command {

    public ClimbUp() {
        requires(Robot.climb);
    }

    protected void initialize() {
    	Robot.climb.stop();
    }

    protected void execute() {
    	Robot.climb.climbUp(1);
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
