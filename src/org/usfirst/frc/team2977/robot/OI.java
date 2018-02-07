package org.usfirst.frc.team2977.robot;

import org.usfirst.frc.team2977.robot.commands.StartTracking;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick stick = new Joystick(1);
	Button abutton = new JoystickButton(stick, 1);
	
	public OI() {
		abutton.whenPressed(new StartTracking());
	}
	
	public double getLeftY() {
		return stick.getRawAxis(1);
	}
	
	public double getLeftX()
	{
		return stick.getRawAxis(0);
	}
	public double getRightY() {
		return stick.getRawAxis(5);
	}
}
