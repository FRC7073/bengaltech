// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc7073.Bengalbot.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc7073.Bengalbot.Robot;

/**
 *
 */
public class ArmDrive extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public ArmDrive() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.arm);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double DriveSpeed;      // -1 to +1, reverse to forward
        double Scaler;
        double AxisValue;

        // Read y joystick axis and control arm motor

        Scaler = -Robot.oi.getJoystick2().getThrottle();
        // System.out.println("Scaler: " + Scaler);
        Scaler = 0.4 + ((1.0 + Scaler) / 2.0) * 0.5;     // Gives value from 0 to 0.75
        // System.out.println("Throttle: " + Scaler);

        DriveSpeed = -Robot.oi.getJoystick2().getY() * Scaler;

        Robot.arm.ArmDrive( DriveSpeed);

        //
        // Read hat switch and control wrist motor
        //

        AxisValue = Robot.oi.getJoystick2().getRawAxis(4);
        if (AxisValue < -0.1)
        {
            // Hat switch is pushed up, lower wrist
            DriveSpeed = -Scaler;
            Robot.wrist.WristDrive( DriveSpeed);        
        }
        else if (AxisValue > 0.1)
        {
            // Hat switch is pushed down, raise wrist
            DriveSpeed = Scaler;
            Robot.wrist.WristDrive( DriveSpeed);        
        }
        else
        {
            // Hat switch is in center, do not move wrist
            DriveSpeed = 0;
            Robot.wrist.WristDrive( DriveSpeed);        
        }

        //
        // Read left thumb switches and control elevator motor
        //

        if (Robot.oi.getJoystick2().getRawButton(5) == true)
        {
            // Raise alevator
            DriveSpeed = Scaler;
            Robot.elevator.ElevatorDrive( DriveSpeed);
        }
        else if (Robot.oi.getJoystick2().getRawButton(3) == true)
        {
            // Lower elevator
            DriveSpeed = -Scaler;
            Robot.elevator.ElevatorDrive( DriveSpeed);
        }
        else 
        {
            // Elevator stop, neither button pushed
            DriveSpeed = 0;
            Robot.elevator.ElevatorDrive( DriveSpeed);
        }

        //
        // Read trigger and side switch and control grabber notor
        //

        if (Robot.oi.getJoystick2().getRawButton(1) == true)
        {
            //  Grab ball
            DriveSpeed = Scaler;
            Robot.graber.GrabberDrive( DriveSpeed);
        }
        else if (Robot.oi.getJoystick2().getRawButton(2) == true)
        {
            // Push ball out
            DriveSpeed = -Scaler;
            Robot.graber.GrabberDrive( DriveSpeed);
        }
        else 
        {
            // Grabber stop, neither button pushed
            DriveSpeed = 0;
            Robot.graber.GrabberDrive( DriveSpeed);
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
