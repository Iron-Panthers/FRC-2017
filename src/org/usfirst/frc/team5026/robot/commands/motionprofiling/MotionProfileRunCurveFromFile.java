package org.usfirst.frc.team5026.robot.commands.motionprofiling;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.motionprofile.MotionProfileWritePath;

import com.ctre.CANTalon;
import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

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
    	
    	System.out.println(lefts);
    	
    	Robot.drive.left.setupMotionProfileMode();
    	Robot.drive.right.setupMotionProfileMode();
    	lindex = 0;
    	rindex = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	MotionProfileStatus mStatus = new MotionProfileStatus();
    	Robot.drive.left.getEncMotor().getMotionProfileStatus(mStatus);
    	System.out.println("Lefts Index: "+lindex+" is "+lefts.get(lindex)+" with pos: "+lefts.get(lindex).position);
    	while (!Robot.drive.left.getEncMotor().isMotionProfileTopLevelBufferFull() && lindex < lefts.size()-1) {
    		Robot.drive.left.getEncMotor().pushMotionProfileTrajectory(lefts.get(lindex));
    		lindex++;
    	}
    	while (!Robot.drive.right.getEncMotor().isMotionProfileTopLevelBufferFull() && rindex < rights.size()-1) {
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
    	System.out.println(mStatusL.activePoint.position+"\t"+mStatusR.activePoint.position);
    	SmartDashboard.putNumber("CANTalon buffer size", Robot.drive.left.getEncMotor().getMotionProfileTopLevelBufferCount());
    	Robot.drive.right.getEncMotor().getMotionProfileStatus(mStatusR);
    	SmartDashboard.putNumber("Active point pos", mStatusR.activePoint.position);
    	SmartDashboard.putNumber("Active point velocity", mStatusR.activePoint.velocity);
    	SmartDashboard.putBoolean("Active point is valid", mStatusR.activePointValid);
//    	return true;
    	return Robot.drive.left.getEncMotor().getMotionProfileTopLevelBufferCount() <= 2 && Robot.drive.right.getEncMotor().getMotionProfileTopLevelBufferCount() <= 2;

//    	return lefts.size() == 0 && rights.size() == 0;
//    	return mStatusL.activePoint.position == lefts.get(lefts.size() - 1).position && mStatusR.activePoint.position == rights.get(rights.size() - 1).position;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Done with path");
    	Robot.drive.left.getEncMotor().set(CANTalon.SetValueMotionProfile.Disable.value);
    	Robot.drive.right.getEncMotor().set(CANTalon.SetValueMotionProfile.Disable.value);
    	Robot.drive.left.getEncMotor().disable();
    	Robot.drive.right.getEncMotor().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Interrtuped path!");
    	Robot.drive.left.getEncMotor().set(CANTalon.SetValueMotionProfile.Disable.value);
    	Robot.drive.right.getEncMotor().set(CANTalon.SetValueMotionProfile.Disable.value);
    	Robot.drive.left.getEncMotor().disable();
    	Robot.drive.right.getEncMotor().disable();
    }
}
