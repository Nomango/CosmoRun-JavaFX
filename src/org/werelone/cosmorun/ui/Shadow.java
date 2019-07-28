package org.werelone.cosmorun.ui;

import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

public class Shadow {
	
	public static DropShadow create() {
		DropShadow dropShadow = new DropShadow(5, 4, 4, Color.hsb(0, 0.0, 0.2));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		return dropShadow;
	}
	
	public static DropShadow create(double depth, double shift, Color color) {
		DropShadow dropShadow = new DropShadow(depth, shift, shift, color);
		return dropShadow;
	}
	
	public static DropShadow create(double depth, double shift, Color color, int blurValue) {
		DropShadow dropShadow = new DropShadow(depth, shift, shift, color);
		dropShadow.setInput(new BoxBlur(blurValue, blurValue, blurValue));
		return dropShadow;
	}

}
