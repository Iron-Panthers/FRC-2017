package org.usfirst.frc.team5026.robot.commands.motionprofiling;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.motionprofile.MotionProfileBufferer;
import org.usfirst.frc.team5026.util.motionprofile.MotionProfilePath;
import org.usfirst.frc.team5026.util.motionprofile.MotionProfileProcessor;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TrajectoryPoint;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionProfileRun extends Command {
	MotionProfileBufferer mpb;
	MotionProfileProcessor proc;
	
    public MotionProfileRun(MotionProfilePath motp) {
        requires(Robot.drive); // Only to make sure no driving happens while doing this.
        proc = new MotionProfileProcessor(Robot.drive);
        proc.setpath(motp);
        mpb = new MotionProfileBufferer(Robot.drive.left, Robot.drive.right, Constants.MOTION_PROFILE_LOOK_DISTANCE);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.left.setupMotionProfileMode();
    	Robot.drive.right.setupMotionProfileMode();
    	
    	mpb.fillTalon(proc, Robot.drive.left);
    	mpb.fillTalon(proc, Robot.drive.right);
    	
//    	ArrayList<ArrayList<TrajectoryPoint>> traj = mpp.smallPath.getTrajectoryPoints();
//    	lefts = traj.get(0);
//    	rights = traj.get(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drive.updatePosition(Constants.DELTA_TIME);
    	
    	Robot.drive.left.motionProfileControl();
    	Robot.drive.right.motionProfileControl();
    	CANTalon.MotionProfileStatus statusL = new CANTalon.MotionProfileStatus();
    	CANTalon.MotionProfileStatus statusR = new CANTalon.MotionProfileStatus();
    	Robot.drive.left.getEncMotor().getMotionProfileStatus(statusL);
    	Robot.drive.right.getEncMotor().getMotionProfileStatus(statusR);
    	if (statusL.btmBufferCnt == 2 && statusR.btmBufferCnt <= 2) {
    		// Refill the buffer from current spot
    		// Also rezero and create offset to use
    		mpb.fillTalon(proc, Robot.drive.left);
    		mpb.fillTalon(proc, Robot.drive.right);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.left.getEncMotor().set(CANTalon.SetValueMotionProfile.Disable.value);
    	Robot.drive.right.getEncMotor().set(CANTalon.SetValueMotionProfile.Disable.value);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
