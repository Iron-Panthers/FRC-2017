package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimberCycleSpeed extends Command {

	private Climber climber;
	private boolean cycleType;	//when true, cycle increases. When false, cycle decreases.
	
    public ClimberCycleSpeed(boolean cycleType) {
    	requires(Robot.climber);
    	climber = Robot.climber;
    	this.cycleType = cycleType;
    }

    protected void initialize() {
    	climber.cycleClimberSpeedType(cycleType);
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
