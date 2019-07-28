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
	public final static double width = 800;	// ���ڿ��
	public final static double height = 600;	// ���ڸ߶�
	private static int bestScore = 0;			// ��߷�
	public static int score = 0;				// ��ǰ�÷�
	public static int bkMode = 0;				// ������ʽ
	public static boolean soundSwitch = true;	// ��������
	public static boolean firstPlay = true;		// �Ƿ��һ����Ϸ
	public static Pane pane = new Pane();		// ��Ϸ�����
	
	public void start(Stage stage) {
		// ��ȡ�浵
		LoadGame.readArchive();
		
		// ��ϷƬͷ����
		pane.getChildren().add(new GameTitle());
		
		// ���ó���
		Scene scene = new Scene(pane, Game.width, Game.height);
		scene.setFill(Color.BLACK);
		stage.setScene(scene);
		// ���ô��ڴ�С
		stage.setWidth(width);
		stage.setHeight(height);
		// �򿪴���
		stage.show();
		
		// ������Ϸ
		this.loadGame();
		
		// �رմ���
		stage.setOnCloseRequest(e -> close());
	}
	
	/*
	 * ������Ϸ
	 */
	private void loadGame() {
		// ���ؽ���
		Background.load();
		util.Music.load();
		Display.load();
		Menu.load();
		GameOver.load();
		UpdateLog.load();
		HowToPlay.load();
		Option.load();
		About.load();
		
		// ����ȫ͸��
		Background.pane.setOpacity(0.0);
		Display.pane.setOpacity(0.0);
		Menu.pane.setOpacity(0.0);
		GameOver.pane.setOpacity(0.0);
		UpdateLog.pane.setOpacity(0.0);
		HowToPlay.pane.setOpacity(0.0);
		Option.pane.setOpacity(0.0);
		About.pane.setOpacity(0.0);
		
		// �ײ��� Background.pane �� Display.pane
		// �ϲ��� Menu.pane, GameOver.pane, Log.pane, HowToPlay.pane, Option.pane, About.pane
		pane.getChildren().addAll(
				Background.pane, Display.pane, 
				Menu.pane, GameOver.pane, UpdateLog.pane, HowToPlay.pane, Option.pane, About.pane);
	}
	
	/*
	 * ������߷�
	 */
	public static void setBestScore(int score) {
		bestScore = bestScore < score ? score : bestScore;
		SaveGame.save();
	}
	
	/*
	 * ��ȡ��߷�
	 */
	public static int getBestScore() {
		return bestScore;
	}
	
	/*
	 * �����������ǰ
	 */
	public static void toFront(Node node) {
		Show.setNode(node);
		node.toFront();
	}
	
	/*
	 * ��Ϸ����ʱִ�еķ���
	 */
	private void close() {
		Music.stopBkMusic();
		SaveGame.save();
	}

}
