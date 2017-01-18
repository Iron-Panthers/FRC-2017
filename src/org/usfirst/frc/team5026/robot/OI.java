package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.MoveMotorBackward;
import org.usfirst.frc.team5026.robot.commands.MoveMotorForward;
import org.usfirst.frc.team5026.robot.commands.TurnRobot90DegreesClockwise;
import org.usfirst.frc.team5026.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	public PantherJoystick buttonBoard;
	
	public Button boardButton1;
	public Button boardButton2;
	public Button boardButton3;
	
	public OI() {
		buttonBoard = new PantherJoystick(RobotMap.DRIVE_JOYSTICK);
		
		initButtonBoard();
	}
	public void initButtonBoard() {
		boardButton1 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_1);
		boardButton2 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_2);
		boardButton3 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_3);
	}
	public void mapButtonBoard() {
		boardButton1.whileHeld(new MoveMotorForward());
		boardButton2.whileHeld(new MoveMotorBackward());
		boardButton3.whenPressed(new TurnRobot90DegreesClockwise());
	}
}
