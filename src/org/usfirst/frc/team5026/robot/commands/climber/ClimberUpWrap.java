package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberUpWrap extends Command {

    private Climber climber;

    public ClimberUpWrap() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
        climber.stopClimb();
    }

    protected void execute() {
        climber.wrapClimb();
    }

    protected boolean isFinished() {
        return climber.hasResistance();
    }

    protected void end() {
        climber.stopClimb();
        System.out.println("CLIMBER WRAP END");

    }

    protected void interrupted() {
        end();
        System.out.println("CLIMBER WRAP INTERRUPTED");
    }
}
