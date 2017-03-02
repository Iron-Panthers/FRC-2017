
package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.AutoDriveStraightWithGyro;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDoNothing;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoSequenceDriveStraightTurn_A_lot;
import org.usfirst.frc.team5026.robot.commands.autonomous.DriveSequenceCheckErrorInDistance;
import org.usfirst.frc.team5026.robot.commands.autonomous.DriveStraightForSetDistance;
import org.usfirst.frc.team5026.robot.commands.drive.DriveDrivebaseForTime;
import org.usfirst.frc.team5026.robot.commands.drive.DriveTurnXDegrees;
import org.usfirst.frc.team5026.robot.subsystems.Climber;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.robot.subsystems.GearClamp;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.Hardware;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static Hardware hardware;
	public static Drive drive;
	public static GearClamp gearclamp;
	public static Climber climber;

	Command autoCommand;
	SendableChooser <Command> autoChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		hardware = new Hardware();
		initSubsystems();
		
		SmartDashboard.putNumber(Constants.DRIVE_DISTANCE_RAMP_SMD_NAME, 150);
		SmartDashboard.putNumber(Constants.DRIVE_TURNXDEGREES_NAME, 0);
	}
	
	private void initSubsystems() {
		drive = new Drive();
		climber = new Climber();
		gearclamp = new GearClamp();
		oi.mapButtonBoard(); // Must be last line
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		
		autoChooser.addDefault("Nothing", new AutoDoNothing());
		// Everytime u write a new auto, do autoChooser.addObject("NAME OF AUTO", new AUTOCOMMAND);
		// Do that here
		autoChooser.addObject("Drive forward, than back", new AutoSequenceDriveStraightTurn_A_lot());
		autoChooser.addObject("Drive Distance Encoder Error Test", new DriveSequenceCheckErrorInDistance());
		autoChooser.addObject("Drive for 5 seconds", new DriveDrivebaseForTime(0.5, 0.5, 5));
		autoChooser.addObject("Drive straight for set distance", new DriveStraightForSetDistance(12));
		autoChooser.addObject("Turn x degrees", new DriveTurnXDegrees());
		autoChooser.addObject("Drive w gyro and ec", new AutoDriveStraightWithGyro(120, 3));
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		autoCommand = autoChooser.getSelected();
		autoCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
   	
}
