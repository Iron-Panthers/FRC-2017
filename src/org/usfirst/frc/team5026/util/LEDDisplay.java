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
	public void cycle(Color... colors)
	{
		writeRegister(Constants.LED_TIME_DEFAULT, colors);
		cycle(0, colors.length - 1);
	}
	public void cycle(double time, Color...colors)
	{
		writeRegister(time, colors);
		cycle(0, colors.length - 1);
	}
	public void fade(ColorTime... ct)
	{
		writeRegister(ct);
		fade(0, ct.length - 1);
	}
	public void fade(Color...colors)
	{
		writeRegister(Constants.LED_TIME_DEFAULT, colors);
		fade(0, colors.length - 1);
	}
	public void fade(double time, Color...colors)
	{
		writeRegister(time, colors);
		fade(0, colors.length - 1);
	}
	public void flash(ColorTime ct)
	{
		writeRegister(ct);
		flash(0);
	}
	public void flash(Color color) {
		writeRegister(Constants.LED_TIME_DEFAULT, color);
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
	public void writeRegister(double time, Color... colors)
	{
		reset();
		for(int i = 0; i < colors.length; i++)
		{
			writeRegister(i, time, colors[i].red, colors[i].green, colors[i].blue);
		}
	}
}
