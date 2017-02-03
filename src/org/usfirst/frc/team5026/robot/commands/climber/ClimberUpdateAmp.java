package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberUpdateAmp extends Command {

	private Climber climber;
	
    public ClimberUpdateAmp() {
    	requires(Robot.climber);
    	climber = Robot.climber;
    }

    protected void initialize() {
    	climber.pollMotorOutput();
    }

    protected void execute() {
    	climber.pollMotorOutput();
    	climber.update();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	climber.update();    	
    }

    protected void interrupted() {
    	end();
    }
}
