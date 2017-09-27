package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GroundGearStayElevation extends Command {
	
	private double potVal;
	private static double p = 0.05;

    public GroundGearStayElevation() {
        requires(Robot.groundgear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.potVal = Robot.hardware.pot.get();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = Robot.hardware.pot.get() - this.potVal;
    	double power = error * p;
    	Robot.groundgear.setLiftPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.groundgear.setLiftPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.groundgear.setLiftPower(0);
    }
}
