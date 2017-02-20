package org.usfirst.frc.team5026.util;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.SpeedController;


public class MotorGroup implements SpeedController {
	
	public SpeedController[] motors;
	public CANTalon encoderMotor;
	private double speed;
	
	public MotorGroup(boolean[] isInverted, CANTalon encoderMotor, SpeedController... otherMotors) {
		this.motors = new SpeedController[otherMotors.length + 1];
		this.motors[0] = encoderMotor;
		for (int i = 1; i < this.motors.length; i++) {
			this.motors[i] = otherMotors[i-1];
		}
		
		this.encoderMotor = encoderMotor;
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
	
	public int getEncPosition() {
		return this.encoderMotor.getEncPosition();
	}
}