package org.usfirst.frc.team2977.robot.subsystems;

import org.usfirst.frc.team2977.robot.Robot;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {

	//Declaring motor controllers
	Talon fR = new Talon(1);
	Talon fL = new Talon(2);
	Talon bR = new Talon(3);
	Talon bL = new Talon(0);
	
	/** ------------------  This is vision tracking stuff  ------------------ **/
	/** It isnt very confusing but this is not needed if we arent using PIXY **/
	//Set the parameters here below. The deadzone is in between the two numbers.
	//If it is between the two numbers then it is done.
	
	public int rightX = 200;
	public int leftX = 100;
	public double turnSpeed = 0.25;
	
	//this is used in the CenteredVision method.
	public boolean isCentered = false;
	public boolean isDone = false;
	
	public void StartVision() {
			if(Robot.vision.PixyX > rightX) {
				turnRight();
			}
			else if (Robot.vision.PixyX < leftX) {
				turnLeft();
			}
			else if (Robot.vision.PixyX < rightX && Robot.vision.PixyX > leftX) {
				centeredVision();
				//StartTracking.isDone = true;
			}
			
			goForward();
		}
	
	public void centeredVision() {
		fL.set(0);
		bL.set(0);
		fR.set(0);
		bR.set(0);
		isDone = true;
	}
	
	public void turnLeft() {
		fL.set(turnSpeed);
		bL.set(turnSpeed);
		fR.set(turnSpeed);
		bR.set(turnSpeed);
		
	}
	
	public void turnRight() {
		fL.set(-turnSpeed);
		bL.set(-turnSpeed);
		fR.set(-turnSpeed);
		bR.set(-turnSpeed);
	}
	
	public int newX;
	
	public void goForward() {
		try {
			newX = Robot.vision.PixyX;
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			SmartDashboard.putString("BROKE", "true");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (newX == Robot.vision.PixyX + 3 || newX == Robot.vision.PixyX - 3) {
		fL.set(turnSpeed);
		bL.set(turnSpeed);
		fR.set(-turnSpeed);
		bR.set(-turnSpeed);
		}
		else {
			fL.set(0);
			bL.set(0);
			fR.set(0);
			bR.set(0);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new DriveWithJoysticks());
    }

}

