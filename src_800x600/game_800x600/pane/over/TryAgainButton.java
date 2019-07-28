package game_800x600.pane.over;

import game_800x600.baseButton.HexagonButton;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TryAgainButton extends HexagonButton{
	private Text text = new Text("TRY AGAIN");
	private Arc arc = new Arc(120 * 0.67, 0, 20 * 0.67, 20 * 0.67, 0, 270);
	private Rectangle arrow1 = new Rectangle(141 * 0.67, -12 * 0.67, 6.5 * 0.67, 20 * 0.67);
	private Rectangle arrow2 = new Rectangle(130 * 0.67, -10 * 0.67, 6.5 * 0.67, 20 * 0.67);
	
	public TryAgainButton() {
		super.setSize(120, 43);
		initProperty();
		
	}

	private void initProperty() {
		text.setFill(Color.WHITE);
		text.setLayoutX(-200 * 0.67 / 2 - 20);
		text.setLayoutY(50 * 0.67 / 2);
		text.setFont(Font.font("Gill Sans MT Condensed", FontWeight.BOLD, 43));
		DropShadow dropShadow = new DropShadow(4, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		text.setEffect(dropShadow);
		this.getChildren().add(text);
		
		arc.setFill(null);
		arc.setStroke(Color.WHITE);
		arc.setStrokeWidth(4);
		arc.setType(ArcType.OPEN);
		arc.setEffect(dropShadow);
		this.getChildren().add(arc);
		
		arrow1.setRotate(30);
		arrow1.setFill(Color.WHITE);
		arrow1.setEffect(dropShadow);
		
		arrow2.setRotate(-60);
		arrow2.setFill(Color.WHITE);
		arrow2.setEffect(dropShadow);
		this.getChildren().addAll(arrow2, arrow1);
	}
}
