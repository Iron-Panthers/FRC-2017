package org.usfirst.frc.team5026.robot.commands.gear;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.CanGearClampsMove;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearUnClampCommand extends Command {

    public GearUnClampCommand() {
        requires(Robot.gearclamp);
    }

    protected void initialize() {
		if(CanGearClampsMove.checkMovement(Robot.gearclamp)){
			Robot.gearclamp.lowerClamp();
			SmartDashboard.putString("Upper Gear Clamp State: ", "Open");
		}
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
