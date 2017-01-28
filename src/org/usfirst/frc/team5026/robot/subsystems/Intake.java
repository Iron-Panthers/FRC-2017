package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    public Intake()
    {
    	
    }
    
    public void intake()
    {
    	Robot.hardware.intake.set(Constants.INTAKE_SPEED);
    }
    public void outtake()
    {
    	Robot.hardware.intake.set(Constants.OUTTAKE_SPEED);
    }
    public void stop()
    {
    	Robot.hardware.intake.set(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

