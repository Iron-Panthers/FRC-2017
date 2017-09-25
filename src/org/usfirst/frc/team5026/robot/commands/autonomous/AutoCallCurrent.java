package org.usfirst.frc.team5026.robot.commands.autonomous;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoCallCurrent extends Command {

	Command c;
	
    public AutoCallCurrent() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	c = Robot.autoChooser.getSelected();
    	c.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.oi.boardButton1.get()) {
    		interrupted();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !c.isRunning();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	c.cancel();
    }
}
