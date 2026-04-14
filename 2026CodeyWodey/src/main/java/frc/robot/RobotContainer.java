// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OperatorConstants;
//import frc.robot.commands.ShaqCommand;
// import frc.robot.autos.DriveForwardAuto;
// import frc.robot.autos.SimpleCoralAuto;
import frc.robot.commands.ShooterCommand;
//import frc.robot.subsystems.ShaqSubsystem;
// import frc.robot.commands.DriveCommand;
// import frc.robot.commands.DriveCommand;
// import frc.robot.subsystems.DriveSubsystem;
// import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import com.pathplanner.lib.auto.NamedCommands;
// import edu.wpi.first.math.controller.ProfiledPIDController;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.geometry.Translation2d;
// import edu.wpi.first.math.trajectory.TrapezoidProfile.Constraints;
// import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.Filesystem;
// import edu.wpi.first.wpilibj.RobotBase;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
// import edu.wpi.first.wpilibj2.command.button.Trigger;
// import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.SwerveSubsystem;
// import java.io.File;
import swervelib.SwerveInputStream;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

  // The robot's subsystems and commands are defined here...
  // Replace with CommandPS4Controller or CommandJoystick if needed
  public static CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.DRIVER_CONTROLLER_PORT);
  // You can remove this if you wish to have a driver, note that you
  // may have to change the binding for left bumper.
  public final CommandXboxController m_operatorController = 
      new CommandXboxController(OperatorConstants.OPERATOR_CONTROLLER_PORT);

  // The autonomous chooser
  SendableChooser<Command> m_chooser = new SendableChooser<>();
// 
// Big mac double cheeeseburger quarter pounder with fries and a large diet coke please. no onions.
// if hungry = true, create [doubleCheeseburger];else if thirsty = false, holdtheshake;
  private final SwerveSubsystem drivebase  = new SwerveSubsystem();
  // public final DriveSubsystem driveSub = new DriveSubsystem();
//   public final ClimberSubsystem m_climber = new ClimberSubsystem();
  public final ShooterSubsystem m_shooter = new ShooterSubsystem();
  public final ShooterSubsystem m_shooterHelper = new ShooterSubsystem(Constants.ShooterConstants.SHOOTERHELPER_ID);
  //public final ShaqSubsystem m_shaq = new ShaqSubsystem();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Set up command bindings
    configureBindings();
    // Set the options to show up in the Dashboard for selecting auto modes. If you
    // add additional auto modes you can add additional lines here with
    // autoChooser.addOption
    // m_chooser.setDefaultOption("Coral Auto", m_simpleCoralAuto);
    // m_chooser.addOption("Drive Forward Auto", m_driveForwardAuto);
    SmartDashboard.putData(m_chooser);
    
    drivebase.setDefaultCommand(driveFieldOrientedAngularVelocity);
    DriverStation.silenceJoystickConnectionWarning(true);
    NamedCommands.registerCommand("test", Commands.print("I EXIST"));
  }

   SwerveInputStream driveAngularVelocity = SwerveInputStream.of(drivebase.getSwerveDrive(),
                                 () -> m_driverController.getLeftY() * -1.5,
                                 () -> m_driverController.getLeftX() * -1.5)
    .withControllerRotationAxis(m_driverController::getRightX)
    .deadband(OperatorConstants.DEADBAND) 
    .scaleTranslation(0.8)
    .allianceRelativeControl(false);


 SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(m_driverController::getRightX,
                                m_driverController::getRightY)
.headingWhile(true);

  Command driveFieldOrientedDirectAngle = drivebase.driveFieldOriented(driveDirectAngle);
  Command driveFieldOrientedAngularVelocity = drivebase.driveFieldOriented(driveAngularVelocity);

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //m_shooter.setDefaultCommand(new ShooterCommand(m_shooter));
    m_operatorController.rightTrigger().whileTrue(new ShooterCommand(m_shooter));
    m_operatorController.leftTrigger().whileTrue(new ShooterCommand(m_shooterHelper));
   // m_operatorController.x().whileTrue(new ShaqCommand(m_shaq, true));
   // m_operatorController.y().whileTrue(new ShaqCommand(m_shaq, false));
    // driveSub.setDefaultCommand(new DriveCommand(driveSub));
    /** 
     * Set the default command for the drive subsystem to an instance of the
     * DriveCommand with the values provided by the joystick axes on the driver
     * controller. The Y axis of the controller is inverted so that pushing the
     * stick away from you (a negative value) drives the robot forwards (a positive
     * value). Similarly for the X axis where we need to flip the value so the
     * joystick matches the WPILib convention of counter-clockwise positive
     */
    // m_drive.setDefaultCommand(new DriveCommand(m_drive,
    //     () -> -m_driverController.getLeftY(),
    //     () -> -m_driverController.getRightX(),
    //     () -> true));

    /**
     * Holding the left bumper (or whatever button you assign) will multiply the speed
     * by a decimal to limit the max speed of the robot -> 
     * 1 (100%) from the controller * .9 = 90% of the max speed when held (we also square it)
     * 
     * Slow mode is very valuable for line ups and the deep climb 
     * 
     * When switching to single driver mode switch to the B button
     */
    // m_driverController.leftBumper().whileTrue(new DriveCommand(m_drive, 
    //     () -> -m_driverController.getLeftY() * DriveConstants.SLOW_MODE_MOVE,  
    //     () -> -m_driverController.getRightX() * DriveConstants.SLOW_MODE_TURN,
    //     () -> true));


    //here - - - - - - - - - -  - - - - - - - - - operations
    /**
     * Here we declare all of our operator commands, these commands could have been
     * written in a more compact manner but are left verbose so the intent is clear.
     */
    //m_operatorController.rightBumper().whileTrue(new AlgaeInCommand(m_roller));
    
    // // Here we use a trigger as a button when it is pushed past a certain threshold
    //m_operatorController.rightTrigger(.2).whileTrue(new AlgaeOutCommand(m_roller));

    // /**
    //  * The arm will be passively held up or down after this is used,
    //  * make sure not to run the arm too long or it may get upset!
    //  */
    //m_operatorController.leftBumper().whileTrue(new ArmUpCommand(m_arm));
    //m_operatorController.leftTrigger(.4).whileTrue(new ArmDownCommand(m_arm));

    // /**
    //  * Used to score coral, the stack command is for when there is already coral
    //  * in L1 where you are trying to score. The numbers may need to be tuned, 
    //  * make sure the rollers do not wear on the plastic basket.
    //  */
    // m_operatorController.y().whileTrue(new CoralStackCommand(m_roller));

    // /**
    //  * POV is a direction on the D-Pad or directional arrow pad of the controller,
    //  * the direction of this will be different depending on how your winch is wound
    //  */
    // m_operatorController.pov(0).whileTrue(new ClimberUpCommand(m_climber));
    // m_operatorController.pov(180).whileTrue(new ClimberDownCommand(m_climber));


//      m_driverController.b().whileTrue(
//          drivebase.driveToPose(
//              new Pose2d(new Translation2d(4, 4), Rotation2d.fromDegrees(0)))
//                              );


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return (Command) drivebase.driveForward();
  }

  public Command getAutonomousShooterHelper() {
    return (Command) m_shooterHelper.shoot().withTimeout(3);
  }

  public Command getAutonomousShoot() {
    return (Command) m_shooter.shoot().withTimeout(3);
  }
}