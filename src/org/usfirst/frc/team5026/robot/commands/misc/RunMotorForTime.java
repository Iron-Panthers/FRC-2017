package org.usfirst.frc.team5026.robot.commands.misc;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 *
 */
public class RunMotorForTime extends TimedCommand {
	
	private SpeedController motor;
	private double power;
	
    public RunMotorForTime(SpeedController motor, double power, double time) {
    	super(time);
    	this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	motor.set(this.power);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after isFinished returns true
    protected void end() {
    	motor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
