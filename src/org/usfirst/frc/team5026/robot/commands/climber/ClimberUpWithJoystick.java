package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberUpWithJoystick extends Command {

	private Climber climber;
	
    public ClimberUpWithJoystick() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
    	climber.stopClimb();
    }

    protected void execute() {
    	climber.scaledClimb();
    }

    protected boolean isFinished() {
    	return climber.hasResistance();
    }

    protected void end() {
    	climber.stopClimb();
    	System.out.println("JOY CLIMBER END");
 
    }

    protected void interrupted() {
   		end();
   		System.out.println("JOY CLIMBER INTERRUPTED");
    }
}
