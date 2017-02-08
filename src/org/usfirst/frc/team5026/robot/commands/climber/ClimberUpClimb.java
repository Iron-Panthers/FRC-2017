package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberUpClimb extends Command {

    private Climber climber;
    private double speed;

    public ClimberUpClimb(double speed) {
        requires(Robot.climber);
        climber = Robot.climber;
        this.speed = speed;
    }

    protected void initialize() {
        climber.stopClimb();
        System.out.println("Climb");
    }

    protected void execute() {
    	climber.setClimbMotors(speed);
    }

    protected boolean isFinished() {
        return climber.hasResistance();
    }

    protected void end() {
        climber.stopClimb();
        System.out.println("CLIMBER END");

    }

    protected void interrupted() {
        end();
        System.out.println("CLIMBER INTERRUPTED");
    }
}
