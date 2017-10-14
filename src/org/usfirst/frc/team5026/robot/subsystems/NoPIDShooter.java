package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.commands.shooter.FlatShooterSpeed;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class NoPIDShooter extends Subsystem {

	private Talon motor;
	
    // Initialize your subsystem here
    public NoPIDShooter() {    	
    	motor = Robot.hardware.shooterMotor;
    }

    public void initDefaultCommand() {
    }

    public void setPower(double power) {
    	System.out.println(power);
    	motor.set(power);
    }

    public void stop() {
        motor.set(0);
    }
}

