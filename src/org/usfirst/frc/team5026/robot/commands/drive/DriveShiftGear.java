package org.usfirst.frc.team5026.robot.commands.drive;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.GearPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveShiftGear extends Command {

	GearPosition target;
	
    public DriveShiftGear() {
        requires(Robot.drive);
    }
    public DriveShiftGear(GearPosition s) {
    	requires(Robot.drive);
    	target = s;
    }

    protected void initialize() {
    	if (target != null) {
    		Robot.drive.setGear(target);
    	} else {
    		Robot.drive.setGear();
    	}
    	//Robot.hardware.led.cycleStates();
    }

    protected boolean isFinished() {
        return true;
    }
    
}
