package org.usfirst.frc.team5026.robot.commands.groundgear;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GroundGearIntake extends Command{

	public GroundGearIntake() {
		requires(Robot.groundgear);
	}
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
