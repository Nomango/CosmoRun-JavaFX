package org.werelone.cosmorun.ui;

import org.werelone.cosmorun.util.GameData;

import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Text extends javafx.scene.text.Text{
	public static enum TextFont {GillSans, MsYaHei};
	
	public Text(String text, double x, double y) {
		super(text);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setFill(Color.WHITE);
		this.setEffect(Shadow.create(5, 3, Color.hsb(0, 0.0, 0.1), 1));
	}
	
	public Text(String text, double x, double y, TextFont font, double size, boolean bold) {
		super(text);
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setFill(Color.WHITE);
		this.setEffect(Shadow.create(5, 3, Color.hsb(0, 0.0, 0.1), 1));
		this.setFont(font, size, bold);
	}
	
	public Text(String text, double x, double y, TextFont font, double size, Paint value, boolean bold) {
		this(text, x, y, font, size, bold);
		this.setFill(value);
	}
	
	public Text(String text, double x, double y, TextFont font, double size, Effect effect, boolean bold) {
		this(text, x, y, font, size, bold);
		this.setEffect(effect);
	}
	
	public Text(String text, double x, double y, TextFont font, double size, Paint value, Effect effect, boolean bold) {
		this(text, x, y, font, size, value, bold);
		this.setEffect(effect);
	}
	
	public void setFont(TextFont font, double size, boolean bold) {
		// 是否加粗
		FontWeight fw;
		if (bold) {
			fw = FontWeight.BOLD;
		} else {
			fw = FontWeight.NORMAL;
		}
		// 字号
		if (GameData.getResolution() == 0) {
			size = size * 0.75;
		}
		// 设置字体
		if (font == TextFont.GillSans) {
			this.setFont(Font.font("Gill Sans MT Condensed", fw, size));
		} else if (font == TextFont.MsYaHei) {
			this.setFont(Font.font("Microsoft YaHei", fw, size));
		}
	}

}
