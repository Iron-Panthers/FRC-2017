package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climb extends Subsystem {

    private Talon leftClimb;
    private Talon rightClimb;
    
	public Climb()
	{
		leftClimb = Robot.hardware.climbLeft;
		rightClimb = Robot.hardware.climbRight;
	}
	
	public void climbUp(double motorSpeed)
	{
		leftClimb.set(motorSpeed);
		rightClimb.set(-motorSpeed);
	}
	public void climbDown(double motorSpeed)
	{
		leftClimb.set(-motorSpeed);
		rightClimb.set(motorSpeed);
	}
	public void stop()
	{
		leftClimb.set(0.0d);
		rightClimb.set(0.0d);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

