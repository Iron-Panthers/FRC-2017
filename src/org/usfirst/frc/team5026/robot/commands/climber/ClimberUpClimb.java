package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

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
        climber.setClimbMotors(Constants.CLIMBER_CLIMB_SPEED);
        climber.update();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        climber.stopClimb();
        System.out.println("CLIMBER CLIMB END");

    }

    protected void interrupted() {
        end();
        System.out.println("CLIMBER CLIMB INTERRUPTED");
    }
}
