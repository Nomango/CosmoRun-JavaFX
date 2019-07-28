package game.baseButton;

import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CloseButton extends Group {
	private Polygon p = new Polygon();
	private Polygon clickedArea = new Polygon();
	private Rectangle r1 = new Rectangle(-3, -24, 6, 48);
	private Rectangle r2 = new Rectangle(-3, -24, 6, 48);

	public CloseButton() {
		initCoordinate();
		initProperty();
		initAnimation();
		this.getChildren().addAll(p, r1, r2, clickedArea);
	}

	private void initCoordinate() {
		// 外层六边形坐标
		for (int i = 0; i < 7; i++) {
			p.getPoints().add(55.0 * Math.cos(Math.toRadians(i * 60)));
			p.getPoints().add(55.0 * Math.sin(Math.toRadians(i * 60)));
			clickedArea.getPoints().add(55.0 * Math.cos(Math.toRadians(i * 60)));
			clickedArea.getPoints().add(55.0 * Math.sin(Math.toRadians(i * 60)));
		}

		// 内层六边形坐标
		for (int i = 0; i < 7; i++) {
			p.getPoints().add(48.0 * Math.cos(Math.toRadians(-i * 60)));
			p.getPoints().add(48.0 * Math.sin(Math.toRadians(-i * 60)));
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
		r1.setFill(Color.WHITE);
		r2.setFill(Color.WHITE);
		r1.setRotate(45);
		r2.setRotate(135);
		r1.setEffect(new BoxBlur(2, 2, 1));
		r2.setEffect(new BoxBlur(2, 2, 1));
	}
	
	private void initAnimation() {
		clickedArea.setOnMouseEntered(e -> {
			p.setFill(Color.gray(0.7));
			r1.setFill(Color.gray(0.8));
			r2.setFill(Color.gray(0.8));
		});
		clickedArea.setOnMouseExited(e -> {
			p.setFill(Color.LIGHTGRAY);
			r1.setFill(Color.WHITE);
			r2.setFill(Color.WHITE);
		});
		clickedArea.setOnMousePressed(e -> {
			p.setFill(Color.gray(0.6));
			r1.setFill(Color.gray(0.7));
			r2.setFill(Color.gray(0.7));
		});
		clickedArea.setOnMouseReleased(e -> {
			p.setFill(Color.LIGHTGRAY);
			r1.setFill(Color.WHITE);
			r2.setFill(Color.WHITE);
		});
	}

}
