package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.GroundGearElevationState;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GroundGearWaitForStabilize extends Command {
	
	private boolean complete = false;
	private double lastExponentialError = 100000;
	private double currentExponentialError = 100000;
	
	private CANTalon motor;
	
	private double alpha = 0.30; // 0 < alpha < 1
	
	private int range; // When to say we are complete
	
	private GroundGearElevationState target;
	
	private int count;
	private int maxCount;

    public GroundGearWaitForStabilize(GroundGearElevationState target) {
        requires(Robot.groundgear);
        this.target = target;
        SmartDashboard.putBoolean("GroundGearMovement Complete", false);
        motor = Robot.groundgear.getElevationMotor();
        maxCount = Constants.GROUND_GEAR_STABILISATION_MAX_COUNT;
        count = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	SmartDashboard.putBoolean("GroundGearMovement Complete", false);
    	complete = false;
    	range = Math.abs(Constants.GROUND_GEAR_TICK_TOLERANCE);
    	
    	lastExponentialError = 100;
    	currentExponentialError = 100;
    	// Resets errors
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double motorOut = motor.getOutputVoltage() / motor.getBusVoltage();
    	SmartDashboard.putNumber("GroundGearTarget", target.rotations);
    	SmartDashboard.putNumber("GroundGearSpeed", motor.getSpeed());
    	SmartDashboard.putNumber("GroundGearError", motor.getClosedLoopError());
        SmartDashboard.putNumber("GroundGearOutput", motorOut);
        SmartDashboard.putNumber("GroundGearPosition", motor.getPosition());
        SmartDashboard.putNumber("GroundGearCurrentExp.Error", currentExponentialError);
    	currentExponentialError = alpha * Math.abs(motor.getClosedLoopError()) + (1-alpha) * lastExponentialError;
    	if (Math.abs(motor.getClosedLoopError()) < range) {
    		count++;
    	}
    	complete = count >= maxCount;
//    	if (Math.abs(currentExponentialError) < range) {
//    		// Stable!
//    		System.out.println("Stable GroundGear!: "+currentExponentialError+"<"+range);
//    		SmartDashboard.putBoolean("GroundGearMovement Complete", true);
//    		complete = true;
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return complete;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Complete!");
    	Robot.groundgear.stopLift();
    	Robot.groundgear.setElevationState(target);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("INTERRUPTED!");
    	Robot.groundgear.stopLift();
    }
}
