package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Shadow;
import org.werelone.cosmorun.util.Property;

import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class AboutButton extends BaseButton {
	
	public AboutButton() {
		super.initSmall();
		this.init();
	}
	
	private void init() {
		Circle c = new Circle(Property.get("startmenu-aboutbtn-c-r"));
		c.setFill(Color.WHITE);
		c.setEffect(Shadow.create(5, 3, Color.hsb(0, 0.0, 0.2, 0.3), 1));
		
		Circle c2 = new Circle(Property.get("startmenu-aboutbtn-c2-r"));
		c2.setLayoutY(Property.get("startmenu-aboutbtn-c2-y"));
		c2.setFill(Color.rgb(0, 136, 215));
		c2.setEffect(new BoxBlur(1, 1, 1));
		
		Rectangle r = new Rectangle(Property.get("startmenu-aboutbtn-r-x"), Property.get("startmenu-aboutbtn-r-y"), Property.get("startmenu-aboutbtn-r-w"), Property.get("startmenu-aboutbtn-r-h"));
		r.setFill(Color.rgb(0, 136, 215));
		r.setEffect(new BoxBlur(1, 1, 1));

		this.getChildren().addAll(c, c2, r);
	}

}
