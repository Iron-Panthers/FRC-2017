package org.usfirst.frc.team5026.robot.commands.drive;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.TimedCommand;

public class DriveDrivebaseForTime extends TimedCommand {

	private double left, right;
	
	public DriveDrivebaseForTime(double left, double right, double time) {
		super(time);
		requires(Robot.drive);
		this.left = left;
		this.right = right;
	}
	
	// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setLeftRightMotors(left, right);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stopMotors();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
	

}
