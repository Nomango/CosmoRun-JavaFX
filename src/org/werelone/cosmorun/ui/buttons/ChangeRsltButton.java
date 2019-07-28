package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Shadow;
import org.werelone.cosmorun.util.Property;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChangeRsltButton extends BaseButton{
	private Rectangle r1;
	private Rectangle r2;
	
	public ChangeRsltButton() {
		super.initSmall();
		this.init();
	}
	
	private void init() {
		this.initShape();
	}
	
	private void initShape() {
		r1 = new Rectangle(Property.get("option-changersltbtn-r1-x"), Property.get("option-changersltbtn-r1-y"), Property.get("option-changersltbtn-r1-w"), Property.get("option-changersltbtn-r1-h"));
		r1.setFill(null);
		r1.setStroke(Color.WHITE);
		r1.setEffect(Shadow.create());
		r1.setStrokeWidth(Property.get("option-changersltbtn-r1-strokew"));
		this.getChildren().add(r1);
		
		r2 = new Rectangle(Property.get("option-changersltbtn-r2-x"), Property.get("option-changersltbtn-r2-y"), Property.get("option-changersltbtn-r2-w"), Property.get("option-changersltbtn-r2-h"));
		r2.setFill(null);
		r2.setStroke(Color.WHITE);
		r2.setEffect(Shadow.create());
		r2.setStrokeWidth(Property.get("option-changersltbtn-r2-strokew"));
		this.getChildren().add(r2);
	}

}
