package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

public class ClimberUpClimb extends Command {

    private Climber climber;
    private boolean initial;

    public ClimberUpClimb(boolean initial) {
        requires(Robot.climber);
        climber = Robot.climber;
        this.initial = initial;
    }

    public ClimberUpClimb() {
        this(false); //default check for resistance
    }

    protected void initialize() {
        climber.stopClimb();
        System.out.println("Climber");
    }

    protected void execute() {
    	climber.climbScaling();
    }

    protected boolean isFinished() {
        if (initial) {
            return !Robot.oi.boardButton1.get(); // No resistance check
        } else {
            return climber.hasResistance() || !Robot.oi.boardButton1.get();
        }
    }

    protected void end() {
        climber.stopClimb();
    }

    protected void interrupted() {
    }
}
