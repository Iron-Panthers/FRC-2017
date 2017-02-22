package org.usfirst.frc.team5026.robot.commands.misc;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LEDSignalGear extends Command {

    public LEDSignalGear() {
    }

    protected void initialize() {
    	Robot.hardware.led.setColor(Constants.LED_GEAR_RELEASE);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.hardware.led.cycleStates();
    }
}
