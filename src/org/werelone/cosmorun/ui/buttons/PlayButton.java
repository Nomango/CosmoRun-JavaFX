package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;

import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class PlayButton extends Group{
	private Polygon shape;
	private Text text;
	private Text text_cn;
	
	public PlayButton() {
		this.initShape();
		this.initMouseAction();
		this.initAnimation();
	}
	
	private void initShape() {
		shape = new Polygon(Property.getArray("startmenu-playbtn-points"));
		shape.setFill(Color.rgb(0, 141, 225, 0.3));
		
		DropShadow dropShadow = new DropShadow(7, 3, 3, Color.hsb(0, 0.0, 0.2, 0.5));
		dropShadow.setInput(new BoxBlur(2, 2, 2));
		shape.setEffect(dropShadow);
		
		text = new Text(Language.getText("en-startmenu-play"), Property.get("startmenu-playbtn-text-x"), Property.get("startmenu-playbtn-text-y"), TextFont.GillSans, 65, true);
		text_cn = new Text(Language.getText("cn-startmenu-play"), Property.get("startmenu-playbtn-text-cn-x"), Property.get("startmenu-playbtn-text-cn-y"), TextFont.MsYaHei, 40, false);
		
		this.getChildren().addAll(shape, text, text_cn);
	}
	
	private void initMouseAction() {
		this.setOnMouseEntered(e -> shape.setFill(Color.rgb(0, 171, 255, 0.3)));
		this.setOnMouseExited(e -> shape.setFill(Color.rgb(0, 141, 225, 0.3)));
		this.setOnMousePressed(e -> shape.setFill(Color.rgb(0, 80, 175, 0.3)));
		this.setOnMouseReleased(e -> shape.setFill(Color.rgb(0, 141, 225, 0.3)));
	}
	
	private void initAnimation() {
		ScaleTransition st = new ScaleTransition(Duration.millis(1000), this);
		st.setByX(0.15f);
		st.setByY(0.15f);
		st.setAutoReverse(true);
		st.setCycleCount(-1);
		st.play();
	}
	
	// «–ªª”Ô—‘
	public void changeLang() {
		if (GameData.language == 0) {
			text.setOpacity(1);
			text_cn.setOpacity(0);
		}
		else {
			text.setOpacity(0);
			text_cn.setOpacity(1);
		}
	}

}
