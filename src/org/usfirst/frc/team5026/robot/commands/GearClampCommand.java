package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.GearClamp;

import edu.wpi.first.wpilibj.command.Command;

public class GearClampCommand extends Command{

	private GearClamp gearclamp;
	
	public GearClampCommand(){
		requires(Robot.gearclamp);
	}
	protected void initialize(){
		Robot.gearclamp.lowerClamp();
	}
	protected void execute(){
		if(gearclamp.hasGear()){
			Robot.gearclamp.elevateClamp();
		}
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
