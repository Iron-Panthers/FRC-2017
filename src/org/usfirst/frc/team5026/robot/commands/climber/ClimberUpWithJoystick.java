package org.usfirst.frc.team5026.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

public class ClimberUpWithJoystick extends Command {

	private Climber climber;
	
    public ClimberUpWithJoystick() {
        requires(Robot.climber);
        climber = Robot.climber;
    }

    protected void initialize() {
    	climber.stopClimb();
    }

    protected void execute() {
    	//modifies climbing joystick to fit curve in climbScaling()
    	
    	climber.setClimbMotors(climber.climbScaling());
    }

    protected boolean isFinished() {
    	return false;
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
