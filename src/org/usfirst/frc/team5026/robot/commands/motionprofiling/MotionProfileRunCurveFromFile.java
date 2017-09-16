package org.usfirst.frc.team5026.robot.commands.motionprofiling;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.motionprofile.MotionProfileWritePath;

import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MotionProfileRunCurveFromFile extends Command {
	List<TrajectoryPoint> lefts;
	List<TrajectoryPoint> rights;
	
	int lindex = 0;
	int rindex = 0;

    public MotionProfileRunCurveFromFile() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ArrayList<ArrayList<TrajectoryPoint>> overall = MotionProfileWritePath.readFile(SmartDashboard.getString("MP Save File", "/home/lvuser/Path.mpp"));
    	
    	lefts = overall.get(0);
    	rights = overall.get(1);
    	
    	Robot.drive.left.setupMotionProfileMode();
    	Robot.drive.right.setupMotionProfileMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	MotionProfileStatus mStatus = new MotionProfileStatus();
    	Robot.drive.left.getEncMotor().getMotionProfileStatus(mStatus);
    	System.out.println(mStatus.activePoint.position);
    	while (!Robot.drive.left.getEncMotor().isMotionProfileTopLevelBufferFull() && lindex < lefts.size()) {
    		Robot.drive.left.getEncMotor().pushMotionProfileTrajectory(lefts.get(lindex));
    		lindex++;
    	}
    	while (!Robot.drive.right.getEncMotor().isMotionProfileTopLevelBufferFull() && rindex < rights.size()) {
    		Robot.drive.right.getEncMotor().pushMotionProfileTrajectory(rights.get(rindex));
    		rindex++;
    	}
    	Robot.drive.left.motionProfileControl();
    	Robot.drive.right.motionProfileControl();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	MotionProfileStatus mStatusL = new MotionProfileStatus();
    	MotionProfileStatus mStatusR = new MotionProfileStatus();
    	Robot.drive.left.getEncMotor().getMotionProfileStatus(mStatusL);
    	Robot.drive.right.getEncMotor().getMotionProfileStatus(mStatusR);
    	return mStatusL.activePoint.position == lefts.get(lefts.size() - 1).position && mStatusR.activePoint.position == rights.get(rights.size() - 1).position;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
