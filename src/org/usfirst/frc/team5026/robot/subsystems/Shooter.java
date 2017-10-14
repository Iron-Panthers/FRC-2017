package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.shooter.JoystickShooterSpeed;
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
	private double wantedPower = 0;
	
    // Initialize your subsystem here
    public Shooter() {
    	super("shooter", Constants.SHOOTER_P, Constants.SHOOTER_I, Constants.SHOOTER_D);
    	
    	encoder = Robot.hardware.shooterEncoder;
    	motor = Robot.hardware.shooterMotor;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new JoystickShooterSpeed());
    }
    
    public void setPower(double power) {
    	wantedPower = Constants.SHOOTER_JOYSTICK_TO_RATE;
    }
    
    public void stop() {
    	wantedPower = 0;
    }

    protected double returnPIDInput() {
        return encoder.getRate() - wantedPower;
    }

    protected void usePIDOutput(double output) {
        motor.set(output);
    }
}
