package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
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
        System.out.println("Climber");
    }

    protected void execute() {
    	climber.climbScaling();
    }

    protected boolean isFinished() {
        return !Robot.oi.boardButton1.get();
    }

    protected void end() {
        climber.stopClimb();
    }

    protected void interrupted() {
    }
}
