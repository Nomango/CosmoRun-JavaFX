package game;

/**
 * @author 宫泽先生
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
	private Text loadingText = new Text("加载中...");
	private BackgroundPane background; // 背景层
	private GamePane gamePane;			// 游戏层
	private MenuPane menuPane;			// 主菜单界面
	private OptionPane optionPane;		// 设置界面
	private AboutPane aboutPane;		// 关于界面
	private LogPane logPane;			// 更新日志
	private HowToPlayPane howToPlayPane;// 游戏说明
	private EndPane endPane;			// 游戏结束界面
	private PlayMusic playMusic = new PlayMusic();
	private int bestScore;
	private int bkColor;
	private boolean soundSwitch;
	private boolean firstPlay = true;

	public MainPane() {
		// 加载动画
		this.loadScene();
		// 读取存档
		loadArchive();
		// 加载游戏
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
		// 设置加载界面文字属性
		loadingText.setLayoutX(Game.width / 2 - 70);
		loadingText.setLayoutY(Game.height / 1.5);
		loadingText.setFill(Color.BLACK);
		loadingText.setFont(Font.font("微软雅黑", FontWeight.BOLD, 40));
		DropShadow dropShadow = new DropShadow(3, 2, 2, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		loadingText.setEffect(dropShadow);
		this.getChildren().add(loadingText);

		// 设置加载动画属性
		loadingPane.setBackground(null);
		loadingPane.setTime(Math.random() + 1.0);
		loadingPane.setOnFinished(e -> show());
		loadingPane.play();
		this.getChildren().add(loadingPane);
	}

	private void load() {
		// 加载游戏界面
		background = new BackgroundPane(bkColor);
		gamePane = new GamePane();
		menuPane = new MenuPane(bestScore);
		optionPane = new OptionPane(bkColor, soundSwitch);
		aboutPane = new AboutPane();
		logPane = new LogPane();
		howToPlayPane = new HowToPlayPane();
		endPane = new EndPane();

		menuPane.setBtOptionOnMouseClicked(e -> menuPaneOption());		// 点击选项按钮
		menuPane.setBtPlayOnMouseClicked(e -> menuPanePlay());			// 点击开始按钮
		menuPane.setOnKeyPressed(e -> {									// 按下空格键
			if (e.getCode() == KeyCode.SPACE)
				menuPanePlay();
		});

		optionPane.setBtCloseOnMouseClicked(e -> optionPaneClose());	// 关闭Option
		optionPane.setBtSoundOnMouseClicked(e -> optionPaneSound());	// 关闭声音
		optionPane.setBtAboutOnMouseClicked(e -> optionPaneAbout());	// 打开About
		optionPane.setBtHowToPlayOnMouseClicked(e -> optionPaneHowToPlay());// 打开HowToPlay
		optionPane.setBtLogOnMouseClicked(e -> optionPaneLog());		// 打开更新日志
		optionPane.setBtBackgroundOnMouseClicked(e -> optionPaneBkColor());	// 切换背景

		logPane.setBtCloseOnMouseClicked(e -> logPaneClose());			// 关闭更新日志
		aboutPane.setBtCloseOnMouseClicked(e -> aboutPaneClose());		// 关闭About
		howToPlayPane.setBtCloseOnMouseClicked(e -> howToPlayPaneClose());// 关闭HowToPlay

		gamePane.getStatusProperty().addListener(e -> gameOver());		// 游戏结束

		endPane.setBtTryAgainOnMouseClicked(e -> endPaneTryAgain());	// 重新开始
		endPane.setOnKeyPressed(e -> {									// 按下空格键
			if (e.getCode() == KeyCode.SPACE)
				endPaneTryAgain();
		});
	}

	private void show() {
		// 淡入式显示界面
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
		
		// 关闭加载动画
		loadingPane.stop();
		this.getChildren().removeAll(loadingPane, loadingText);
		loadingPane = null;

		// 播放背景音乐
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
			// 淡入式显示 Pane
			Show.setNode(optionPane);
			this.getChildren().add(optionPane);
			optionPane.status = true;

			// 淡出式隐藏 Pane
			Fade fade = new Fade(menuPane);
			// 完全淡出后移除该 Pane
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
			// 重置游戏
			gamePane.resetAnimation();
			gamePane.setOnResetFinished(e -> {
				// 显示 menuPane
				Show.setNode(menuPane);
				this.getChildren().add(menuPane);
				menuPane.status = true;
				menuPane.requestFocus();
			});
			gamePane.playResetAnimation();

			menuPane.setBestScore(this.bestScore);

			// 隐藏 endPane
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
