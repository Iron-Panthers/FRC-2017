package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GroundGearStayElevation extends Command {
	
	private static double p;
	private static double d;
	private static double lastError;

    public GroundGearStayElevation() {
        requires(Robot.groundgear);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	p = SmartDashboard.getNumber("GroundGear P", 3.2);
    	d = SmartDashboard.getNumber("GroundGear D", 0.032);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = Robot.groundgear.elevationState.potValue - Robot.hardware.pot.get();
    	double dEE = (error - lastError) / 2;
    	double power;
    	
    	if (error < 0) {
    		power = error * p + dEE * d - Constants.GROUND_GEAR_LIFT_DEADZONE;
    	} else {
    		power = error * p + dEE * d + Constants.GROUND_GEAR_LIFT_DEADZONE;
    	}
    	
    	SmartDashboard.putNumber("pot error", error);
    	SmartDashboard.putNumber("pot power", power);
    	
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
