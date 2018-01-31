package org.usfirst.frc.team2977.robot.subsystems;

import org.usfirst.frc.team2977.robot.commands.IdleVision;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This stuff is kind of cool?
 */
public class Vision extends Subsystem {

	Port port = Port.kOnboard; //Onboard means its using I2C I believe, I will test this later.
	String print;
	public PixyPacket[] packet1 = new PixyPacket[7]; //not sure why its 7, will test this later as well
	
	public PixyI2C pixyCamera = new PixyI2C("gear", new I2C(port, 0x4), packet1, new PixyException(print), new PixyPacket());

	public int PixyX;
	public int PixyY;
	public int PixyWidth;
	public int PixyHeight;
	public int PixyArea;
	public boolean CanSeeBlock = false;


	public Vision() {
		pixyCamera = new PixyI2C("Pixy", new I2C(port, 0x4), packet1, new PixyException(print), new PixyPacket());
	}
	
	public void testPixy() {
		for (int i = 0; i < packet1.length; i++)
			packet1[i] = null;
		SmartDashboard.putString("Pixy hello", "working");
		for (int i = 1; i < 8; i++) {
			try {
				packet1[i - 1] = pixyCamera.readPacket(i);
			} catch (PixyException e) {
				SmartDashboard.putString("Test Pixy Error: " + i, "exception");
			}
			if (packet1[i - 1] == null) {
				SmartDashboard.putString("Test Pixy Error: " + i, "True");
				CanSeeBlock =  false;
				continue;
			}
			//this is setting the ints in this subsystem
			PixyX = packet1[i - 1].X;
			PixyY = packet1[i - 1].Y;
			PixyWidth = packet1[i - 1].Width;
			PixyHeight = packet1[i - 1].Height;
			CanSeeBlock = true;
			/*
			SmartDashboard.putNumber("Pixy X Value: " + i, packet1[i - 1].X);
			SmartDashboard.putNumber("Pixy Y Value: " + i, packet1[i - 1].Y);
			SmartDashboard.putNumber("Pixy Width Value: " + i, packet1[i - 1].Width);
			SmartDashboard.putNumber("Pixy Height Value: " + i, packet1[i - 1].Height);
			*/
			SmartDashboard.putNumber("Pixy X Value: " + i, PixyX);
			SmartDashboard.putNumber("Pixy Y Value: " + i, PixyY);
			SmartDashboard.putNumber("Pixy Width Value: " + i, PixyWidth);
			SmartDashboard.putNumber("Pixy Height Value: " + i, PixyHeight);
			SmartDashboard.putString("Pixy Error: " + i, "False");
		}
	}

	//This is what the Github I found from another team, Its basically calling all the information the pixy can get.
	public void testGearPixy() {
		for (int i = 0; i < packet1.length; i++)
			packet1[i] = null;
		SmartDashboard.putString("Pixy hello", "working");
		for (int i = 1; i < 8; i++) {
			try {
				packet1[i - 1] = pixyCamera.readPacket(i);
			} catch (PixyException e) {
				SmartDashboard.putString("Pixy Error: " + i, "exception");
			}
			if (packet1[i - 1] == null) {
				SmartDashboard.putString("Pixy Error: " + i, "True");
				continue;
			}
			SmartDashboard.putNumber("Pixy X Value: " + i, packet1[i - 1].X);
			SmartDashboard.putNumber("Pixy Y Value: " + i, packet1[i - 1].Y);
			SmartDashboard.putNumber("Pixy Width Value: " + i, packet1[i - 1].Width);
			SmartDashboard.putNumber("Pixy Height Value: " + i, packet1[i - 1].Height);
			SmartDashboard.putString("Pixy Error: " + i, "False");
		}
	}

	public void initDefaultCommand() {
		setDefaultCommand(new IdleVision());
	}
}