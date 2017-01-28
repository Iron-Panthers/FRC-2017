package org.usfirst.frc.team5026.robot.commands;

import org.usfirst.frc.team5026.robot.PantherJoystick;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class JoystickChoose extends Command {

	// Add instance variables
	
	public JoystickChoose(PantherJoystick joystick) {
		// Requires nothing
		// Pass in joystick type and set it to instance variable
	}
	protected void initialize() {
		Robot.oi.setJoystickType(); // pass in joystick type
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
}
