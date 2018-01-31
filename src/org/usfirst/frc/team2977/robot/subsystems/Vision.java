package org.usfirst.frc.team2977.robot.subsystems;

import org.usfirst.frc.team2977.robot.commands.ExampleCommand;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Vision extends Subsystem {

	Port port = Port.kOnboard;
	String print;
	public PixyPacket[] packet1 = new PixyPacket[7];
	public PixyPacket[] packet2 = new PixyPacket[7];
	
	public PixyI2C gearPixy = new PixyI2C("gear", new I2C(port, 0x4), packet1, new PixyException(print), new PixyPacket());
	public PixyI2C boilerPixy;
	


	public Vision() {
		gearPixy = new PixyI2C("gear", new I2C(port, 0x4), packet1, new PixyException(print), new PixyPacket());
		boilerPixy = new PixyI2C("boiler", new I2C(port, 0x55), packet2, new PixyException(print), new PixyPacket());
	}

	public void initDefaultCommand() {
		// TODO: Should we add a PixyIdle command? What should it do? Nothing?
		// Might be useful to have it call some function to get target and print
		// to dashboard? Eh.. if robot isn't aimed at the target we get nothing.
		setDefaultCommand(new ExampleCommand());
	}

	public void testGearPixy() {
		for (int i = 0; i < packet1.length; i++)
			packet1[i] = null;
		SmartDashboard.putString("gearPixy hello", "working");
		for (int i = 1; i < 8; i++) {
			try {
				packet1[i - 1] = gearPixy.readPacket(i);
			} catch (PixyException e) {
				SmartDashboard.putString("gearPixy Error: " + i, "exception");
			}
			if (packet1[i - 1] == null) {
				SmartDashboard.putString("gearPixy Error: " + i, "True");
				continue;
			}
			SmartDashboard.putNumber("gearPixy X Value: " + i, packet1[i - 1].X);
			SmartDashboard.putNumber("gearPixy Y Value: " + i, packet1[i - 1].Y);
			SmartDashboard.putNumber("gearPixy Width Value: " + i, packet1[i - 1].Width);
			SmartDashboard.putNumber("gearPixy Height Value: " + i, packet1[i - 1].Height);
			SmartDashboard.putString("gearPixy Error: " + i, "False");
		}
	}

	public void testBoilerPixy() {
		for (int i = 0; i < packet2.length; i++)
			packet2[i] = null;
		SmartDashboard.putString("boilerPixy hello", "working");
		for (int i = 1; i < 8; i++) {
			try {
				packet2[i - 1] = boilerPixy.readPacket(i);
			} catch (PixyException e) {
				SmartDashboard.putString("boilerPixy Error: " + i, "exception");
			}
			if (packet2[i - 1] == null) {
				SmartDashboard.putString("boilerPixy Error: " + i, "True");
				continue;
			}
			SmartDashboard.putNumber("boilerPixy X Value: " + i, packet2[i - 1].X);
			SmartDashboard.putNumber("boilerPixy Y Value: " + i, packet2[i - 1].Y);
			SmartDashboard.putNumber("boilerPixy Width Value: " + i, packet2[i - 1].Width);
			SmartDashboard.putNumber("boilerPixy Height Value: " + i, packet2[i - 1].Height);
			SmartDashboard.putString("boilerPixy Error: " + i, "False");
		}

	}

	// Get blocks that represent the vision tape on either side of the peg. This
	// can return 0,1, or 2 blocks depending what is found in a frame.
	public PixyPacket[] getPegPosition() {
		PixyPacket[] blocks = gearPixy.readBlocks();
		SmartDashboard.putBoolean("Peg Blocks Array is null", blocks == null);
		if (blocks == null)
			return null;
		SmartDashboard.putString("Peg Block 0", (blocks[0] == null) ? "null" : blocks[0].toString());
		SmartDashboard.putString("Peg Block 1", (blocks[1] == null) ? "null" : blocks[1].toString());
		return blocks;
	}
}