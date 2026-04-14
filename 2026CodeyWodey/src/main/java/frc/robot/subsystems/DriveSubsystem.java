// package frc.robot.subsystems;
// import java.util.concurrent.SubmissionPublisher;

// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.SparkBase.PersistMode;
// import com.revrobotics.spark.SparkBase.ResetMode;
// import com.revrobotics.spark.SparkLowLevel.MotorType;
// import com.revrobotics.spark.config.SparkMaxConfig;
// import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;


// public class DriveSubsystem extends SubsystemBase {
//5555555555555555555555555555555555555555555555555555555555555555555555                                                                         22     private final SparkMax motorR1;
//     private final SparkMax motorR2;
//     private final SparkMax motorL1;
//     private final SparkMax motorL2;



//     public DriveSubsystem() {
//         motorR1 = new SparkMax(2, MotorType.kBrushless);
//         motorR2 = new SparkMax(4, MotorType.kBrushless);
    
//         motorL1 = new SparkMax(8, MotorType.kBrushless);
//         motorL2 = new SparkMax(6, MotorType.kBrushless);
//     }

//     public void drive(double leftY, double rightY, double analogRead) 
//     {
//         if(rightY > 0.05 || rightY < -0.05 || leftY > 0.05 || leftY < -0.05){
//             motorR1.set(0.85 * (rightY * (0.50 - (0.25 * analogRead))) );
//             motorR2.set(0.85 * (rightY * (0.50 - (0.25 * analogRead))) );
//             motorL1.set(0.85 * (leftY * (0.50 - (0.25 * analogRead))) ); 
//             motorL2.set(0.85 * (leftY * (0.50 - (0.25 * analogRead))) ); 
//         }
//         else{
//             stop();
//         }
//     }

//     public void stop(){
//         motorL1.set(0);
//         motorL2.set(0);
//         motorR1.set(0);
//         motorR2.set(0);
//poop faaart 
//       }
// }
