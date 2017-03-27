package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GroundGearOuttake extends Command{

	public GroundGearOuttake() {
		requires(Robot.groundgear);
	}
	@Override
	protected void initialize(){
		Robot.groundgear.intakeGear();
	}
	protected void execute(){
	}
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	protected void end(){
	}
	protected void interrupt(){
	}

}
