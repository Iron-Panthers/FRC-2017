package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

/**
 *
 */
public class ClimberCheckResistance extends Command {
	private Climber climber;
	private boolean hasResistance;

    public ClimberCheckResistance() {
        requires(Robot.climber);
        climber = Robot.climber;
        hasResistance = false;
    }

    protected void initialize() {
        hasResistance = climber.hasResistance();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return hasResistance;
    }

    protected void end() {
    }

    protected void interrupted() {
        end();
    }
}
