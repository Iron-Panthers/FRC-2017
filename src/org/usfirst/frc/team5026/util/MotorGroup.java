package org.usfirst.frc.team5026.util;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.SpeedController;


public class MotorGroup implements SpeedController {
	
	public CANTalon[] motors;
	private CANTalon encoderMotor;
	private double speed;
	
	public MotorGroup(boolean[] isInverted, CANTalon encoderMotor, CANTalon... otherMotors) {
		this.motors = new CANTalon[otherMotors.length + 1];
		this.motors[0] = encoderMotor;
		for (int i = 1; i < this.motors.length; i++) {
			this.motors[i] = otherMotors[i-1];
		}
		
		this.encoderMotor = encoderMotor;
		this.speed = 0;
		for (int i = 0; i < motors.length; i++) {
			motors[i].setInverted(isInverted[i]);
		}
		setupTalons(isInverted, Constants.PIDF, Constants.PID_PROFILE);
	}
	public void setupTalons(boolean[] inverted, double[] pidf, int pidProfile) {
		int encTicks = Constants.ENCODER_TICKS_PER_ROTATION;
		
		// This can be found in Sec. 17.2.3 in the TalonSRX Software Manual
    	encoderMotor.setProfile(pidProfile);
		encoderMotor.setF(pidf[3]);
    	encoderMotor.setP(pidf[0]);
    	encoderMotor.setI(pidf[1]);
    	encoderMotor.setD(pidf[2]);
    	// 12.8
    	encoderMotor.setMotionMagicCruiseVelocity(Constants.MAX_VELOCITY);
    	encoderMotor.setMotionMagicAcceleration(Constants.MAX_ACCELERATION);

    	// Sec. 17.2.3 (Software Reference Manual)
    	encoderMotor.reverseSensor(inverted[0]); // First talon is encTalon
    	if (encTicks == 4096) {
    		// For a quadature encoder (Versaplanetary encoders)
    		encoderMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
    		encoderMotor.configEncoderCodesPerRev((int) (0.25*encTicks)); // 1/4 * 4096 (CAUSE 4096 IS THE NUMBER OF TICKS PER REV. MEASURED. F U MANUAL (check position changes (big number in selftest))
    	} else {
    		// TODO: Add Grayhill Encoder mappings
    		encoderMotor.setFeedbackDevice(FeedbackDevice.AnalogEncoder);
    		encoderMotor.configEncoderCodesPerRev(encTicks);
    	}
    	encoderMotor.setPosition(0);
		encoderMotor.disable();
		
		for (int i = 0; i < motors.length; i++) {
			// Get Encoder port, make sure everything else is follower
	    	// Sec. 12.1.3 (Software Reference Manual)
			motors[i].setForwardSoftLimit(+15.0);
			motors[i].setReverseSoftLimit(-15.0);
			motors[i].configNominalOutputVoltage(+0.0f, -0.0f);
	    	motors[i].configPeakOutputVoltage(+12.0f, -12.0f);
	    	if (i != 0) {
	    		// Make sure all of the other Talons are followers of the encoder talon
		    	motors[i].setProfile(pidProfile);
		    	motors[i].changeControlMode(TalonControlMode.Follower);
		    	motors[i].reverseOutput(inverted[i]);
		    	motors[i].set(encoderMotor.getDeviceID()); // Follows the encoder CANTalon
	    	}
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
		encoderMotor.enable();
		encoderMotor.set(speed);
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
	
	public CANTalon getEncMotor() {
		return encoderMotor;
	}
}