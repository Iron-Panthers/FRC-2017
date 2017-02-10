package org.usfirst.frc.team5026.robot.commands.drive;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveSwapForwards extends Command {

    protected void initialize() {
    	Robot.oi.driveJoystick.goingForward = false;
    }

    protected boolean isFinished() {
        return false;
    }
    protected void end() {
    	Robot.oi.driveJoystick.goingForward=true;
    }
}
