package game;

/**
 * @author ��������
 * @date 2016/6/11
 * @version 1.3
 */

import game.animation.AndroidLoadingPane;
import game.animation.Fade;
import game.animation.Show;
import game.music.PlayMusic;
import game.pane.AboutPane;
import game.pane.BackgroundPane;
import game.pane.EndPane;
import game.pane.GamePane;
import game.pane.HowToPlayPane;
import game.pane.LogPane;
import game.pane.MenuPane;
import game.pane.OptionPane;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MainPane extends Pane {
	private AndroidLoadingPane loadingPane = new AndroidLoadingPane(Game.width / 2, Game.height / 2, 200);
	private Text loadingText = new Text("������...");
	private BackgroundPane background; // ������
	private GamePane gamePane;			// ��Ϸ��
	private MenuPane menuPane;			// ���˵�����
	private OptionPane optionPane;		// ���ý���
	private AboutPane aboutPane;		// ���ڽ���
	private LogPane logPane;			// ������־
	private HowToPlayPane howToPlayPane;// ��Ϸ˵��
	private EndPane endPane;			// ��Ϸ��������
	private PlayMusic playMusic = new PlayMusic();
	private int bestScore;
	private int bkColor;
	private boolean soundSwitch;
	private boolean firstPlay = true;

	public MainPane() {
		// ���ض���
		this.loadScene();
		// ��ȡ�浵
		loadArchive();
		// ������Ϸ
		this.load();
	}
	
	private void loadArchive() {
		String archive = "         011";
		if (LoadGame.canLoad()) {
			archive = LoadGame.load();
			firstPlay = false;
		}
		int index = 0;
		while(true) {
			if (Character.isDigit(archive.charAt(index)))
				break;
			index++;
		}
		bestScore = Integer.parseInt(archive.substring(index, 10));
		bkColor = Integer.parseInt(archive.substring(10, 11));
		if (Integer.parseInt(archive.substring(11)) == 1) {
			this.soundSwitch = true;
		} else {
			this.soundSwitch = false;
		}
	}

	private void loadScene() {
		// ���ü��ؽ�����������
		loadingText.setLayoutX(Game.width / 2 - 70);
		loadingText.setLayoutY(Game.height / 1.5);
		loadingText.setFill(Color.BLACK);
		loadingText.setFont(Font.font("΢���ź�", FontWeight.BOLD, 40));
		DropShadow dropShadow = new DropShadow(3, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		loadingText.setEffect(dropShadow);
		this.getChildren().add(loadingText);

		// ���ü��ض�������
		loadingPane.setBackground(null);
		loadingPane.setTime(Math.random() + 1.0);
		loadingPane.setOnFinished(e -> show());
		loadingPane.play();
		this.getChildren().add(loadingPane);
	}

	private void load() {
		// ������Ϸ����
		background = new BackgroundPane(bkColor);
		gamePane = new GamePane();
		menuPane = new MenuPane(bestScore);
		optionPane = new OptionPane(bkColor, soundSwitch);
		aboutPane = new AboutPane();
		logPane = new LogPane();
		howToPlayPane = new HowToPlayPane();
		endPane = new EndPane();

		menuPane.setBtOptionOnMouseClicked(e -> menuPaneOption());		// ���ѡ�ť
		menuPane.setBtPlayOnMouseClicked(e -> menuPanePlay());			// �����ʼ��ť
		menuPane.setOnKeyPressed(e -> {									// ���¿ո��
			if (e.getCode() == KeyCode.SPACE)
				menuPanePlay();
		});

		optionPane.setBtCloseOnMouseClicked(e -> optionPaneClose());	// �ر�Option
		optionPane.setBtSoundOnMouseClicked(e -> optionPaneSound());	// �ر�����
		optionPane.setBtAboutOnMouseClicked(e -> optionPaneAbout());	// ��About
		optionPane.setBtHowToPlayOnMouseClicked(e -> optionPaneHowToPlay());// ��HowToPlay
		optionPane.setBtLogOnMouseClicked(e -> optionPaneLog());		// �򿪸�����־
		optionPane.setBtBackgroundOnMouseClicked(e -> optionPaneBkColor());	// �л�����

		logPane.setBtCloseOnMouseClicked(e -> logPaneClose());			// �رո�����־
		aboutPane.setBtCloseOnMouseClicked(e -> aboutPaneClose());		// �ر�About
		howToPlayPane.setBtCloseOnMouseClicked(e -> howToPlayPaneClose());// �ر�HowToPlay

		gamePane.getStatusProperty().addListener(e -> gameOver());		// ��Ϸ����

		endPane.setBtTryAgainOnMouseClicked(e -> endPaneTryAgain());	// ���¿�ʼ
		endPane.setOnKeyPressed(e -> {									// ���¿ո��
			if (e.getCode() == KeyCode.SPACE)
				endPaneTryAgain();
		});
	}

	private void show() {
		// ����ʽ��ʾ����
		Show.setNode(background);
		Show.setNode(menuPane);
		Show showGamePane = new Show(gamePane);
		showGamePane.setOnFinished(e -> gamePane.initGame());
		this.getChildren().add(background);
		this.getChildren().add(gamePane);
		if (firstPlay) {
			this.getChildren().add(howToPlayPane);
		} else {
			this.getChildren().add(menuPane);
			menuPane.requestFocus();
		}
		
		// �رռ��ض���
		loadingPane.stop();
		this.getChildren().removeAll(loadingPane, loadingText);
		loadingPane = null;

		// ���ű�������
		if (soundSwitch)
			playMusic.playBkMusic();
	}

	public void close() {
		this.saveGame();
		playMusic.stopBkMusic();
	}

	private void menuPanePlay() {
		if (menuPane.status) {
			menuPane.status = false;
			Fade fade = new Fade(menuPane);
			fade.setOnFinished(f -> {
				this.getChildren().remove(menuPane);
				gamePane.play();
				gamePane.requestFocus();
			});
		}
	}

	private void menuPaneOption() {
		if (menuPane.status) {
			menuPane.status = false;
			// ����ʽ��ʾ Pane
			Show.setNode(optionPane);
			this.getChildren().add(optionPane);
			optionPane.status = true;

			// ����ʽ���� Pane
			Fade fade = new Fade(menuPane);
			// ��ȫ�������Ƴ��� Pane
			fade.setOnFinished(f -> this.getChildren().remove(menuPane));
		}
	}

	private void optionPaneClose() {
		if (optionPane.status) {
			optionPane.status = false;
			Show.setNode(menuPane);
			this.getChildren().add(menuPane);
			menuPane.status = true;
			menuPane.requestFocus();
			Fade fade = new Fade(optionPane);
			fade.setOnFinished(f -> this.getChildren().remove(optionPane));
		}
	}

	private void optionPaneSound() {
		if (optionPane.getBtSoundStatus()) {
			optionPane.setBtSound(false);
			playMusic.stopBkMusic();
			this.saveGame();
		} else {
			optionPane.setBtSound(true);
			playMusic.playBkMusic();
			this.saveGame();
		}
	}

	private void optionPaneAbout() {
		if (optionPane.status) {
			optionPane.status = false;
			Fade fade = new Fade(optionPane);
			fade.setOnFinished(f -> {
				this.getChildren().remove(optionPane);
				Show.setNode(aboutPane);
				this.getChildren().add(aboutPane);
				aboutPane.status = true;
			});
		}
	}
	
	private void optionPaneLog() {
		if (optionPane.status) {
			optionPane.status = false;
			Fade fade = new Fade(optionPane);
			fade.setOnFinished(f -> {
				this.getChildren().remove(optionPane);
				Show.setNode(logPane);
				this.getChildren().add(logPane);
				logPane.status = true;
			});
		}
	}

	private void optionPaneBkColor() {
		if (BackgroundPane.bkColor == BackgroundPane.BkColor.blue) {
			background.setBkColor(BackgroundPane.BkColor.green);
			optionPane.setBtBk(BackgroundPane.BkColor.green);
			gamePane.setFloorColor(BackgroundPane.BkColor.green);
			this.saveGame();
		} else if (BackgroundPane.bkColor == BackgroundPane.BkColor.green) {
			background.setBkColor(BackgroundPane.BkColor.blue);
			optionPane.setBtBk(BackgroundPane.BkColor.blue);
			gamePane.setFloorColor(BackgroundPane.BkColor.blue);
			this.saveGame();
		}
	}
	
	private void optionPaneHowToPlay() {
		if (optionPane.status) {
			optionPane.status = false;
			Fade fade = new Fade(optionPane);
			fade.setOnFinished(f -> {
				this.getChildren().remove(optionPane);
				Show.setNode(howToPlayPane);
				this.getChildren().add(howToPlayPane);
				howToPlayPane.status = true;
			});
		}
	}

	private void aboutPaneClose() {
		if (aboutPane.status) {
			aboutPane.status = false;
			Fade fade = new Fade(aboutPane);
			fade.setOnFinished(f -> {
				this.getChildren().remove(aboutPane);
				Show.setNode(optionPane);
				this.getChildren().add(optionPane);
				optionPane.status = true;
			});
		}
	}
	
	private void logPaneClose() {
		if (logPane.status) {
			logPane.status = false;
			Fade fade = new Fade(logPane);
			fade.setOnFinished(f -> {
				this.getChildren().remove(logPane);
				Show.setNode(optionPane);
				this.getChildren().add(optionPane);
				optionPane.status = true;
			});
		}
	}
	
	private void howToPlayPaneClose() {
		if (howToPlayPane.status) {
			howToPlayPane.status = false;
			Show.setNode(menuPane);
			this.getChildren().add(menuPane);
			menuPane.status = true;
			menuPane.requestFocus();
			Fade fade = new Fade(howToPlayPane);
			fade.setOnFinished(f -> this.getChildren().remove(howToPlayPane));
		}
	}

	private void gameOver() {
		if (gamePane.getStatusProperty().get() == 0) {
			this.setBestScore(gamePane.getScore());
			this.saveGame();
			endPane.setScore(gamePane.getScore(), this.bestScore);
			gamePane.setOnBallHideFinished(e -> {
				Show.setNode(endPane);
				this.getChildren().add(endPane);
				endPane.requestFocus();
				endPane.status = true;
			});
		}
	}

	private void endPaneTryAgain() {
		if (endPane.status) {
			endPane.status = false;
			// ������Ϸ
			gamePane.resetAnimation();
			gamePane.setOnResetFinished(e -> {
				// ��ʾ menuPane
				Show.setNode(menuPane);
				this.getChildren().add(menuPane);
				menuPane.status = true;
				menuPane.requestFocus();
			});
			gamePane.playResetAnimation();

			menuPane.setBestScore(this.bestScore);

			// ���� endPane
			Fade fade = new Fade(endPane);
			fade.setOnFinished(f -> {
				this.getChildren().remove(endPane);
			});
		}
	}

	private void saveGame() {
		String saveMessage = String.format("%10d%1d%1d", this.bestScore, BackgroundPane.getMode(),
				PlayMusic.getStatus());
		SaveGame.save(saveMessage);
	}

	private void setBestScore(int score) {
		this.bestScore = bestScore < score ? score : bestScore;
	}

}
