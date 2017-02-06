package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.GearClamp;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

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
