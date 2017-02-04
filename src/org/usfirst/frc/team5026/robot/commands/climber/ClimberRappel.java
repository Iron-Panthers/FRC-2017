package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberRappel extends Command {
	
	public Climber climber;
	
    public ClimberRappel() {
    	requires(Robot.climber);
    	climber = Robot.climber;
    }

    protected void initialize() {
        System.out.println("Rappel");
    	climber.stopClimb();
    }

    protected void execute() {
    	climber.rappel();
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
