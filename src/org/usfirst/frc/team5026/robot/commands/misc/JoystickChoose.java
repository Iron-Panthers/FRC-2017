package org.usfirst.frc.team5026.robot.commands.misc;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.JoystickType;

import edu.wpi.first.wpilibj.command.Command;

public class JoystickChoose extends Command {

	// Add instance variables
	private JoystickType joystickType;
	
	public JoystickChoose(JoystickType joystickType) {
		// Requires nothing
		// Pass in joystick type and set it to instance variable
		this.joystickType = joystickType;
	}
	protected void initialize() {
		Robot.oi.driveJoystick.setJoystickType(joystickType); // pass in joystick type
		Robot.oi.buttonBoard.setJoystickType(joystickType);
		System.out.println(joystickType.toString());
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
