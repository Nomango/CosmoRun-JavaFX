package game;

import game.animation.Show;
import game.music.Music;
import game.pane.Display;
import game.pane.GameTitle;
import game.pane.about.About;
import game.pane.background.Background;
import game.pane.howtoplay.HowToPlay;
import game.pane.menu.Menu;
import game.pane.option.Option;
import game.pane.over.GameOver;
import game.pane.updateLog.UpdateLog;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author 宫泽先生
 * @date 2016/6/23
 * @version 1.5
 */

public class Game extends Application{
	public final static double width = 1200;	// 窗口宽度
	public final static double height = 900;	// 窗口高度
	private static int bestScore = 0;			// 最高分
	public static int score = 0;				// 当前得分
	public static int bkMode = 0;				// 背景样式
	public static boolean soundSwitch = true;	// 声音开关
	public static boolean firstPlay = true;		// 是否第一次游戏
	public static Pane pane = new Pane();		// 游戏主面板
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		// 读取存档
		LoadGame.readArchive();
		// 游戏片头动画
		pane.getChildren().add(new GameTitle());
		// 加载游戏
		this.loadGame();

		// 窗口图标
		primaryStage.getIcons().add(new Image("game/sources/icon.png"));
		// 窗口名称
		primaryStage.setTitle("Cosmo Run");
		// 场景和大小
		primaryStage.setScene(new Scene(pane, width, height));
		// 固定窗口大小
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);
		// 打开窗口
		primaryStage.show();

		// 关闭窗口
		primaryStage.setOnCloseRequest(e -> close());
	}
	
	/*
	 * 加载游戏
	 */
	private void loadGame() {
		// 加载界面
		Background.load();
		Music.load();
		Display.load();
		Menu.load();
		GameOver.load();
		UpdateLog.load();
		HowToPlay.load();
		Option.load();
		About.load();
		
		// 设置全透明
		Background.pane.setOpacity(0.0);
		Menu.pane.setOpacity(0.0);
		GameOver.pane.setOpacity(0.0);
		UpdateLog.pane.setOpacity(0.0);
		HowToPlay.pane.setOpacity(0.0);
		Option.pane.setOpacity(0.0);
		About.pane.setOpacity(0.0);
		
		// 底层是 Background.pane 和 Display.pane
		// 上层是 Menu.pane, GameOver.pane, Log.pane, HowToPlay.pane, Option.pane, About.pane
		pane.getChildren().addAll(
				Background.pane, Display.pane, 
				Menu.pane, GameOver.pane, UpdateLog.pane, HowToPlay.pane, Option.pane, About.pane);
	}
	
	/*
	 * 设置最高分
	 */
	public static void setBestScore(int score) {
		bestScore = bestScore < score ? score : bestScore;
		Menu.setBestScore(bestScore);
		GameOver.setScore(score, bestScore);
	}
	
	/*
	 * 获取最高分
	 */
	public static int getBestScore() {
		return bestScore;
	}
	
	/*
	 * 将面板置于最前
	 */
	public static void toFront(Node node) {
		Show.setNode(node);
		node.toFront();
	}
	
	/*
	 * 游戏结束时执行的方法
	 */
	private void close() {
		SaveGame.save();
		if (soundSwitch)
			Music.stopBkMusic();
	}

}
