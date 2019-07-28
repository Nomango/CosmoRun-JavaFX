package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Shadow;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Property;

import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class OptionButton extends BaseButton {
	
	public OptionButton() {
		super.initSmall();
		this.init();
	}
	
	private void init() {
		Circle c = new Circle(Property.get("startmenu-optionbtn-c-r"));
		c.setFill(Color.rgb(0, 136, 215));
		c.setEffect(new BoxBlur(1, 1, 1));
		
		Polygon p = new Polygon();
		p.setFill(Color.WHITE);
		p.setEffect(Shadow.create(5, 3, Color.hsb(0, 0.0, 0.2, 0.3), 1));
		for (int i = 0; i < 8; i++) {
			double cos = Math.cos(Math.toRadians(i * 45));
			double sin = Math.sin(Math.toRadians(i * 45));
			if (GameData.getResolution() == 0) {
				p.getPoints().addAll(
						12 * cos + 3.6 * sin, 12 * -sin + 3.6 * cos,
						16 * cos + 2.6 * sin, 16 * -sin + 2.6 * cos,
						16 * cos - 2.6 * sin, 16 * -sin - 2.6 * cos,
						12 * cos - 3.6 * sin, 12 * -sin - 3.6 * cos
						);
			} else {
				p.getPoints().addAll(
						18 * cos + 5.5 * sin, 18 * -sin + 5.5 * cos,
						25 * cos + 4 * sin, 25 * -sin + 4 * cos,
						25 * cos - 4 * sin, 25 * -sin - 4 * cos,
						18 * cos - 5.5 * sin, 18 * -sin - 5.5 * cos
						);
			}
			
		}
		this.getChildren().addAll(p, c);
	}

}
