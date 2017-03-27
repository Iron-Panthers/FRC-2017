package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;

public class GroundGearIntake extends Command{

	public GroundGearIntake() {
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
