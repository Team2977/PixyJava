package org.usfirst.frc.team2977.robot.subsystems;

//this essentially calls if there is an exception, so if the pixy breaks mid game or whatever.
public class PixyException extends Exception{
	public PixyException(String message){
		super(message);
	}
}

