package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Shooter extends PIDSubsystem {

	private Encoder encoder;
	private Talon motor;
	
    // Initialize your subsystem here
    public Shooter() {
    	super("shooter", Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D);
    	
    	encoder = Robot.hardware.shooterEncoder;
    	motor = Robot.hardware.shooterMotor;
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        return encoder.getRate();
    }

    protected void usePIDOutput(double output) {
        motor.set(output);
    }
}
