package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberCycleSpeed extends Command {

	private Climber climber;
	
    public ClimberCycleSpeed() {
    	//requires(Robot.climber);		disabled require to smooth transition
    	climber = Robot.climber;
    }

    protected void initialize() {
    	climber.cycleClimberSpeedType();
    	System.out.println("cycled");
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
