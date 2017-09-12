package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GroundGearElevationState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GroundGearElevationControl extends Command {
	GroundGearElevationState target;
	boolean init = false;

    public GroundGearElevationControl(GroundGearElevationState target) {
        requires(Robot.groundgear);
        this.target = target;
        init = true;
    }

    public GroundGearElevationControl() {
    	requires(Robot.groundgear);
    	init = false;
    	target = GroundGearElevationState.Legal;
	}

	// Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.groundgear.setup();
    	Robot.groundgear.getElevationMotor().enable();
    	if (!init) {
    		if (target == GroundGearElevationState.Lowered) {
    			target = GroundGearElevationState.Scoring;
    		} else {
    			target = GroundGearElevationState.Lowered;
    		}
    		SmartDashboard.putString("GroundGearTargetState", target.toString());
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean allowed = true;
//    	if (target == GroundGearElevationState.Legal) {
//    		allowed = true;
//    	} else {
//    		allowed = CanGearClampsMove.checkMovement(Robot.groundgear, Robot.gearclamp);
//    	}
    	if (allowed) {
    		// Safe error checking, checks gear movement before continuing
    		Robot.groundgear.travelToState(target);
    	}
    	else {
    		// If there is illegal movement, should not be done! Cancels the command (calls interrupted)
    		this.cancel();
    	}
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
    	// Perhaps move back to previous state?
    	// For now, just stop
    	// Notable issue: If trying to intake before fully in correct state, state movement will cancel!
//    	Robot.groundgear.stopLift();
    }
}
