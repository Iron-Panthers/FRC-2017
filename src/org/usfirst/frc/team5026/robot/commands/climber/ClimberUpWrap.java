package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

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
        climber.setClimbMotors(Constants.CLIMBER_WRAP_SPEED);
        climber.update();
    }

    protected boolean isFinished() {
        return false;	//return false so long as its not while held
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
