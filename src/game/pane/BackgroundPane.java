package game.pane;

import game.Game;
import game.animation.Fade;
import game.animation.Show;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class BackgroundPane extends Pane{
	public static enum BkColor{blue, green};
	public static BkColor bkColor;
	private Rectangle background = new Rectangle(Game.width / 10, 0, Game.width * 8 / 10, Game.height);
	private Rectangle background1 = new Rectangle(0, 0, Game.width, Game.height);
	private Rectangle background2 = new Rectangle(0, 0, Game.width, Game.height);
	
	public BackgroundPane(int mode) {
		background.setArcWidth(Game.width * 3 / 10);
		background.setArcHeight(Game.height * 3 / 10);
		background.setEffect(new BoxBlur(100, 100, 50));
		background.setFill(Color.hsb(0, 0.0, 0.35, 0.1));
		
		background1.setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(0, Color.rgb(118, 40, 78)),
                new Stop(1, Color.rgb(45, 31, 66))));
		background2.setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(0, Color.rgb(8, 39, 110)),
                new Stop(1, Color.rgb(6, 37, 38))));
		
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
		
		if (mode == 1) {
			bkColor = BkColor.blue;
			this.getChildren().addAll(background2, background1, background);
		}
		else if (mode == 2) {
			bkColor = BkColor.green;
			this.getChildren().addAll(background1, background2, background);
		}
	}
	
	public void setBkColor(BkColor bkColor) {
		BackgroundPane.bkColor = bkColor;
		switch (bkColor) {
		case blue:
			Fade.setNode(background2);
			Show.setNode(background1);
			break;
		case green:
			Fade.setNode(background1);
			Show.setNode(background2);
			break;
		}
	}
	
	public static int getMode() {
		switch (bkColor) {
		case blue:
			return 1;
		case green:
			return 2;
		default:
			return 0;
		
		}
	}
	
}
