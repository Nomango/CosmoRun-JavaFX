package game.pane.background;

import game.Game;
import game.animation.Fade;
import game.animation.Show;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class Background {
	public static Pane pane = new Pane();
	public static final int MAX_MODE = 3;
	public static String[] bkModeString = new String[MAX_MODE];
	private static Rectangle[] background = new Rectangle[MAX_MODE];
//	private static Rectangle effect;
	
	public static void load() {
	//	effect = new Rectangle(Game.width / 10, Game.height / 10, Game.width * 8 / 10, Game.height * 9 / 10);
	//	effect.setArcWidth(Game.width * 3 / 10);
	//	effect.setArcHeight(Game.height * 3 / 10);
	//	effect.setEffect(new BoxBlur(100, 100, 200));
	//	effect.setFill(Color.hsb(0, 0.0, 0.35, 0.03));
		
		for (int i = 0; i < MAX_MODE; i++) {
			background[i] = new Rectangle(0, 0, Game.width, Game.height);
			background[i].setOpacity(0.0);
			pane.getChildren().add(background[i]);
		}
		
		bkModeString[0] = "BLU";
		background[0].setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(0, Color.rgb(8, 39, 110)),
                new Stop(1, Color.rgb(6, 37, 38))));
		
		bkModeString[1] = "VLT";
		background[1].setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(0, Color.rgb(118, 40, 78)),
                new Stop(1, Color.rgb(45, 31, 66))));
		
		bkModeString[2] = "GLD";
		background[2].setFill(new LinearGradient(0, 0, 0, 1, true,
                CycleMethod.REFLECT,
                new Stop(0, Color.rgb(7, 35, 82)),
                new Stop(1, Color.rgb(55, 26, 19))));
		
		background[Game.bkMode].setOpacity(1.0);
	//	pane.getChildren().add(effect);
	}
	
	public static void change() {
		// Òþ²Øµ±Ç°±³¾°
		Fade.setNode(background[Game.bkMode]);
		// ÇÐ»»±³¾°
		Game.bkMode = Game.bkMode + 1 == MAX_MODE ? 0 : Game.bkMode + 1;
		Show.setNode(background[Game.bkMode]);
	}
	
}
