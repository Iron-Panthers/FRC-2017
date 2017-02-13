package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;
import org.usfirst.frc.team5026.util.ClimberSpeedType;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberUpClimb extends Command {

    private Climber climber;

    public ClimberUpClimb() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
        climber.stopClimb();
        System.out.println("Climb");
    }

    protected void execute() {
    	climber.climbScaling();
    }

    protected boolean isFinished() {
        return climber.hasResistance();
    }

    protected void end() {
        climber.stopClimb();
    }

    protected void interrupted() {
        end();
        System.out.println("CLIMBER INTERRUPTED");
    }
}
