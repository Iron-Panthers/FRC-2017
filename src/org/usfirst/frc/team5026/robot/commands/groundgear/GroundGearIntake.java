package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;

public class GroundGearIntake extends Command{
	boolean runAndEnd = true;

	public GroundGearIntake() {
//		requires(Robot.groundgear);
	}
	public GroundGearIntake(boolean runAndEnd) {
		this.runAndEnd = runAndEnd;
	}
	@Override
	protected void initialize(){
		Robot.groundgear.intakeGear();
	}
	protected void execute(){
	}
	protected boolean isFinished() {
		if (!runAndEnd) {
			//return false;
			return Robot.groundgear.hasGear(); // This will end either if the button is released, or the ground gear gets the gear.
			// If the button is released, interrupt is called. Otherwise, end is called
		}
		return false;
	}
	protected void end(){
		if (!runAndEnd)
		Robot.groundgear.stopIntake();
		// Only stops if you want to end
	}
	protected void interrupt(){
	}

}
