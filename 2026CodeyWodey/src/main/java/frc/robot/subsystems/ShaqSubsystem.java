// package frc.robot.subsystems;

// import com.revrobotics.spark.SparkMax;

// import com.revrobotics.PersistMode;
// import com.revrobotics.ResetMode;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkMaxConfig;
// import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;

// public class ShaqSubsystem extends SubsystemBase {

//     private final SparkMax shooterMotor;
//     /**
//      * This subsytem that controls the roller.
//      */
//     public ShaqSubsystem () {;
//     // Set up the roller motor as a brushed motor
//     shooterMotor = new SparkMax(Constants.SHAQ_ID, MotorType.kBrushless);
//         //THE ID IS 11
//     // Set can timeout. Because this project only sets parameters once on
//     // construction, the timeout can be long without blocking robot operation. Code
//     // which sets or gets parameters during operation may need a shorter timeout.
//     shooterMotor.setCANTimeout(250);

//     // Create and apply configuration for roller motor. Voltage compensation helps
//     // the roller behave the same as the battery
//     // voltage dips. The current limit helps prevent breaker trips or burning out
//     // the motor in the event the roller stalls.
//     SparkMaxConfig shooterConfig = new SparkMaxConfig();
//     shooterConfig.voltageCompensation(Constants.ShooterConstants.SHOOTER_MOTOR_VOLTAGE_COMP);
//     shooterConfig.smartCurrentLimit(Constants.ShooterConstants.SHOOTER_MOTOR_CURRENT_LIMIT);
//     shooterConfig.idleMode(IdleMode.kCoast);
//     shooterMotor.configure(shooterConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
//     }

//     @Override
//     public void periodic() {
//     }
//     /**
//      *  This is a method that makes the roller spin to your desired speed.
//      *  Positive values make it spin forward and negative values spin it in reverse.
//      * 
//      * @param speedmotor speed from -1.0 to 1, with 0 stopping it
//      */
//     public void runRoller(double speed){
//         shooterMotor.set(speed);
//     }

//     public void end(boolean interrupted){
//         shooterMotor.set(0);
//     }

//     public Command shoot(){
//         return startEnd(
//             ()->runRoller(.8),
//             ()->runRoller(0)
//         );
//     }


// }