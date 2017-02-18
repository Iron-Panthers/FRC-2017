package org.usfirst.frc.team5026.robot.commands.intake;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeOut extends Command {

	private Intake intake;
	
    public IntakeOut() {
        requires(Robot.intake);
        intake = Robot.intake;
    }

    protected void initialize() {
    	intake.stop();
    }

    protected void execute() {
    	intake.outtake();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	intake.stop();
    }

    protected void interrupted() {
    }
}
