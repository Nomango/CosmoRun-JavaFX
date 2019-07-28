package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.util.GameData;

import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CloseButton extends Group {
	private Polygon p = new Polygon();
	private Polygon clickedArea = new Polygon();
	private Rectangle r1;
	private Rectangle r2;
	
	private double[][] properties = new double[][]{
		{36.85, 32.16, -2, -16, 4, 32}, // 800x600分辨率
		{55.0, 48.0, -3, -24, 6, 48}}; // 1200x900分辨率

	public CloseButton() {
		initCoordinate();
		initProperty();
		initAnimation();
		this.getChildren().addAll(p, r1, r2, clickedArea);
	}

	private void initCoordinate() {
		// 外层六边形坐标
		for (int i = 0; i < 7; i++) {
			p.getPoints().add(properties[GameData.getResolution()][0] * Math.cos(Math.toRadians(i * 60)));
			p.getPoints().add(properties[GameData.getResolution()][0] * Math.sin(Math.toRadians(i * 60)));
			clickedArea.getPoints().add(properties[GameData.getResolution()][0] * Math.cos(Math.toRadians(i * 60)));
			clickedArea.getPoints().add(properties[GameData.getResolution()][0] * Math.sin(Math.toRadians(i * 60)));
		}

		// 内层六边形坐标
		for (int i = 0; i < 7; i++) {
			p.getPoints().add(properties[GameData.getResolution()][1] * Math.cos(Math.toRadians(-i * 60)));
			p.getPoints().add(properties[GameData.getResolution()][1] * Math.sin(Math.toRadians(-i * 60)));
		}
	}

	private void initProperty() {
		// 外框属性
		p.setFill(Color.LIGHTGRAY);
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		p.setEffect(dropShadow);

		// 点击区域属性
		clickedArea.setFill(Color.hsb(0, 0.0, 0.9, 0.1));

		// 叉号属性
		r1 = new Rectangle(properties[GameData.getResolution()][2], properties[GameData.getResolution()][3], properties[GameData.getResolution()][4], properties[GameData.getResolution()][5]);
		r1.setFill(Color.WHITE);
		r1.setEffect(new BoxBlur(2, 2, 1));
		r1.setRotate(45);
		
		r2 = new Rectangle(properties[GameData.getResolution()][2], properties[GameData.getResolution()][3], properties[GameData.getResolution()][4], properties[GameData.getResolution()][5]);
		r2.setFill(Color.WHITE);
		r2.setEffect(new BoxBlur(2, 2, 1));
		r2.setRotate(135);
	}
	
	private void initAnimation() {
		clickedArea.setOnMouseEntered(e -> {
			p.setFill(Color.WHITE);
		});
		clickedArea.setOnMouseExited(e -> {
			p.setFill(Color.LIGHTGRAY);
			r1.setFill(Color.WHITE);
			r2.setFill(Color.WHITE);
		});
		clickedArea.setOnMousePressed(e -> {
			r1.setFill(Color.gray(0.8));
			r2.setFill(Color.gray(0.8));
		});
		clickedArea.setOnMouseReleased(e -> {
			p.setFill(Color.LIGHTGRAY);
			r1.setFill(Color.WHITE);
			r2.setFill(Color.WHITE);
		});
	}
}
