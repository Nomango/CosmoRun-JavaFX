package game.pane.option;

import game.baseButton.HexagonButton;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class SoundButton extends HexagonButton{
	private Polygon loudspeaker = new Polygon();
	private Group wave = new Group();
	private Group fork = new Group();
	
	public SoundButton(boolean soundSwitch) {
		initButton();
		if (soundSwitch) {
			this.getChildren().add(wave);
		}
		else 
			this.getChildren().add(fork);
	}
	
	private void initButton() {
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		
		loudspeaker.getPoints().addAll(
				0.0, 20.0, -17.0, 9.0, -25.0, 9.0, -25.0, -9.0, -17.0, -9.0, 0.0, -20.0);
		loudspeaker.setFill(Color.WHITE);
		loudspeaker.setEffect(dropShadow);
		this.getChildren().add(loudspeaker);
		
		Arc wave1 = new Arc(0, 0, 15, 15, -55, 110);
		wave1.setFill(null);
		wave1.setStroke(Color.WHITE);
		wave1.setStrokeWidth(4);
		wave1.setType(ArcType.OPEN);
		wave1.setEffect(dropShadow);
		
		Arc wave2 = new Arc(0, 0, 25, 25, -55, 110);
		wave2.setFill(null);
		wave2.setStroke(Color.WHITE);
		wave2.setStrokeWidth(4);
		wave2.setType(ArcType.OPEN);
		wave2.setEffect(dropShadow);
		
		wave.getChildren().addAll(wave1, wave2);
		
		Rectangle fork1 = new Rectangle(15, -15, 4, 30);
		fork1.setRotate(45);
		fork1.setFill(Color.WHITE);
		fork1.setEffect(dropShadow);
		
		Rectangle fork2 = new Rectangle(15, -15, 4, 30);
		fork2.setRotate(-45);
		fork2.setFill(Color.WHITE);
		fork2.setEffect(dropShadow);
		
		fork.getChildren().addAll(fork1, fork2);
	}
	
	public void set(boolean status) {
		if (status) {
			this.getChildren().removeAll(fork);
			this.getChildren().addAll(wave);
		} else {
			this.getChildren().removeAll(wave);
			this.getChildren().addAll(fork);
		}
	}

}
