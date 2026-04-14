// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import frc.robot.Constants;
// import frc.robot.subsystems.ShaqSubsystem;
// import edu.wpi.first.wpilibj2.command.Command;

// /** A CoralOutCommand that uses a roller subsystem. */
// public class ShaqCommand extends Command {
//   private final ShaqSubsystem m_roller;
//   private boolean forward;

//   /**
//    * Use to score coral into L1.
//    *
//    * @param roller The subsystem used by this command.
//    */
//   public ShaqCommand(ShaqSubsystem roller, boolean Forward) {
//     forward = Forward;
//     m_roller = roller;
//     addRequirements(roller);
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {}

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     if (forward == true)
//     {
//       m_roller.runRoller(Constants.SHAQ_SPEED);
//     }
//     else
//     {
//       m_roller.runRoller(-Constants.SHAQ_SPEED);
//     }
//   }

//   // Called once the command ends or is interrupted. Ensures the roller
//   // is not running after we let go of the button. 
//   @Override
//   public void end(boolean interrupted) {
//     m_roller.runRoller(0);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return false;
//   }
// }