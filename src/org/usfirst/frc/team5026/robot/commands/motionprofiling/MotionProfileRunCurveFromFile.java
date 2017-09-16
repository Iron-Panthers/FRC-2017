package org.usfirst.frc.team5026.robot.commands.motionprofiling;

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
	TrajectoryPoint[] lefts;
	TrajectoryPoint[] rights;
	
	int lindex = 0;
	int rindex = 0;

    public MotionProfileRunCurveFromFile() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	TrajectoryPoint[][] overall = MotionProfileWritePath.readFile(SmartDashboard.getString("MP Save File", "/home/lvuser/Path.mpp"));
    	
    	lefts = overall[0];
    	rights = overall[1];
    	
    	Robot.drive.left.setupMotionProfileMode();
    	Robot.drive.right.setupMotionProfileMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	while (!Robot.drive.left.getEncMotor().isMotionProfileTopLevelBufferFull() && lindex < lefts.length) {
    		Robot.drive.left.getEncMotor().pushMotionProfileTrajectory(lefts[lindex]);
    		lindex++;
    	}
    	while (!Robot.drive.right.getEncMotor().isMotionProfileTopLevelBufferFull() && rindex < rights.length) {
    		Robot.drive.right.getEncMotor().pushMotionProfileTrajectory(rights[rindex]);
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
    	return mStatusL.activePoint == lefts[lefts.length - 1] && mStatusR.activePoint == rights[rights.length - 1];
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
