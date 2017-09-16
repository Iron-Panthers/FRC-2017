package org.usfirst.frc.team5026.robot.commands.motionprofiling;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team5026.robot.Robot;
import org.usfirst.frc.team5026.util.motionprofile.MotionProfileWritePath;

import com.ctre.CANTalon.TrajectoryPoint;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MotionProfileBuildCurveFromPush extends Command {

	List<TrajectoryPoint> lefts;
	List<TrajectoryPoint> rights;
	
    public MotionProfileBuildCurveFromPush() {
        lefts = new ArrayList<TrajectoryPoint>();
        rights = new ArrayList<TrajectoryPoint>();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.left.setBrakeMode(false);
    	Robot.drive.right.setBrakeMode(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	addPoint(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // check button
    }

    // Called once after isFinished returns true
    protected void end() {
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	// It is a whileheld, so this will happen to end it
    	addPoint(true);
    	MotionProfileWritePath.writeFile(lefts, rights, SmartDashboard.getString("MP Save File", "/home/lvuser/Path.mpp"));
    	Robot.drive.left.setBrakeMode(true);
    	Robot.drive.right.setBrakeMode(true);
    }
    private void addPoint(boolean last) {
    	// last point added
    	TrajectoryPoint left = new TrajectoryPoint();
    	left.position = Robot.drive.left.getEncPosition();
    	left.profileSlotSelect = 0;
    	left.timeDurMs = 10; // 10 ms, must be larger than 10 ms
    	left.velocity = Robot.drive.left.getEncMotor().getSpeed();
    	left.isLastPoint = last;
    	lefts.add(left);
    	TrajectoryPoint right = new TrajectoryPoint();
    	right.position = Robot.drive.right.getEncPosition();
    	right.profileSlotSelect = 0;
    	right.timeDurMs = 10;
    	right.velocity = Robot.drive.right.getEncMotor().getSpeed();
    	right.isLastPoint = last;
    	rights.add(right);
    }
}
