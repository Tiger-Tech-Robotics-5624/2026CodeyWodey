// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.RobotContainer;
// import frc.robot.subsystems.DriveSubsystem;

// public class DriveCommand extends Command {
//   /** Creates a new DriveCommand. */
//   DriveSubsystem driveSub;
//   public DriveCommand(DriveSubsystem drive) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     driveSub = drive;
//     addRequirements(driveSub);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}

//   // Called every time the scheduler runs while the command is scheduled.
// shaq will not break ever :)
//   @Override
//   public void execute() {
//     driveSub.drive(RobotContainer.m_driverController.getLeftY(), RobotContainer.m_driverController.getRightY(), 0.00);
    
//     // driveSub.pidTestStart(RobotContainer.xboxController.getXButton(), RobotContainer.xboxController.getYButton());
//     // driveSub.pidTest();
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {}

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }