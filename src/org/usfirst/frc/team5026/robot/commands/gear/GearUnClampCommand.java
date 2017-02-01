package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearUnClampCommand extends Command {

    public GearUnClampCommand() {
        requires(Robot.gearclamp);
    }

    protected void initialize() {
    	Robot.gearclamp.lowerClamp();
    	//Unclamps
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
