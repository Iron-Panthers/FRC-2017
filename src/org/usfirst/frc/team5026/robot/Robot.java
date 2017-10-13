
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

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
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

	// New Camera stuff!
	public static UsbCamera cam1;
	public static UsbCamera cam2;
	
	public static CvSink cvsink1;
	public static CvSink cvsink2;
	public static VideoSink server;
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
		startCamera();
	}
	private static void startCamera() {
		CameraServer camera = CameraServer.getInstance();
		cam1 = camera.startAutomaticCapture("cam0", 0);
		cam2 = camera.startAutomaticCapture("cam1", 1);
		server = camera.getServer();
		cvsink1 = new CvSink("cam1cv");
		cvsink2 = new CvSink("cam2cv");
		cvsink1.setSource(cam1);
		cvsink1.setEnabled(true);
		cvsink2.setSource(cam2);
		cvsink2.setEnabled(true);
//		server.setSource(cam1);
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
		
//		autoChooser.addDefault("Nothing", new AutoDoNothing());
//		autoChooser.addDefault("Default", new AutoBlueDriveCarveLeftToPegFromLoadingZoneWithGyro());
		// Everytime u write a new auto, do autoChooser.addObject("NAME OF AUTO", new AUTOCOMMAND);
		// Do that here
		// Our default is now the middle peg. That way we at least drive past the baseline in the case of auto failure...
		autoChooser.addDefault("Both: Middle peg", new AutoDriveDistanceMotionProfilingInches("Auto Mid Left", "Auto Mid Right", Constants.AUTO_MIDDLE_TARGET_COUNT));
		
		
//		autoChooser.addObject("Red: Right peg", new AutoRedDriveCarveLeftToPegFromBoiler());
//		autoChooser.addObject("Red: Left peg", new AutoRedDriveCarveRightToPegFromLoadingZone());
//		autoChooser.addObject("Blue: Right peg", new AutoBlueDriveCarveLeftToPegFromLoadingZone());
//		autoChooser.addObject("Blue: Left peg", new AutoBlueDriveCarveRightToPegFromBoiler());
//		autoChooser.addObject("Red: Right peg with Banner", new AutoRedDriveCarveLeftToPegFromBoilerBanner());
//		autoChooser.addObject("Red: Left peg with Banner", new AutoRedDriveCarveRightToPegFromLoadingZoneBanner());
//		autoChooser.addObject("Blue: Right peg with Banner", new AutoBlueDriveCarveLeftToPegFromLoadingZoneBanner());
//		autoChooser.addObject("Blue: Left peg with Banner", new AutoBlueDriveCarveRightToPegFromBoilerBanner());
		
		
		autoChooser.addObject("Red: Right peg with Gyro", new AutoRedDriveCarveLeftToPegFromBoilerWithGyro());
		autoChooser.addObject("Red: Left peg with Gyro", new AutoRedDriveCarveRightToPegFromLoadingZoneWithGyro());
		autoChooser.addObject("Blue: Right peg with Gyro", new AutoBlueDriveCarveLeftToPegFromLoadingZoneWithGyro());
		autoChooser.addObject("Blue: Left peg with Gyro", new AutoBlueDriveCarveRightToPegFromBoilerWithGyro());
		
		
		autoChooser.addObject("Auto Drop Gear", new AutoDriveDistanceMotionProfilingInches("Auto Drop Gear Left","Auto Drop Gear Right", Constants.AUTO_MIDDLE_TARGET_COUNT));
		autoChooser.addObject("Auto Gyro Loop", new DriveTurnXDegrees(60, false));
		autoChooser.addObject("Auto Gyro Loop -", new DriveTurnXDegrees(-60, false));
		
		SmartDashboard.putData("Autonomous Chooser", autoChooser);
		
		drive.setBrakeMode(false);
		new JoystickChoose(JoystickType.RED).start();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
//		joyChooser.getSelected().start();
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
		hardware.gyro.reset();
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
		sDisplay("Auto Rotation P", Constants.AUTO_TURN_P);
		sDisplay("Auto Rotation I", Constants.AUTO_TURN_I);
		sDisplay("Auto Angle Rotation Tolerance", Constants.AUTO_TURN_ANGLE_TOLERANCE);
		
		sDisplay("Banner Buffer", Constants.AUTO_BANNER_BUFFER);
		
		sDisplay("Auto Mid Left", Constants.AUTO_MIDDLE_TARGET_LEFT);
		sDisplay("Auto Mid Right", Constants.AUTO_MIDDLE_TARGET_RIGHT);
		
		sDisplay("Auto Boiler Close Red", Constants.AUTO_BOILER_TARGET_CLOSE_RED);
		sDisplay("Auto Boiler Far Red", Constants.AUTO_BOILER_TARGET_FAR_RED);
		sDisplay("Auto Boiler Extra Distance Red", Constants.AUTO_BOILER_AFTER_TURN_TO_PEG_RED);
		sDisplay("Auto Loading Close Red", Constants.AUTO_LOADING_TARGET_CLOSE_RED);
		sDisplay("Auto Loading Far Red", Constants.AUTO_LOADING_TARGET_FAR_RED);
		sDisplay("Auto Loading Extra Distance Red", Constants.AUTO_LOADING_AFTER_TURN_TO_PEG_RED);
		
		sDisplay("Auto Boiler Close Blue", Constants.AUTO_BOILER_TARGET_CLOSE_BLUE);
		sDisplay("Auto Boiler Far Blue", Constants.AUTO_BOILER_TARGET_FAR_BLUE);
		sDisplay("Auto Boiler Extra Distance Blue", Constants.AUTO_BOILER_AFTER_TURN_TO_PEG_BLUE);
		sDisplay("Auto Loading Close Blue", Constants.AUTO_LOADING_TARGET_CLOSE_BLUE);
		sDisplay("Auto Loading Far Blue", Constants.AUTO_LOADING_TARGET_FAR_BLUE);
		sDisplay("Auto Loading Extra Distance Blue", Constants.AUTO_LOADING_AFTER_TURN_TO_PEG_BLUE);
		
		sDisplay("Auto Drop Gear Left", Constants.AUTO_DROP_GEAR_LEFT);
		sDisplay("Auto Drop Gear Right", Constants.AUTO_DROP_GEAR_RIGHT);
		
		sDisplay("LEFT P", Constants.P_LEFT);
		sDisplay("LEFT D", Constants.D_LEFT);
		sDisplay("LEFT RAMP", Constants.RAMP_LEFT);
		sDisplay("TELEOP LEFT RAMP", Constants.TELEOP_RAMP_LEFT);
		sDisplay("RIGHT P", Constants.P_RIGHT);
		sDisplay("RIGHT D", Constants.D_RIGHT);
		sDisplay("TELEOP RIGHT RAMP", Constants.TELEOP_RAMP_RIGHT);
		sDisplay("Auto Drive Stabilization Tolerance (Ticks)", Constants.DRIVE_STABILIZATION_TOLERANCE);
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
