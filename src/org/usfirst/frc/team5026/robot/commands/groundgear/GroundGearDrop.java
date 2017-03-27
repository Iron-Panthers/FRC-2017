package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GroundGearDrop extends Command{

	public GroundGearDrop() {
		requires(Robot.groundgear);
		requires(Robot.gearclamp);
	}
	@Override
	protected void initialize(){
		if(CanGearClampsMove.checkMovement(Robot.groundgear, Robot.gearclamp)){
			Robot.groundgear.drop();
			SmartDashboard.putString("Ground Gear State: ", "Dropped");
		}
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

