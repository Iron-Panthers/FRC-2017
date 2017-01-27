package org.usfirst.frc.team5026.robot.commands.drive;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.GearPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveShiftGear extends Command {

	private GearPosition pos;
	
    public DriveShiftGear(GearPosition pos) {
        requires(Robot.drive);
        this.pos = pos;
    }

    protected void initialize() {
    	Robot.drive.setGear(pos);
    }

    protected boolean isFinished() {
        return true;
    }
    
}
