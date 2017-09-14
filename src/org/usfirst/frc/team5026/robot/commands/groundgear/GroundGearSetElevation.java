package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GroundGearElevationState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    	SmartDashboard.putString("PotLimit", "start");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.groundgear.moveTowardsState(targetState);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double delta = Robot.groundgear.deltaToTargetState(targetState);
        
        SmartDashboard.putNumber("PotDelta", delta);
        
        if (Math.abs(delta) < 0.03) { //go into robot more
        	Robot.groundgear.elevationState = targetState;
        	SmartDashboard.putString("PotLimit", "reached");
			return true;
        } else {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.groundgear.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.groundgear.stopIntake();
    }
}
