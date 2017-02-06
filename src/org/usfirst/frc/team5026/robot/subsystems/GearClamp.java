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
    //Clamps
  } 
  public void lowerClamp(){ 
    hardware.gearClampPiston.set(Value.kReverse); 
    //Unclamps
  } 
  public boolean hasGear(){ 
    return Robot.hardware.gearClampSensor.get();
    //Banner sensor on the gear clamp that detects whether there's a gear in it
  } 
   
} 