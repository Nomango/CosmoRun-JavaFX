package game_800x600.pane.background;

import game_800x600.Game;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MenuBackground extends Pane{
	public MenuBackground() {
		Rectangle background1 = new Rectangle(0, 0, Game.width, Game.height * 0.25);
		Rectangle background2 = new Rectangle(0, Game.height * 0.25, Game.width, Game.height);
		background1.setFill(Color.rgb(60, 55, 87, 0.6));
		background2.setFill(Color.rgb(63, 38, 75, 0.7));
		this.getChildren().addAll(background1, background2);
	}
	
	public MenuBackground(Paint value) {
		Rectangle background = new Rectangle(0, 0, Game.width, Game.height);
		background.setFill(value);
		this.getChildren().add(background);
	}
	
	public MenuBackground(Paint value1, Paint value2) {
		Rectangle background1 = new Rectangle(0, 0, Game.width, Game.height * 0.25);
		Rectangle background2 = new Rectangle(0, Game.height * 0.25, Game.width, Game.height);
		background1.setFill(value1);
		background2.setFill(value2);
		this.getChildren().addAll(background1, background2);
	}

}
