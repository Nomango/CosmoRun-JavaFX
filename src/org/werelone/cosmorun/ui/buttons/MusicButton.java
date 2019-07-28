package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Shadow;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Property;

import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MusicButton extends BaseButton{
	private Group shape;
	private Group wave;
	private Group fork;
	
	public MusicButton() {
		super.initSmall();
		this.init();
	}
	
	public void setOn(boolean musicOn) {
		shape.getChildren().clear();
		if (musicOn) {
			shape.getChildren().add(wave);
		} else {
			shape.getChildren().add(fork);
		}
	}
	
	private void init() {
		this.initShape();
		this.setOn(GameData.musicOn);
	}
	
	private void initShape() {
		DropShadow dropShadow = Shadow.create();
		
		Polygon audioShape = new Polygon(Property.getArray("option-musicbtn-audioshape"));
		audioShape.setFill(Color.WHITE);
		audioShape.setEffect(dropShadow);
		this.getChildren().add(audioShape);
		
		Arc wave1 = new Arc(0, 0, Property.get("option-musicbtn-wave1-radiusx"), Property.get("option-musicbtn-wave1-radiusy"), Property.get("option-musicbtn-wave-startangle"), Property.get("option-musicbtn-wave-length"));
		wave1.setFill(null);
		wave1.setStroke(Color.WHITE);
		wave1.setStrokeWidth(Property.get("option-musicbtn-wave-strokew"));
		wave1.setType(ArcType.OPEN);
		wave1.setEffect(dropShadow);
		
		Arc wave2 = new Arc(0, 0, Property.get("option-musicbtn-wave2-radiusx"), Property.get("option-musicbtn-wave2-radiusy"), Property.get("option-musicbtn-wave-startangle"), Property.get("option-musicbtn-wave-length"));
		wave2.setFill(null);
		wave2.setStroke(Color.WHITE);
		wave2.setStrokeWidth(Property.get("option-musicbtn-wave-strokew"));
		wave2.setType(ArcType.OPEN);
		wave2.setEffect(dropShadow);
		
		wave = new Group();
		wave.getChildren().addAll(wave1, wave2);
		
		Rectangle fork1 = new Rectangle(Property.get("option-musicbtn-fork-x"), Property.get("option-musicbtn-fork-y"), Property.get("option-musicbtn-fork-w"), Property.get("option-musicbtn-fork-h"));
		fork1.setRotate(45);
		fork1.setFill(Color.WHITE);
		fork1.setEffect(dropShadow);
		
		Rectangle fork2 = new Rectangle(Property.get("option-musicbtn-fork-x"), Property.get("option-musicbtn-fork-y"), Property.get("option-musicbtn-fork-w"), Property.get("option-musicbtn-fork-h"));
		fork2.setRotate(-45);
		fork2.setFill(Color.WHITE);
		fork2.setEffect(new BoxBlur(2, 2, 1));
		
		fork = new Group();
		fork.getChildren().addAll(fork1, fork2);
		
		shape = new Group();
		this.getChildren().add(shape);
	}

}
