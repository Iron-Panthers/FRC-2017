package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.JoystickType;
import org.usfirst.frc.team5026.robot.Robot;

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
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
