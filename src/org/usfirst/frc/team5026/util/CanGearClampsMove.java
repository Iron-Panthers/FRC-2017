package org.usfirst.frc.team5026.util;

public class CanGearClampsMove {

	public CanGearClampsMove(){
	}
	
	public static boolean checkMovement(GearOpenable groundClamp, GearOpenable upperClamp){
		if(!groundClamp.isOpen && !upperClamp.isOpen){
			return true;
		}
		return false;
	}
	
	public static boolean canOtherMove(GearOpenable otherClamp) {
		return !otherClamp.isOpen;
	}
}
