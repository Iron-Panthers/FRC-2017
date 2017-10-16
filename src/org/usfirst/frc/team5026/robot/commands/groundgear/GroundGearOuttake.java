package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GroundGearOuttake extends Command{

	public GroundGearOuttake() {
//		requires(Robot.groundgear);
	}
	@Override
	protected void initialize(){
		Robot.groundgear.outtakeGear();
	}
	protected void execute(){
	}
	protected boolean isFinished() {
		return false;
	}
	protected void end(){
		Robot.groundgear.stopIntake();
	}
	protected void interrupted(){
		Robot.groundgear.stopIntake();
	}

}
