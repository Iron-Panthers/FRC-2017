package org.usfirst.frc.team5026.util;

public class CanGearClampsMove {

	public CanGearClampsMove(){
	}
	
	public static boolean checkMovement(GearState groundClamp, GearState upperClamp){
		if(!groundClamp.isOpen && !upperClamp.isOpen){
			return true;
		}
		return false;
	}
}
