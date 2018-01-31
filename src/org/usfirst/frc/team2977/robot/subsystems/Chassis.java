package org.usfirst.frc.team2977.robot.subsystems;


import org.usfirst.frc.team2977.robot.Robot;
import org.usfirst.frc.team2977.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
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
	
	//Spark left = new Spark(1);
	//Spark right = new Spark(0);
	
	//Delcaring interger for drive mode switch
	public int DriveMode = 1;
	int Speed = 1;
	/** 1 is arcade, 2 is tank. Feel free to switch it above to driver preference. **/
	
	//Declaring sensors and anything else
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();	//This is our gyro. does not require port because it
	double gyroangle = gyro.getAngle();				//is plugged into the SLI on RoboRIO
	Accelerometer accel = new BuiltInAccelerometer();
	DigitalInput green = new DigitalInput(0);
	DigitalInput green2 = new DigitalInput(1);
	
	DigitalOutput outp = new DigitalOutput(2);
	double accelX;
	double accelY;
	double accelZ;
	double adjust;  
	double angle; // not degrees	
	double constant = .25; //motor speed  //was at .25 earlier if it matters
	double factor = .75; 
	
	//--------------------Accelerometer--------------------------------------//
		public double getX() {
			accelX = accel.getX();
			SmartDashboard.putNumber("X", accelX);
			return accelX;
		}
		
		public double getY() {
			accelY = accel.getY();
			SmartDashboard.putNumber("Y", accelY);
			return accelY; 
		}

		public double getZ() {
			accelZ = accel.getZ();
			SmartDashboard.putNumber("Z", accelZ);
			return accelZ;
		}
	
	       	public double GyroAngle() {
	       		SmartDashboard.putNumber("Angle", gyro.getAngle());
	       		return gyro.getAngle();
	       	}
	       	public void Reset() { 
				gyro.reset();
				SmartDashboard.putBoolean("Resetted", true);
	       	}
	       	
	//Robot.m_oi.getLeftY(), Robot.m_oi.getLeftX(), Robot.m_oi.getRightY()
	public void ArcadeDrive(double LeftY, double LeftX, double RightY) {
		getX();
		getY();
		getZ();
		GyroAngle();
		//fL.set((LeftY - LeftX)/Speed);
		//bL.set((LeftY - LeftX)/Speed);
		//fR.set((-LeftY - LeftX)/Speed);
		//bR.set((-LeftY - LeftX)/Speed);
		//test stuff
		SmartDashboard.putBoolean("Sees Green", green.get()); //Port 0
		SmartDashboard.putBoolean("AAAAAAA", green2.get()); //Port 1
		
	}

	public void DriveStraight(double speed) {
		fL.set(-speed);
		bL.set(-speed);
		fR.set(speed);
		bR.set(speed);
	}
	
	public void CaliGyro() {
		gyro.calibrate();
	}
	
	
	
	/** ------------------  This is vision tracking stuff  ------------------ **/
	/** It isnt very confusing but this is not needed if we arent using PIXY **/
	//Set the parameters here below. The deadzone is in between the two numbers.
	//If it is between the two numbers then it is done.
	
	public int rightX;
	public int leftX;
	public double turnSpeed = 0.125;
	
	//this is used in the CenteredVision method.
	public boolean centered = false;
	public boolean isDone = false;
	
	public void StartVision() {
		if(Robot.vision.CanSeeBlock == false) {
			System.out.println("Pixy can't find a block. Cancelling the command.");
			isDone = true;
			turnSpeed = 0;
		}
		else {
			if(Robot.vision.PixyX > rightX) {
				turnRight();
			}
			else if (Robot.vision.PixyX > leftX) {
				turnLeft();
			}
			else if (Robot.vision.PixyX < rightX && Robot.vision.PixyX < leftX) {
				centeredVision();
			}
		}
		
	}
	
	public void centeredVision() {
		fL.set(0);
		bL.set(0);
		fR.set(0);
		bR.set(0);
	}
	
	public void turnRight() {
		fL.set(turnSpeed);
		bL.set(turnSpeed);
		fR.set(turnSpeed);
		bR.set(turnSpeed);
		
	}
	
	public void turnLeft() {
		fL.set(turnSpeed);
		bL.set(turnSpeed);
		fR.set(-turnSpeed);
		bR.set(-turnSpeed);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoysticks());
    }

}

