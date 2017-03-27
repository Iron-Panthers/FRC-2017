package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GroundGearLift extends Command{

	public GroundGearLift() {
		requires(Robot.groundgear);
		requires(Robot.gearclamp);
	}
	@Override
	protected void initialize(){
		Robot.groundgear.lift();
		SmartDashboard.putString("Ground Gear State: ", "Lifted");
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
