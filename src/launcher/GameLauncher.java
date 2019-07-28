package launcher;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * 
 * @author LittleSShark
 * @version 1.6
 * @date 2016/6/24
 *
 */

public class GameLauncher extends Application{

	public static void main(String[] args) {
		launch(args);

	}
	
	@Override
	public void init() {
		if (!Font.getFontNames().contains("Gill Sans MT Condensed")) {
			Font.loadFont(GameLauncher.class.getResource("/resources/font/Gill-Sans-MT-Condensed.TTF").toExternalForm(), 10.0);
		}
		if (!Font.getFontNames().contains("Microsoft YaHei")) {
			Font.loadFont(GameLauncher.class.getResource("/resources/font/msyh.ttf").toExternalForm(), 10.0);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		pane.setSpacing(15);
		
		ImageView iconView = new ImageView(new Image("resources/icon.png"));
		iconView.setFitWidth(100);
		iconView.setFitHeight(100);
		
		Label label1 = new Label("CosmoRun v1.6");
		label1.setFont(Font.font("Gill Sans MT Condensed", 35));
		
		Label label2 = new Label("请选择游戏分辨率");
		label2.setFont(Font.font("Microsoft YaHei", 18));
		
		Button bt1 = new Button("800x600");
		bt1.setFont(Font.font("Microsoft YaHei", 15));
		bt1.setMinSize(160, 40);
		bt1.setOnAction(e -> {
			this.initStage(primaryStage);
			new game_800x600.Game().start(primaryStage);
		});
		
		Button bt2 = new Button("1200x900");
		bt2.setFont(Font.font("Microsoft YaHei", 15));
		bt2.setMinSize(160, 40);
		bt2.setOnAction(e -> {
			this.initStage(primaryStage);
			new game_1200x900.Game().start(primaryStage);
		});
		
	/*	Text advancedOptions = new Text("高级选项");
		advancedOptions.setFont(Font.font("Microsoft YaHei", 13));
		advancedOptions.setFill(Color.BLUE);
		advancedOptions.setCursor(Cursor.HAND);
		
		Button btReturn = new Button("返回");
		btReturn.setFont(Font.font("Microsoft YaHei", 15));
		btReturn.setMinSize(160, 40);
		
		btReturn.setOnAction(e -> {
			pane.getChildren().clear();
			pane.getChildren().addAll(iconView, label1, label2, bt1, bt2, advancedOptions);
		});
		advancedOptions.setOnMouseClicked(e -> {
			pane.getChildren().clear();
			pane.getChildren().addAll(btReturn);
		});*/
		
		pane.getChildren().addAll(iconView, label1, label2, bt1, bt2);
		
		primaryStage.setTitle("Launcher");
		primaryStage.setScene(new Scene(pane, 300, 400));
		// 固定窗口大小
		primaryStage.setWidth(300);
		primaryStage.setHeight(400);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	private void initStage(Stage primaryStage) {
		// 隐藏窗口
		primaryStage.hide();
		primaryStage.setScene(null);
		// 设置窗口图标
		primaryStage.getIcons().add(new Image("resources/icon.png"));
		// 设置窗口名称
		primaryStage.setTitle("Cosmo Run");
	}

}

/*
 * int response = JOptionPane.showOptionDialog(null, "请选择游戏分辨率：",
 * "CosmoRun Launcher", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE,
 * null, new Object[]{"800x600","1200x900"}, null);
 * 
 * if(response==0) { new game_800x600.Game().start(primaryStage); } else
 * if(response==1) { new game_1200x900.Game().start(primaryStage); } else {
 * Platform.exit(); }
 */