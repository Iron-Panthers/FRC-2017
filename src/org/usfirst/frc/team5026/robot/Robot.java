
package org.usfirst.frc.team5026.robot;

import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDoNothing;
import org.usfirst.frc.team5026.robot.commands.autonomous.AutoDriveDistanceMotionProfilingInches;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoBlueDriveCarveLeftToPegFromLoadingZone;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoBlueDriveCarveLeftToPegFromLoadingZoneBanner;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoBlueDriveCarveLeftToPegFromLoadingZoneWithGyro;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoBlueDriveCarveRightToPegFromBoiler;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoBlueDriveCarveRightToPegFromBoilerBanner;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoBlueDriveCarveRightToPegFromBoilerWithGyro;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoRedDriveCarveLeftToPegFromBoiler;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoRedDriveCarveLeftToPegFromBoilerBanner;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoRedDriveCarveLeftToPegFromBoilerWithGyro;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoRedDriveCarveRightToPegFromLoadingZone;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoRedDriveCarveRightToPegFromLoadingZoneBanner;
import org.usfirst.frc.team5026.robot.commands.autonomous.sequences.AutoRedDriveCarveRightToPegFromLoadingZoneWithGyro;
import org.usfirst.frc.team5026.robot.commands.drive.DriveTurnXDegrees;
import org.usfirst.frc.team5026.robot.commands.misc.JoystickChoose;
import org.usfirst.frc.team5026.robot.subsystems.Climber;
import org.usfirst.frc.team5026.robot.subsystems.Drive;
import org.usfirst.frc.team5026.robot.subsystems.GearClamp;
import org.usfirst.frc.team5026.robot.subsystems.GroundGear;
import org.usfirst.frc.team5026.robot.subsystems.Intake;
import org.usfirst.frc.team5026.util.Constants;
import org.usfirst.frc.team5026.util.Hardware;
import org.usfirst.frc.team5026.util.JoystickType;

import edu.wpi.first.wpilibj.CameraServer;
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
	public static Intake intake;
	public static GroundGear groundgear;
	
	Command autoCommand;
	public static SendableChooser <Command> autoChooser = new SendableChooser<>();
	public static SendableChooser <Command> joyChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		hardware = new Hardware();
		SmartDashboard.putData(Scheduler.getInstance());
		joyChooser.addDefault("Red Joystick", new JoystickChoose(JoystickType.RED));
		// The name should be joystick type, the object is: new JoystickChoose(proper joystick type);
		joyChooser.addObject("Blue Joystick", new JoystickChoose(JoystickType.BLUE));
		joyChooser.addObject("Spinny Joystick", new JoystickChoose(JoystickType.SPINNY));
		SmartDashboard.putData("Joystick Type", joyChooser);
		initSubsystems();
		SmartDashboard.putData(climber);
		displayMods();
		CameraServer camera = CameraServer.getInstance();
//		camera.startAutomaticCapture("cam1", 0);
	}
	
	private void initSubsystems() {
		drive = new Drive();
		climber = new Climber();
		gearclamp = new GearClamp();
		groundgear = new GroundGear();
		intake = new Intake();
		oi.mapButtonBoard();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Scheduler.getInstance().removeAll();
		
		SmartDashboard.putBoolean("Running", false);
		
		autoChooser.addDefault("Nothing", new AutoDoNothing());
		// Everytime u write a new auto, do autoChooser.addObject("NAME OF AUTO", new AUTOCOMMAND);
		// Do that here
		autoChooser.addObject("Both: Middle peg", new AutoDriveDistanceMotionProfilingInches("Auto Mid Left", "Auto Mid Right", Constants.AUTO_MIDDLE_TARGET_COUNT));
		autoChooser.addObject("Red: Right peg", new AutoRedDriveCarveLeftToPegFromBoiler());
		autoChooser.addObject("Red: Left peg", new AutoRedDriveCarveRightToPegFromLoadingZone());
		autoChooser.addObject("Blue: Right peg", new AutoBlueDriveCarveLeftToPegFromLoadingZone());
		autoChooser.addObject("Blue: Left peg", new AutoBlueDriveCarveRightToPegFromBoiler());
		autoChooser.addObject("Red: Right peg with Banner", new AutoRedDriveCarveLeftToPegFromBoilerBanner());
		autoChooser.addObject("Red: Left peg with Banner", new AutoRedDriveCarveRightToPegFromLoadingZoneBanner());
		autoChooser.addObject("Blue: Right peg with Banner", new AutoBlueDriveCarveLeftToPegFromLoadingZoneBanner());
		autoChooser.addObject("Blue: Left peg with Banner", new AutoBlueDriveCarveRightToPegFromBoilerBanner());
		autoChooser.addObject("Red: Right peg with Gyro", new AutoRedDriveCarveLeftToPegFromBoilerWithGyro());
		autoChooser.addObject("Red: Left peg with Gyro", new AutoRedDriveCarveRightToPegFromLoadingZoneWithGyro());
		autoChooser.addObject("Blue: Right peg with Gyro", new AutoBlueDriveCarveLeftToPegFromLoadingZoneWithGyro());
		autoChooser.addObject("Blue: Left peg with Gyro", new AutoBlueDriveCarveRightToPegFromBoilerWithGyro());
		autoChooser.addObject("Auto Drop Gear", new AutoDriveDistanceMotionProfilingInches("Auto Drop Gear Left","Auto Drop Gear Right", Constants.AUTO_MIDDLE_TARGET_COUNT));
		autoChooser.addObject("Auto Gyro Loop", new DriveTurnXDegrees(60, false));
		autoChooser.addObject("Auto Gyro Loop -", new DriveTurnXDegrees(-60, false));
		
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
		
		drive.setBrakeMode(false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		joyChooser.getSelected().start();
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
		drive.setBrakeMode(true);
		SmartDashboard.putBoolean("Running", true);
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
		drive.setBrakeMode(true);
		SmartDashboard.putBoolean("Running", true);
	}
	private void displayMods() {
		sDisplay("GroundGear P", 3.2);
		sDisplay("GroundGear D", 0.032);
	}
	private void sDisplay(String n, double v) {
		SmartDashboard.putNumber(n, v);
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putBoolean("Left Banner", hardware.driveLeftBanner.get());
		SmartDashboard.putBoolean("Right banner", hardware.driveRightBanner.get());
		SmartDashboard.putNumber("Pot", hardware.pot.get());  //ground = 0.22, on to peg = 0.55, inside robot = 0.87
		
		
		
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
