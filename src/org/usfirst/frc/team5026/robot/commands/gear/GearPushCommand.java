package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearPushCommand extends Command {

    public GearPushCommand() {
    	requires(Robot.gearclamp);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gearclamp.pullGear();
    	setTimeout(Constants.PUSH_PEG_PISTON_WAIT_TIME);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gearclamp.pushGear();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return isTimed
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gearclamp.pullGear();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
