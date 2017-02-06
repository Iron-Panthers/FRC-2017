package org.usfirst.frc.team5026.util;

import com.mindsensors.CANLight;

public class LEDDisplay extends CANLight {
	
	public LEDDisplay(int port)
	{
		super(port);
	}
	
	public void cycle(ColorTime... ct)
	{
		writeRegister(ct);
		cycle(0, ct.length - 1);
	}
	public void fade(ColorTime... ct)
	{
		writeRegister(ct);
		fade(0, ct.length - 1);
	}
	public void flash(ColorTime ct)
	{
		writeRegister(ct);
		flash(0);
	}
	public void setColor(Color color)
	{
		showRGB(color.red, color.green, color.blue);
	}
	
	public void writeRegister(ColorTime... ct)
	{
		reset();
		for(int i = 0; i < ct.length; i++)
		{
			writeRegister(i, ct[i].time, ct[i].color.red, ct[i].color.green, ct[i].color.blue);
		}	
	}
}
