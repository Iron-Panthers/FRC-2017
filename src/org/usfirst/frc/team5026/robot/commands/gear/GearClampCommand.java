package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GearClampCommand extends Command{

	
	public GearClampCommand(){
		requires(Robot.gearclamp);
	}
	protected void initialize(){
	}
	protected void execute(){
		Robot.gearclamp.clampOnGear();
		//Clamps the gear
	}
	protected boolean isFinished() {
		return Robot.gearclamp.hasGear();
		//Command ends when it has the gear
	}
	protected void end() {
	}
	protected void interrupted() {
		Robot.gearclamp.lowerClamp();
	}

}
