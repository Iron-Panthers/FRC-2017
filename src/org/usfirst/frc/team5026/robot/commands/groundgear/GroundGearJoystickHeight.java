package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GroundGearJoystickHeight extends Command {

    public GroundGearJoystickHeight() {
        requires(Robot.groundgear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.groundgear.stopLift();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.groundgear.lift(Robot.oi.buttonBoard.getY());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.boardButton1.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
