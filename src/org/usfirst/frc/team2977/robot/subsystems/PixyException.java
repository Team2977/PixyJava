package org.usfirst.frc.team2977.robot.subsystems;

//this essentially calls if there is an exception, so if the pixy breaks mid game or whatever.
@SuppressWarnings("serial")
public class PixyException extends Exception{
	public PixyException(String message){
		super(message);
	}
}

