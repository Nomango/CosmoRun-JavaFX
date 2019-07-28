package org.werelone.cosmorun;

import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Music;
import org.werelone.cosmorun.util.SaveTool;
import org.werelone.cosmorun.view.RootLayout;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	// 游戏窗口的单例
	private static Stage stage;
	
	public static void main(String args[]) {
		// 初始化游戏数据
		GameData.init();
		// 启动游戏
		Application.launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		// 储存窗口句柄
		Main.stage = stage;
		// 设置场景
		stage.setScene(getScene());
		// 设置窗口大小
		stage.setWidth(GameData.getWidth());
		stage.setHeight(GameData.getHeight());
		// 固定窗口大小
		stage.setResizable(false);
		// 设置窗口图标
		stage.getIcons().add(new Image("resources/icon.png"));
		// 设置窗口名称
		stage.setTitle("宇宙漫步");
		// 关闭窗口时自动储存数据
		stage.setOnCloseRequest(e -> SaveTool.save());
		// 打开窗口
		stage.show();
	}
	
	@Override
	public void init() {
		// 加载字体
		if (!Font.getFontNames().contains("Gill Sans MT Condensed")) {
			Font.loadFont(Main.class.getResource("/resources/font/Gill-Sans-MT-Condensed.TTF").toExternalForm(), 10.0);
		}
		if (!Font.getFontNames().contains("Microsoft YaHei")) {
			Font.loadFont(Main.class.getResource("/resources/font/msyh.ttf").toExternalForm(), 10.0);
		}
	}
	
	private static Scene getScene() {
		// 创建游戏布局
		Pane pane = RootLayout.create();
		// 设置场景
		Scene scene = new Scene(pane, GameData.getWidth(), GameData.getHeight());
		scene.setFill(Color.BLACK);
		return scene;
	}
	
	// 切换游戏分辨率
	public static void changeResolution() {
		// 关闭当前窗口
		stage.hide();
		// 停止背景音乐
		Music.stopBgMusic();
		// 修改游戏分辨率属性
		GameData.changeResolutionData();
		// 设置场景
		stage.setScene(getScene());
		// 设置窗口大小
		stage.setWidth(GameData.getWidth());
		stage.setHeight(GameData.getHeight());
		stage.show();
	}

}
