package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberSlow extends Command {

	public Climber climber;
	
    public ClimberSlow() {
    	requires(Robot.climber);
    	climber = Robot.climber;
    }

    protected void initialize() {
    	climber.stopClimb();
    	System.out.println("Slow");
    }

    protected void execute() {
    	climber.slowClimb();
    }

    protected boolean isFinished() {
        return climber.hasResistance();
    }

    protected void end() {
    	climber.stopClimb();
    }

    protected void interrupted() {
    	end();
    }
}
