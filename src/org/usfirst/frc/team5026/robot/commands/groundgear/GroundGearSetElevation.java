package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GroundGearElevationState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GroundGearSetElevation extends Command {
	
	private GroundGearElevationState targetState;
	
    public GroundGearSetElevation(GroundGearElevationState targetState) {
        requires(Robot.groundgear);
        this.targetState = targetState;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.groundgear.elevationState = targetState;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
