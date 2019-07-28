package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.util.Property;

import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class BaseButton extends Group{
	protected Polygon outsidePolygon;
	protected Polygon insidePolygon;
	
	protected void initSmall() {
		this.outsidePolygon = new Polygon(Property.getArray("basebtn-little-outside"));
		this.insidePolygon = new Polygon(Property.getArray("basebtn-little-inside"));
		this.init();
	}
	
	protected void initLarge() {
		this.outsidePolygon = new Polygon(Property.getArray("basebtn-big-outside"));
		this.insidePolygon = new Polygon(Property.getArray("basebtn-big-inside"));
		this.init();
	}
	
	protected void initCustom(double width, double height) {
		double[] outsidePoints = new double[]{-width, -height, width, -height, width + Property.get("basebtn-ex"), 0, width, height, -width, height, -width - Property.get("basebtn-ex"), 0};
		this.outsidePolygon = new Polygon(outsidePoints);
		
		double insideWidth = width - Property.get("basebtn-diff");
		double insideHeight = height - Property.get("basebtn-diff");
		double[] insidePoints = new double[]{-insideWidth, -insideHeight, insideWidth, -insideHeight, insideWidth + Property.get("basebtn-ex"), 0, insideWidth, insideHeight, -insideWidth, insideHeight, -insideWidth - Property.get("basebtn-ex"), 0};
		this.insidePolygon = new Polygon(insidePoints);
		this.init();
	}
	
	private void init() {
		this.initProperty();
		this.initMouseAction();
	}
	
	private void initProperty() {
		outsidePolygon.setFill(Color.rgb(0, 161, 255));
		insidePolygon.setFill(Color.rgb(0, 136, 215));
		
		DropShadow dropShadow = new DropShadow(7, 3, 3, Color.hsb(0, 0.0, 0.2, 0.5));
		dropShadow.setInput(new BoxBlur(2, 2, 2));
		outsidePolygon.setEffect(dropShadow);
		
		insidePolygon.setEffect(new BoxBlur(2, 2, 1));
		
		this.getChildren().addAll(outsidePolygon, insidePolygon);
	}
	
	private void initMouseAction() {
		this.setOnMouseEntered(e -> {
			outsidePolygon.setFill(Color.rgb(0, 181, 255));
			insidePolygon.setFill(Color.rgb(0, 146, 225));
		});
		this.setOnMouseExited(e -> {
			outsidePolygon.setFill(Color.rgb(0, 161, 255));
			insidePolygon.setFill(Color.rgb(0, 136, 215));
		});
		this.setOnMousePressed(e -> {
			outsidePolygon.setFill(Color.rgb(0, 141, 235));
			insidePolygon.setFill(Color.rgb(0, 116, 195));
		});
		this.setOnMouseReleased(e -> {
			outsidePolygon.setFill(Color.rgb(0, 161, 255));
			insidePolygon.setFill(Color.rgb(0, 136, 215));
		});
	}

}
