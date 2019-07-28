package game_800x600;

import game_800x600.pane.Display;
import game_800x600.pane.GameTitle;
import game_800x600.pane.about.About;
import game_800x600.pane.background.Background;
import game_800x600.pane.howtoplay.HowToPlay;
import game_800x600.pane.menu.Menu;
import game_800x600.pane.option.Option;
import game_800x600.pane.over.GameOver;
import game_800x600.pane.updateLog.UpdateLog;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Music;
import util.animation.Show;

public class Game {
	public final static double width = 800;	// 窗口宽度
	public final static double height = 600;	// 窗口高度
	private static int bestScore = 0;			// 最高分
	public static int score = 0;				// 当前得分
	public static int bkMode = 0;				// 背景样式
	public static boolean soundSwitch = true;	// 声音开关
	public static boolean firstPlay = true;		// 是否第一次游戏
	public static Pane pane = new Pane();		// 游戏主面板
	
	public void start(Stage stage) {
		// 读取存档
		LoadGame.readArchive();
		
		// 游戏片头动画
		pane.getChildren().add(new GameTitle());
		
		// 设置场景
		Scene scene = new Scene(pane, Game.width, Game.height);
		scene.setFill(Color.BLACK);
		stage.setScene(scene);
		// 设置窗口大小
		stage.setWidth(width);
		stage.setHeight(height);
		// 打开窗口
		stage.show();
		
		// 加载游戏
		this.loadGame();
		
		// 关闭窗口
		stage.setOnCloseRequest(e -> close());
	}
	
	/*
	 * 加载游戏
	 */
	private void loadGame() {
		// 加载界面
		Background.load();
		util.Music.load();
		Display.load();
		Menu.load();
		GameOver.load();
		UpdateLog.load();
		HowToPlay.load();
		Option.load();
		About.load();
		
		// 设置全透明
		Background.pane.setOpacity(0.0);
		Display.pane.setOpacity(0.0);
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
		SaveGame.save();
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
		Music.stopBkMusic();
		SaveGame.save();
	}

}
