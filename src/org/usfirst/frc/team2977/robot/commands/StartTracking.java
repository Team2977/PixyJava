package org.usfirst.frc.team2977.robot.commands;

import org.usfirst.frc.team2977.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartTracking extends Command {

    public StartTracking() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.chassis.turnSpeed = .2;
    	Robot.chassis.isDone = false;
    	Robot.chassis.isCentered = false;
    	Robot.vision.CanSeeBlock = false;
    	//Robot.chassis.centeredVision();
    }

    public static boolean isDone;
    public boolean isCentered;
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.StartVision();
    	isDone = Robot.chassis.isDone;
    	isCentered = Robot.chassis.isCentered;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone || isCentered;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.chassis.turnSpeed = 0;
    	//System.out.println("Stopped running StartTracking command");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("StartTracking command interrupted!!!");
    }
}
