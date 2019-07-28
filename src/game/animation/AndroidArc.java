package game.animation;

import javafx.scene.shape.Arc;

public class AndroidArc extends Arc {
	public boolean isEnlarge = false;
	private int delay = 10;
	
	public AndroidArc(
			double centerX, double centerY, 
			double radiusX, double radiusY, 
			double startAngle, 
			double length ) {
		super(centerX, centerY, radiusX, radiusY, startAngle, length);
	}
	
	public boolean isDelay() {
		return delay > 0;
	}
	
	public void subDelay() {
		delay--;
	}
	
	public void resetDelay() {
		delay = 20;
	}
}
