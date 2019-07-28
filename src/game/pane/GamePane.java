package game.pane;

import game.Game;
import game.object.Ball;
import game.object.Floor;
import game.object.Ball.Direct;
import game.object.FirstFloor;
import game.object.Floor.MainMode;
import game.object.FloorsGroup;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GamePane extends Pane {
	private FloorsGroup floors = new FloorsGroup();
	private Ball ball = new Ball();
	private Timeline moveAnimation;
	private Timeline processTimeline;
	private Timeline resetAnimation;
	private DoubleProperty statusProperty = new SimpleDoubleProperty(0);
	private Text scoreText = new Text("");
	private int score;

	public GamePane() {
		this.setMinWidth(Game.width);
		this.setMinHeight(Game.height);
		this.setOnMousePressed(e -> {
			if (this.getStatusProperty().get() == 1) {
				this.changeDirect();
			}
		});
		this.setOnKeyPressed(e -> {
			if (this.getStatusProperty().get() == 1 && e.getCode() == KeyCode.SPACE)
				this.changeDirect();
		});

		initText();
		initAnimation();
		this.getChildren().add(floors);
		this.getChildren().add(scoreText);
		this.getChildren().add(ball);
	}
	
	private void initText() {
		scoreText.setLayoutX(Game.width / 2 - 20);
		scoreText.setLayoutY(120);
		scoreText.setFill(Color.WHITE);
		scoreText.setFont(Font.font("微软雅黑",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		scoreText.setEffect(dropShadow);
	}
	
	public DoubleProperty getStatusProperty() {
		return statusProperty;
	}

	public void play() {
		moveAnimation.play();
		processTimeline.play();
		statusProperty.set(1);
	}
	
	public void pause() {
		moveAnimation.pause();
		processTimeline.pause();
		statusProperty.set(-1);
	}
	
	public void stop() {
		moveAnimation.stop();
		processTimeline.stop();
		statusProperty.set(0);
	}
	
	public int getScore() {
		return score;
	}
	
	public void resetAnimation() {
		double stepX = floors.getTranslateX() / 70;
		double stepY = floors.getTranslateY() / 70;
		resetAnimation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
				floors.setTranslateX(floors.getTranslateX() - stepX);
				floors.setTranslateY(floors.getTranslateY() - stepY);
		}));
		resetAnimation.setCycleCount(70);
	}
	
	public void playResetAnimation() {
		floors.hideAll();
		floors.setOnHideAllFinished(e -> this.initGame());
		this.resetAnimation.play();
	}
	
	public void setOnResetFinished(EventHandler<ActionEvent> e) {
		this.resetAnimation.setOnFinished(e);
	}
	
	public void setOnBallHideFinished(EventHandler<ActionEvent> e) {
		ball.setOnHideFinished(e);
	}

	public void initGame() {
		score = 0;
		floors.getChildren().clear();
		ball.show();

		Floor floor0 = new FirstFloor();
		floors.setHide(floor0);
		
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(60), e -> addFloor()));
		tl.setCycleCount(18);
		tl.setOnFinished(e -> {
			ball.setOn(floor0);
			ball.initDirect(floors.get(1));
		});
		tl.play();
	}

	private void initAnimation() {
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
		
		moveAnimation = new Timeline(new KeyFrame(Duration.millis(2), e -> move()));
		moveAnimation.setCycleCount(-1);
		
		processTimeline = new Timeline(new KeyFrame(Duration.millis(15), e -> {
			if (!ball.isOn(floors.getTranslateX(), floors.getTranslateY())) {
				ball.setOn(floors.get(floors.indexOf(ball.getOn()) + 1));
					scoreText.setText(++score + "");
					addFloor();
					if (floors.size() > 20) {
						floors.get(floors.size() - 21).hide(floors);
					}
			}
			if (floors.isOutOfFloors()) {
				scoreText.setText("");
				ball.hide();
				this.stop();
			}
		}));
		processTimeline.setCycleCount(-1);
	}

	private void move() {
		switch (ball.direct) {
		case DOWN:
			moveFloor(0, -2);
			break;
		case LEFT_DOWN:
			moveFloor(1.732, -1);
			break;
		case LEFT_UP:
			moveFloor(1.732, 1);
			break;
		case RIGHT_DOWN:
			moveFloor(-1.732, -1);
			break;
		case RIGHT_UP:
			moveFloor(-1.732, 1);
			break;
		case UP:
			moveFloor(0, 2);
			break;

		}
	}

	private void moveFloor(double dx, double dy) {
		floors.setTranslateX(floors.getTranslateX() + dx / 4.5);
		floors.setTranslateY(floors.getTranslateY() + dy / 4.5);
	}

	private void addFloor() {
		// 创建一个新板块
		Floor f = new Floor(floors.getHide());

		// 判断位置是否和其他板块冲突
		if (f.isCollisionWith(floors)) {
			// 重置隐藏板块
			floors.resetHide();
			// 重新添加板块
			addFloor();
		} else {
			// 将板块加入到面板中
			floors.showHide();
			floors.setHide(f);
		}
	}

	private void changeDirect() {
		if (ball.direct == ball.getOn().direct) {
			for (int i = floors.indexOf(ball.getOn()) + 1; i < floors.size() - 1; i++) {
				if (floors.get(i).direct != ball.direct) {
					if (ball.getOn().mainMode == floors.get(i).mainMode) {
						ball.direct = floors.get(i).direct;
					}
					else if (ball.getOn().mainMode == MainMode.F1) {
						if (floors.get(i).mainMode == MainMode.F3) {
							switch (floors.get(i).direct) {
							case UP:
								ball.direct = Direct.RIGHT_UP;
								break;
							case DOWN:
								ball.direct = Direct.LEFT_DOWN;
								break;
							default:
								break;
							}
						} else if (floors.get(i).mainMode == MainMode.F2) {
							switch (floors.get(i).direct) {
							case DOWN:
								ball.direct = Direct.RIGHT_DOWN;
								break;
							case UP:
								ball.direct = Direct.LEFT_UP;
								break;
							default:
								break;
							}
						}
					} else if (ball.getOn().mainMode == MainMode.F2) {
						if (floors.get(i).mainMode == MainMode.F1) {
							switch (floors.get(i).direct) {
							case RIGHT_UP:
								ball.direct = Direct.RIGHT_DOWN;
								break;
							case LEFT_DOWN:
								ball.direct = Direct.LEFT_UP;
								break;
							default:
								break;
							}
						} else if (floors.get(i).mainMode == MainMode.F3) {
							switch (floors.get(i).direct) {
							case LEFT_DOWN:
								ball.direct = Direct.DOWN;
								break;
							case RIGHT_UP:
								ball.direct = Direct.UP;
								break;
							default:
								break;
							}
						}
					} else if (ball.getOn().mainMode == MainMode.F3) {
						if (floors.get(i).mainMode == MainMode.F2) {
							switch (floors.get(i).direct) {
							case RIGHT_DOWN:
								ball.direct = Direct.DOWN;
								break;
							case LEFT_UP:
								ball.direct = Direct.UP;
								break;
							default:
								break;
							}
						} else if (floors.get(i).mainMode == MainMode.F1) {
							switch (floors.get(i).direct) {
							case RIGHT_DOWN:
								ball.direct = Direct.RIGHT_UP;
								break;
							case LEFT_UP:
								ball.direct = Direct.LEFT_DOWN;
								break;
							default:
								break;
							}
						}
					}
					break;
				}
			}
		} else {
			ball.direct = ball.getOn().direct;
		}
	}
	
	public void setFloorColor(BackgroundPane.BkColor bkColor) {
		floors.setFloorColor(bkColor);
	}

}