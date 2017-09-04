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
		return Robot.groundgear.hasGear(); // This will end either if the button is released, or the ground gear gets the gear.
		// If the button is released, interrupt is called. Otherwise, end is called
	}
	protected void end(){
		Robot.groundgear.stopIntake();
	}
	protected void interrupt(){
		Robot.groundgear.stopIntake();
	}

}
