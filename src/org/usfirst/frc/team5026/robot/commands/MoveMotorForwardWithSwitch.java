package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.TalonMotorWithSwitch;

import edu.wpi.first.wpilibj.command.Command;

public class MoveMotorForwardWithSwitch extends Command {

	private TalonMotorWithSwitch talonMotorSwitch;
	
    public MoveMotorForwardWithSwitch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.talonMotorSwitch);
        talonMotorSwitch = Robot.talonMotorSwitch;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	talonMotorSwitch.stopMotor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	talonMotorSwitch.setMotorPositive();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	talonMotorSwitch.stopMotor();
    }
}
