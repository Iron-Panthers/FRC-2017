package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GearClampCommand extends Command{

	
	public GearClampCommand(){
		// Would there ever be a situation where requiring groundgear here is a good idea?
		// It just stops the elevation, so it would still be out of state, better to let it finish, than to require it
		requires(Robot.gearclamp);
//		requires(Robot.groundgear);
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
		SmartDashboard.putString("Upper Gear Clamp State: ", "Clamped");
		//Clamps the gear
	}
	protected void interrupted() {
	}
}
