package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Constants;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

public class ClimberUpLatch extends Command {

    private Climber climber;

    public ClimberUpLatch() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.climber);
        climber = Robot.climber;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        climber.stopClimb();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        climber.setClimbMotors(Constants.CLIMBER_LATCH);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        climber.stopClimb();
        System.out.println("CLIMBER LATCH END");

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
        System.out.println("CLIMBER LATCH INTERRUPTED");
    }
}
