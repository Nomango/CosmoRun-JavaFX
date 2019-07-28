package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Shadow;
import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class TryAgainButton extends BaseButton{
	private Text text;
	private Text text_cn;
	private Arc arc;
	private Rectangle arrow1;
	private Rectangle arrow2;
	
	public TryAgainButton() {
		super.initCustom(Property.get("gameover-tryagainbtn-w"), Property.get("gameover-tryagainbtn-h"));
		this.init();
	}
	
	private void init() {
		text = new Text(Language.getText("en-gameover-tryagain"), Property.get("gameover-tryagainbtn-text-x"), Property.get("gameover-tryagainbtn-text-y"), TextFont.GillSans, 65, true);
		text_cn = new Text(Language.getText("cn-gameover-tryagain"), Property.get("gameover-tryagainbtn-text-cn-x"), Property.get("gameover-tryagainbtn-text-cn-y"), TextFont.MsYaHei, 40, false);
		this.getChildren().add(text);
		this.getChildren().add(text_cn);
		
		DropShadow dropShadow = Shadow.create(5, 3, Color.BLACK, 1);
		
		arc = new Arc(Property.get("gameover-tryagainbtn-arc-x"), 0, Property.get("gameover-tryagainbtn-arc-radiusx"), Property.get("gameover-tryagainbtn-arc-radiusy"), 0, Property.get("gameover-tryagainbtn-arc-length"));
		arc.setFill(null);
		arc.setStroke(Color.WHITE);
		arc.setStrokeWidth(Property.get("gameover-tryagainbtn-arc-strokew"));
		arc.setType(ArcType.OPEN);
		arc.setEffect(dropShadow);
		this.getChildren().add(arc);
		
		arrow1 = new Rectangle(Property.get("gameover-tryagainbtn-r1-x"), Property.get("gameover-tryagainbtn-r1-y"), Property.get("gameover-tryagainbtn-r-w"), Property.get("gameover-tryagainbtn-r-h"));
		arrow1.setRotate(30);
		arrow1.setFill(Color.WHITE);
		arrow1.setEffect(dropShadow);
		
		arrow2 = new Rectangle(Property.get("gameover-tryagainbtn-r2-x"), Property.get("gameover-tryagainbtn-r2-y"), Property.get("gameover-tryagainbtn-r-w"), Property.get("gameover-tryagainbtn-r-h"));
		arrow2.setRotate(-60);
		arrow2.setFill(Color.WHITE);
		arrow2.setEffect(dropShadow);
		this.getChildren().addAll(arrow2, arrow1);
		
		changeLang();
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
