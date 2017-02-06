package org.usfirst.frc.team5026.util;

public enum Color {
	RED(255, 0, 0),
	GREEN(0, 255, 0),
	BLUE(0, 0, 255);
	
	public int red;
	public int green;
	public int blue;
	
	private Color(int r, int g, int b)
	{
		this.red = r;
		this.green = g;
		this.blue = b;
	}
}
