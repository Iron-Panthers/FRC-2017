package org.usfirst.frc.team5026.robot.commands.climber;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ClimberPsuedoSequence extends Command {

	private Climber climber;
	
	private Command c = new WaitCommand(1);
	private ClimberStop cs = new ClimberStop();
	private ClimberUpClimb cuc = new ClimberUpClimb();
	private ClimberUpClimbInitial cuci = new ClimberUpClimbInitial();
	
    public ClimberPsuedoSequence() {
    	requires(Robot.climber);
    	climber = Robot.climber;
    }

    protected void initialize() {
    	cuci.start();		//runs ClimberUpClimbInitial()
		c.start(); 			//runs wait command for 1 second
		System.out.println("STAGE ONE");
    }

    protected void execute() {
    	System.out.println(c.isRunning());
    	System.out.println(cs.isFinished());
		if(!c.isRunning() && !cuc.isRunning()) {
			cuci.end();
			cuci.cancel();		// fail safe
			cuc.start();		//runs ClimberUpClimb()
			System.out.println("STAGE TWO");
			if(!cs.isRunning() && cuc.isFinished()) {
				cs.start();			//runs ClimberStop()
				cs.isFinished();
				System.out.println("STAGE THREE");
			}
		}
	}

    protected boolean isFinished() {
    	 return cs.isFinished();
    }

    protected void end() {
    	climber.stopClimb();
    }

    protected void interrupted() {
    }
}
