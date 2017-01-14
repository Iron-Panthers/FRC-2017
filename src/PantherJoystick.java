import org.usfirst.frc.team5026.robot.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class PantherJoystick extends Joystick {
	
	
	public PantherJoystick(int port) {
		super(port);
	}
	public double getScaledMagnitude(){
		double x = this.getX();
		double y = this.getY();
		double magnitude = Math.sqrt((x * x) + (y * y));
		if(magnitude < Constants.DEADZONE_RADIUS){
			x = 0;
			y = 0;
		}	
		else{
			magnitude = (magnitude - Constants.DEADZONE_RADIUS)/(1 - magnitude);
		}
		return magnitude;
	}
	
	public double getScaledX(){
		double newX = this.getScaledMagnitude() * Math.cos(this.getDirectionDegrees()) * Constants.SCALING;
		return newX;
		
	}
	public double getScaledY(){
		double newY = this.getScaledMagnitude() * Math.sin(this.getDirectionDegrees()) * Constants.SCALING;
		return newY;
	}
}
