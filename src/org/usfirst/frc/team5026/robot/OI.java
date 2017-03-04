package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.drive.DriveTurnXDegrees;
import org.usfirst.frc.team5026.robot.commands.gear.GearClampCommand;
import org.usfirst.frc.team5026.robot.commands.gear.GearUnClampCommand;
import org.usfirst.frc.team5026.robot.commands.intake.IntakeIn;
import org.usfirst.frc.team5026.robot.commands.intake.IntakeOut;
import org.usfirst.frc.team5026.robot.commands.misc.LEDSignalGear;
import org.usfirst.frc.team5026.util.PantherJoystick;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public PantherJoystick buttonBoard;
	public PantherJoystick driveJoystick;
	
	public Button driveButton1; 
	public Button driveButton2; 
	public Button driveButton3; 
	public Button driveButton4; 
	public Button driveButton5; 
	public Button driveButton6;
	public Button driveButton7;
	public Button driveButton8;
	
	public Button boardButton1; 
	public Button boardButton2; 
	public Button boardButton3; 
	public Button boardButton4; 
	public Button boardButton5; 
	public Button boardButton6;
	public Button boardButton7;
	public Button boardButton8;
	
	public OI() {
		driveJoystick = new PantherJoystick(RobotMap.DRIVE_JOYSTICK);
		buttonBoard = new PantherJoystick(RobotMap.BUTTON_JOYSTICK);

		initButtonBoard();
	}
	public void initButtonBoard() {
		boardButton1 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_1); 
	    boardButton2 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_2); 
	    boardButton3 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_3); 
	    boardButton4 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_4); 
	    boardButton5 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_5); 
	    boardButton6 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_6);
	    boardButton7 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_7);
	    boardButton8 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_8);
		
	    driveButton1 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_1); //TODO: Add RobotMappings
	    driveButton2 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_2);
	    driveButton3 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_3);
	    driveButton4 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_4);
	    driveButton5 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_5);
	}
	public void mapButtonBoard() {
		boardButton2.whenPressed(new GearClampCommand());
		boardButton3.whenPressed(new GearUnClampCommand());
		boardButton4.whenPressed(new DriveTurnXDegrees());
	}
}
