package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.climber.ClimberRappel;
import org.usfirst.frc.team5026.robot.commands.climber.ClimberSequence;
import org.usfirst.frc.team5026.robot.commands.drive.DriveShiftGear;
import org.usfirst.frc.team5026.robot.commands.drive.DriveSwapForwards;
import org.usfirst.frc.team5026.robot.commands.gear.GearClampCommand;
import org.usfirst.frc.team5026.robot.commands.gear.GearUnClampCommand;
import org.usfirst.frc.team5026.robot.commands.groundgear.GroundGearElevationControlSequence;
import org.usfirst.frc.team5026.robot.commands.groundgear.GroundGearIntake;
import org.usfirst.frc.team5026.robot.commands.groundgear.GroundGearOuttake;
import org.usfirst.frc.team5026.robot.commands.misc.GroundGearScoreGearWhileDrivingBackward;
import org.usfirst.frc.team5026.util.GroundGearElevationState;
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
	public Button boardButton9;
	public Button boardButton10;
	public Button boardButton11;
	
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
	    boardButton9 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_9);
	    boardButton10 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_10);
	    boardButton11 = new JoystickButton(buttonBoard, RobotMap.BOARD_BUTTON_11);
	    
	    driveButton1 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_1); //TODO: Add RobotMappings
	    driveButton2 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_2);
	    driveButton3 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_3);
	    driveButton4 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_4);
	    driveButton5 = new JoystickButton(driveJoystick, RobotMap.DRIVE_BUTTON_5);
	}
	public void mapButtonBoard() {
		driveButton1.whileHeld(new DriveSwapForwards());
		driveButton3.whenPressed(new DriveShiftGear());
		driveButton4.whenPressed(new GroundGearScoreGearWhileDrivingBackward());
//		driveButton2.whileHeld(new LEDSignalGear());
		
		//Attack 3 Joystick Mapping
		boardButton1.whileHeld(new ClimberSequence());
		boardButton2.whileHeld(new GroundGearOuttake());
		boardButton3.whileHeld(new GroundGearIntake());
//		boardButton4.whenPressed(new GroundGearStop()); //donut use
		boardButton4.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.Lowered)); //redundancy bc blue joystick button6 doesn't register
		boardButton5.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.Legal));
		boardButton6.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.Lowered));
		boardButton7.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.DriveMode));
		boardButton8.whileHeld(new ClimberRappel());
//		boardButton8.whenPressed(new GroundGearIntakeWithBannerElevation());
		boardButton9.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.Scoring));
		boardButton10.whenPressed(new GearUnClampCommand());
		boardButton11.whenPressed(new GearClampCommand());
//		boardButton11.whenPressed(new GroundGearElevationControlSequence()); // This does a toggle
//		boardButton10.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.DriveMode));
//		boardButton12.whenPressed(new GroundGearIntakeWithBannerElevation());
		
		
		//GamePad Joystick Mapping
//		//boardButton1.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.Lowered));
//		boardButton2.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.Legal));
//		boardButton3.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.DriveMode));
//		boardButton4.whenPressed(new GroundGearElevationControlSequence(GroundGearElevationState.Scoring));
//		boardButton5.whileHeld(new GroundGearOuttake());
//		boardButton6.whileHeld(new GroundGearIntake());
//		boardButton7.whenPressed(new GearUnClampCommand());
//		boardButton8.whenPressed(new GearClampCommand());
//		boardButton1.whileHeld(new ClimberSequence());
//		boardButton10.whileHeld(new ClimberRappel());		
		
/*//		boardButton1.whileHeld(new ClimberSequence());
//		boardButton2.whenPressed(new ClimberCycleSpeed());
		boardButton2.whileHeld(new ClimberClimbSpeed());
//		boardButton4.whileHeld(new IntakeIn());
//		boardButton5.whileHeld(new IntakeOut());
		boardButton6.whenPressed(new GearClampCommand());
		boardButton7.whenPressed(new GearUnClampCommand());
		boardButton8.whileHeld(new ClimberRappel());
		boardButton9.whenPressed(new AutoCallCurrent());*/
	}
}
