package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.ClimbDown;
import org.usfirst.frc.team5026.robot.commands.ClimbUp;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	  public PantherJoystick joystick; 
	  public Button climbUp; 
	  public Button climbDown;
	   
	  public OI() { 
		  joystick = new PantherJoystick(RobotMap.driveJoystick); 
		  initButtons(); 
	  } 
	   
	  public void initButtons() { 
		  climbUp = new JoystickButton(joystick, 2);
		  climbDown = new JoystickButton(joystick, 3);
	  } 
	   
	  public void mapButtons() { 
		  climbUp.whileHeld(new ClimbUp());
		  climbDown.whileHeld(new ClimbDown());
	  } 
}
