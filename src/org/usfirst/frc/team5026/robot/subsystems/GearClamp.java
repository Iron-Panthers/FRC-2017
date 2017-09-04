	package org.usfirst.frc.team5026.robot.subsystems; 
 
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GearOpenable;
import org.usfirst.frc.team5026.util.Hardware;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value; 
 
public class GearClamp extends GearOpenable {
 
	  private Hardware hardware; 
	  // TODO: Add states here
	  public GearClamp(){ 
		  hardware = Robot.hardware; 
	  } 
	  @Override 
	  protected void initDefaultCommand() { 
		  // TODO Auto-generated method stub 
	  
	  } 
	 
	  public void clampOnGear(){ 
		  hardware.gearClampPiston.set(Value.kForward);
		  //Clamps
		  isOpen = false;
	  } 
	  public void lowerClamp(){ 
		  hardware.gearClampPiston.set(Value.kReverse);
		  //Unclamps
		  isOpen = true;
	  }
	  public boolean hasGear(){ 
		  return Robot.hardware.gearClampSensor.get();
		  //Banner sensor on the gear clamp that detects whether there's a gear in it
	  } 
   
} 