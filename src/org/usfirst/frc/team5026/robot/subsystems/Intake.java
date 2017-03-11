package org.usfirst.frc.team5026.robot.subsystems;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Talon intakeMotor;
	
	public Intake() {
		intakeMotor = Robot.hardware.intake;
	}
	public void intake() {
		intakeMotor.set(Constants.INTAKE_INTAKE_SPEED);
	}
	public void outtake() {
		intakeMotor.set(Constants.INTAKE_OUTTAKE_SPEED);
	}
	public void stop() {
		intakeMotor.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

