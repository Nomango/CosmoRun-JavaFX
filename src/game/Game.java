package game;

/**
 * @author 宫泽先生
 * @date 2016/6/11
 * @version 1.3
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Game extends Application{
	public final static double width = 1200;
	public final static double height = 900;
	
	@Override
	public void start(Stage primaryStage) {
		MainPane pane = new MainPane();
		Scene scene = new Scene(pane, width, height);

		primaryStage.getIcons().add(new Image("game/sources/icon.png"));
		primaryStage.setTitle("Cosmo Run");
		primaryStage.setScene(scene);
		primaryStage.show();

		// 固定窗口大小
		primaryStage.setMaxHeight(primaryStage.getHeight());
		primaryStage.setMaxWidth(primaryStage.getWidth());
		primaryStage.setMinHeight(primaryStage.getHeight());
		primaryStage.setMinWidth(primaryStage.getWidth());
		
		primaryStage.setOnCloseRequest(e -> pane.close());
		
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
