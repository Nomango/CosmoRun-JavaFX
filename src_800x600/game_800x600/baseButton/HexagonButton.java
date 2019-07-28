package game_800x600.baseButton;

import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;

public abstract class HexagonButton extends Group{
	protected Polygon outsidePolygon;
	protected Polygon insidePolygon;
	protected Group bottom = new Group();
	protected double width = 31.5;
	protected double height = 38.2;
	protected double insideWidth = width - 5.4;
	protected double insideHeight = height- 5.4;
	
	public HexagonButton() {
		this.getChildren().add(bottom);
		initProperty();
		initAnimation();
	}

	private void initProperty() {
		outsidePolygon = new Polygon(-width, -height, width, -height, width + 30.2, 0.0, width, height, -width, height, -width - 30.2, 0.0);
		insidePolygon = new Polygon(-insideWidth, -insideHeight, insideWidth, -insideHeight, insideWidth + 26.8, 0.0, insideWidth, insideHeight, -insideWidth, insideHeight, -insideWidth - 26.8, 0.0);
		outsidePolygon.setFill(Color.rgb(0, 161, 255));
		insidePolygon.setFill(Color.rgb(0, 136, 215));
		
		DropShadow dropShadow = new DropShadow(7, 3, 3, Color.hsb(0, 0.0, 0.2, 0.5));
		dropShadow.setInput(new BoxBlur(2, 2, 2));
		outsidePolygon.setEffect(dropShadow);
		
		insidePolygon.setEffect(new BoxBlur(2, 2, 1));
		
		bottom.getChildren().addAll(outsidePolygon, insidePolygon);
	}
	
	private void initAnimation() {
		this.setOnMouseEntered(e -> {
			outsidePolygon.setFill(Color.rgb(0, 140, 230));
			insidePolygon.setFill(Color.rgb(0, 110, 200));
		});
		this.setOnMouseExited(e -> {
			outsidePolygon.setFill(Color.rgb(0, 161, 255));
			insidePolygon.setFill(Color.rgb(0, 136, 215));
		});
		this.setOnMousePressed(e -> {
			outsidePolygon.setFill(Color.rgb(0, 100, 190));
			insidePolygon.setFill(Color.rgb(0, 70, 160));
		});
		this.setOnMouseReleased(e -> {
			outsidePolygon.setFill(Color.rgb(0, 140, 230));
			insidePolygon.setFill(Color.rgb(0, 110, 200));
		});
	}
	
	public void setSize(double width, double height) {
		if (width != 0) {
			this.width = width;
			this.insideWidth = this.width - 5.4;
		}
		if (height != 0) {
			this.height = height;
			this.insideHeight = this.height - 5.4;
		}
		bottom.getChildren().clear();
		initProperty();
	}
	
	public void setOutsideColor(Paint value) {
		outsidePolygon.setFill(value);
	}
	
	public void setInsideColor(Paint value) {
		insidePolygon.setFill(value);
	}
	
	public double getWidth() {
		return (width + 30.2) * 2;
	}
	
	public double getHeight() {
		return height * 2;
	}

}
