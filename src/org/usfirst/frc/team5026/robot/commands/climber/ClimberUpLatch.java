package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

public class ClimberUpLatch extends Command {

    private Climber climber;

    public ClimberUpLatch() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
        climber.stopClimb();
    }

    protected void execute() {
        climber.setClimbMotors(Constants.CLIMBER_LATCH);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        climber.stopClimb();
        System.out.println("CLIMBER LATCH END");

    }

    protected void interrupted() {
        end();
        System.out.println("CLIMBER LATCH INTERRUPTED");
    }
}
