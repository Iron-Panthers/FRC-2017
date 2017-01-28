package org.usfirst.frc.team5026.robot;
public enum JoystickType {
	RED(Constants.DEADZONE_X, Constants.DEADZONE_Y),
	BLUE(Constants.DEADZONE_X, Constants.DEADZONE_Y),
	SPINNY(Constants.DEADZONE_X, Constants.DEADZONE_Y);
	
	public float deadzoneX;
	public float deadzoneY;
	JoystickType(float deadzoneX, float deadzoneY) {
		this.deadzoneX = deadzoneX;
		this.deadzoneY = deadzoneY;
	}
}

//JoystickType.RED;