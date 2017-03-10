
package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.AutoDriveStraightWithGyro;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDoNothing;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistancePosition;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoGearSequence_TopPos;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoSequenceDriveStraightTurn_A_lot;
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
		Scheduler.getInstance().removeAll();

		autoChooser.addDefault("Nothing", new AutoDoNothing());
		// Everytime u write a new auto, do autoChooser.addObject("NAME OF AUTO", new AUTOCOMMAND);
		// Do that here
		autoChooser.addObject("Auto sequence: mid position start (Uses PID)", new AutoDriveDistancePosition(Constants.AUTO_MIDDLE_TARGET_LEFT, Constants.AUTO_MIDDLE_TARGET_RIGHT));
		autoChooser.addObject("Auto carve to peg", new AutoDriveDistancePosition(-11.5, -8));
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
		Robot.drive.endPositionDrive();
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
