package org.usfirst.frc.team5026.robot;

import edu.wpi.first.wpilibj.SpeedController;


public class MotorGroup implements SpeedController {
	
	public SpeedController[] motors;
	private boolean isInverted;
	private double speed;
	
	public MotorGroup(boolean isInverted, SpeedController... motors) {
		this.motors = motors;
		this.isInverted = isInverted;
		this.speed = 0;
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
		this.isInverted = isInverted;
		for (SpeedController m: motors) {
			m.setInverted(isInverted);
		}
	}

	@Override
	public boolean getInverted() {
		return this.isInverted;
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