package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Meter;

// import com.pathplanner.lib.auto.AutoBuilder;
// import com.pathplanner.lib.commands.PathPlannerAuto;
// import com.pathplanner.lib.commands.PathfindingCommand;
// import com.pathplanner.lib.config.PIDConstants;
// import com.pathplanner.lib.config.RobotConfig;
// import com.pathplanner.lib.controllers.PPHolonomicDriveController;
// import com.pathplanner.lib.path.PathConstraints;
// import com.pathplanner.lib.path.PathPlannerPath;
// import com.pathplanner.lib.util.DriveFeedforwards;
// import com.pathplanner.lib.util.swerve.SwerveSetpoint;
// import com.pathplanner.lib.util.swerve.SwerveSetpointGenerator;
// import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
// import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
// import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.util.Units;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj2.command.button.RobotModeTriggers;
// import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine.Config;
import frc.robot.Constants;
// import frc.robot.subsystems.swervedrive.Vision.Cameras;
import java.io.File;
// import java.io.IOException;
// import java.util.Arrays;
// import java.util.Optional;
// import java.util.concurrent.atomic.AtomicReference;
// import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
// import org.json.simple.parser.ParseException;
// import org.photonvision.targeting.PhotonPipelineResult;
// import swervelib.SwerveController;
import swervelib.SwerveDrive;
// import swervelib.SwerveDriveTest;
// import swervelib.math.SwerveMath;
// import swervelib.parser.SwerveControllerConfiguration;
// import swervelib.parser.SwerveDriveConfiguration;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;

// import java.io.File;
import edu.wpi.first.wpilibj.Filesystem;
// import swervelib.parser.SwerveParser;
// import swervelib.SwerveDrive;
// import edu.wpi.first.math.util.Units;

public class SwerveSubsystem extends SubsystemBase {
    double maximumSpeed = Units.feetToMeters(4.5);
    File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(),"swerve");
    SwerveDrive swerveDrive;

    public SwerveSubsystem(){
      SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        try
        {
        swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(Constants.MAX_SPEED, new Pose2d(new Translation2d(Meter.of(0),Meter.of(0)),Rotation2d.fromDegrees(0)));
      // Alternative method if you don't want to supply the conversion factor via JSON files.
      // swerveDrive = new SwerveParser(directory).createSwerveDrive(maximumSpeed, angleConversionFactor, driveConversionFactor);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        // swerveDrive.setHeadingCorrection(false); // Heading correction should only be used while controlling the robot via
        // angle.
        swerveDrive.setCosineCompensator(false);// !SwerveDriveTelemetry.isSimulation); // Disables cosine compensation for
            // simulations since it causes discrepancies not seen in real life.
        swerveDrive.setAngularVelocityCompensation(true,
        true,
        0.1); // Correct for skew that gets worse as angular velocity increases. Start with a
        // coefficient of 0.1.
        swerveDrive.setModuleEncoderAutoSynchronize(false,
        1);
    }
    @Override
    public void periodic() {
    }
    @Override
    public void simulationPeriodic() {
    }
    
    public SwerveDrive getSwerveDrive(){
        return swerveDrive;
    }

    public ChassisSpeeds driveSpeed(){
        ChassisSpeeds velocity = new ChassisSpeeds(-1,0,0);
        return velocity;
    }
    public Command driveForward (){
        return run(() -> {
            swerveDrive.driveFieldOriented(driveSpeed());
            });
    }
    public void driveFieldOriented(ChassisSpeeds velocity)
    {
        swerveDrive.driveFieldOriented(velocity);
    }

    public Command driveFieldOriented(Supplier<ChassisSpeeds> velocity)
    {
        return run(() -> {
        swerveDrive.driveFieldOriented(velocity.get());
        });
    }

    public void end(boolean interrupted)
    {
        swerveDrive.driveFieldOriented(new ChassisSpeeds(0,0,0));
    }

    public Command stop()
    {
        return run(() -> {
            swerveDrive.driveFieldOriented(new ChassisSpeeds(0,0,0));
            });
    }
}

