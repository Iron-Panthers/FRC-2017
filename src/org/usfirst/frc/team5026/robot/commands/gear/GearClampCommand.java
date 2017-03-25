package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;

public class GearClampCommand extends Command{

	
	public GearClampCommand(){
		requires(Robot.gearclamp);
		requires(Robot.groundgear);
	}
	protected void initialize(){
		
	}
	protected void execute(){
	}
	protected boolean isFinished() {
		return Robot.gearclamp.hasGear();
		//Command ends when it has the gear
	}
	protected void end() {
		Robot.gearclamp.clampOnGear();
		//Clamps the gear
	}
	protected void interrupted() {
		if(CanGearClampsMove.checkMovement(Robot.groundgear, Robot.gearclamp)){
			Robot.gearclamp.lowerClamp();
		}
	}
}
