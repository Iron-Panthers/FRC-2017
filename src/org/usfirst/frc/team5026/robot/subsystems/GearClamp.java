package org.usfirst.frc.team5026.robot.subsystems; 
 
import org.usfirst.frc.team5026.robot.Hardware;
import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem; 
 
public class GearClamp extends Subsystem { 
 
  private Hardware hardware; 
   
  public GearClamp(){ 
    hardware = Robot.hardware; 
  } 
  @Override 
  protected void initDefaultCommand() { 
    // TODO Auto-generated method stub 
     
  } 
 
  public void elevateClamp(){ 
	  hardware.gearClampPiston.set(Value.kForward);
  } 
  public void lowerClamp(){ 
	  hardware.gearClampPiston.set(Value.kReverse); 
  } 
  public boolean hasGear(){ 
	  //banner sensor that detects whether there is a gear in the clamp
	  return Robot.hardware.gearClampSensor.get(); 
  } 
  public void pushGear(){
	  //activates piston that pushes gear onto the peg
	  hardware.gearPegPiston.set(Value.kForward);
  }
  public void pullGear(){
	  //pulls back the piston
	  hardware.gearPegPiston.set(Value.kReverse);
  }
}