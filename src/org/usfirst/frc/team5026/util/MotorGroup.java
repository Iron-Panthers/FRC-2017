package org.usfirst.frc.team5026.util;

import edu.wpi.first.wpilibj.SpeedController;


public class MotorGroup implements SpeedController {
	
	public SpeedController[] motors;
	private double speed;
	
	public MotorGroup(boolean[] isInverted, SpeedController... motors) {
		this.motors = motors;
		this.speed = 0;
		for (int i = 0; i < motors.length; i++) {
			motors[i].setInverted(isInverted[i]);
		}
	}
	
	@Override
	public void pidWrite(double output) {
		for (SpeedController m: motors) {
			m.pidWrite(output);
		}
	}
	
	@Override
	public double get() {
		return speed;
	}
	
	@Override
	public void set(double speed) {
		this.speed = speed;
		for (SpeedController m: motors) {
			m.set(speed);
		}
	}

	@Override
	public void setInverted(boolean isInverted) {
		// TODO: FIX
	}

	@Override
	public boolean getInverted() {
		return false;
	}

	@Override
	public void disable() {
		for (SpeedController m: motors) {
			m.disable();
		}
	}

	@Override
	public void stopMotor() {
		for (SpeedController m: motors) {
			m.stopMotor();
		}
	}
}