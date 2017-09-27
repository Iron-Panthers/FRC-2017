package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GroundGearElevationState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GroundGearSetElevation extends Command {

	private static final double potThreshold = 0.08;
	private static final double movePower = 0.3;
	
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
    	double delta = deltaToTargetState();
    	
    	if (delta < 0) {
    		Robot.groundgear.setLiftPower(-movePower);
    	} else {
    		Robot.groundgear.setLiftPower(movePower);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double delta = deltaToTargetState();
        
        SmartDashboard.putNumber("PotDelta", delta);
        
        if (Math.abs(delta) < potThreshold) { //go into robot more
        	Robot.groundgear.elevationState = targetState;
        	SmartDashboard.putString("PotLimit", "reached");
			return true;
        } else {
        	return false;
        }
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
    
    
    private double deltaToTargetState() {
		return Robot.hardware.pot.get() - targetState.potValue;
    }
}
