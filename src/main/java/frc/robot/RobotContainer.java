// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.SwerveSubsystem;
import yams.mechanisms.swerve.utility.SwerveInputStream;

public class RobotContainer
{

  final CommandXboxController driverXbox = new CommandXboxController(0);

  private final SwerveSubsystem swerve = new SwerveSubsystem();

  private final SwerveInputStream driveAngularVelocity =
      swerve.getAngularVelocityStream(
                () -> driverXbox.getLeftY() * 1.0 ,
                () -> driverXbox.getLeftX() * 1.0 ,
                () -> driverXbox.getRightX() * -1.0)
            .withAllianceRelativeControl();

  public RobotContainer()
  {
    configureBindings();
  }

  private void configureBindings()
  {
    // Default drive command
    swerve.setDefaultCommand(swerve.drive(driveAngularVelocity));
    

    // Zero the gyro with Start + Back — use this if the field-relative heading drifts
    driverXbox.start().and(driverXbox.back()).onTrue(swerve.zeroGyro());
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void periodic()
  {
  }

}
