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
 * @author ��������
 * @date 2016/6/23
 * @version 1.5
 */

public class Game extends Application{
	public final static double width = 1200;	// ���ڿ��
	public final static double height = 900;	// ���ڸ߶�
	private static int bestScore = 0;			// ��߷�
	public static int score = 0;				// ��ǰ�÷�
	public static int bkMode = 0;				// ������ʽ
	public static boolean soundSwitch = true;	// ��������
	public static boolean firstPlay = true;		// �Ƿ��һ����Ϸ
	public static Pane pane = new Pane();		// ��Ϸ�����
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		// ��ȡ�浵
		LoadGame.readArchive();
		// ��ϷƬͷ����
		pane.getChildren().add(new GameTitle());
		// ������Ϸ
		this.loadGame();

		// ����ͼ��
		primaryStage.getIcons().add(new Image("game/sources/icon.png"));
		// ��������
		primaryStage.setTitle("Cosmo Run");
		// �����ʹ�С
		primaryStage.setScene(new Scene(pane, width, height));
		// �̶����ڴ�С
		primaryStage.setWidth(width);
		primaryStage.setHeight(height);
		primaryStage.setResizable(false);
		// �򿪴���
		primaryStage.show();

		// �رմ���
		primaryStage.setOnCloseRequest(e -> close());
	}
	
	/*
	 * ������Ϸ
	 */
	private void loadGame() {
		// ���ؽ���
		Background.load();
		Music.load();
		Display.load();
		Menu.load();
		GameOver.load();
		UpdateLog.load();
		HowToPlay.load();
		Option.load();
		About.load();
		
		// ����ȫ͸��
		Background.pane.setOpacity(0.0);
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
		Menu.setBestScore(bestScore);
		GameOver.setScore(score, bestScore);
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
		SaveGame.save();
		if (soundSwitch)
			Music.stopBkMusic();
	}

}
