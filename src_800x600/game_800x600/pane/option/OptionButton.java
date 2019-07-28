package game_800x600.pane.option;

import game_800x600.baseButton.HexagonButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class OptionButton extends HexagonButton{
	private Circle c = new Circle(6.6);
	private Polygon p = new Polygon();
	
	public OptionButton() {
		p.setFill(Color.WHITE);
		c.setFill(Color.rgb(0, 136, 215));
		
		for (int i = 0; i < 8; i++) {
			double cos = Math.cos(Math.toRadians(i * 45));
			double sin = Math.sin(Math.toRadians(i * 45));
			p.getPoints().addAll(
					12 * cos + 3.6 * sin, 12 * -sin + 3.6 * cos,
					16 * cos + 2.6 * sin, 16 * -sin + 2.6 * cos,
					16 * cos - 2.6 * sin, 16 * -sin - 2.6 * cos,
					12 * cos - 3.6 * sin, 12 * -sin - 3.6 * cos
					);
		}
		
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		p.setEffect(dropShadow);
		c.setEffect(new BoxBlur(2, 2, 1));
		
		this.getChildren().addAll(p, c);
	}

}
