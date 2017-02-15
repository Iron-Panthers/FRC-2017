package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

public class ClimberStop extends Command {

	private Climber climber;
	
    public ClimberStop() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
    }

    protected void execute() {
    	climber.stopClimb();
    	System.out.println("STOPPED");
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

