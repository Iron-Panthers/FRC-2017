package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.SpeedController;


public class MotorGroup implements SpeedController {
	
	public SpeedController[] motors;
	public boolean[] isInverted;
	
	public MotorGroup(SpeedController[] motors, boolean[] isInverted) {
		this.motors = motors;
		this.isInverted = isInverted;
		for (int i = 0; i < motors.length; i++) {
			motors[i].setInverted(isInverted[i]);
		}
	}
	
	public void set(double speed) {
		for (SpeedController m : motors)
		{
			m.set(speed);
		}
	}
	public void stop() {
		set(0);
	}

	public double get() { return 0; }
	public void pidWrite(double output) {}
	@Override
	public void setInverted(boolean isInverted) {}
	@Override
	public boolean getInverted() { return false; }
	@Override
	public void disable() {}
	@Override
	public void stopMotor() {}
}