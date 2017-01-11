package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {

	public ArcadeDrive(){
		requires(Robot.drive);
	}
	protected void initialize(){
		Robot.drive.stopDriveMotors();
	}
	protected void execute(){
		Robot.drive.useArcadeDrive(Robot.hardware.stick);
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	protected void end(){
		Robot.drive.stopDriveMotors();
	}

}
