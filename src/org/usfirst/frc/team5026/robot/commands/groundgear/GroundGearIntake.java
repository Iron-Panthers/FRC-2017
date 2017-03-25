package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;

public class GroundGearIntake extends Command{

	public GroundGearIntake() {
		requires(Robot.groundgear);
		requires(Robot.gearclamp);
	}
	@Override
	protected void initialize(){
		Robot.groundgear.lift();
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
		if(CanGearClampsMove.checkMovement(Robot.groundgear, Robot.gearclamp)){
			Robot.groundgear.drop();
		}
	}

}
