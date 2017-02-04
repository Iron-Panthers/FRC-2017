package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

public class ClimberStop extends Command {

	private Climber climber;
	
    public ClimberStop() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
    	climber.stopClimb();
    	System.out.println("Stop");
    }

    protected void execute() {
    	climber.stopClimb();
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

