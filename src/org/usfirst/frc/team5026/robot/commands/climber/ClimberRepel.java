package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberRepel extends Command {
	
	public Climber climber;
	
    public ClimberRepel() {
    	requires(Robot.climber);
    	climber = Robot.climber;
    }

    protected void initialize() {
    	climber.stopClimb();
    }

    protected void execute() {
    	climber.repel();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	climber.stopClimb();
    }

    protected void interrupted() {
    	end();
    }
}
