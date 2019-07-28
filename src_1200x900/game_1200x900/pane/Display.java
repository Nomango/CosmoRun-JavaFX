package game_1200x900.pane;

import javax.swing.JOptionPane;

import game_1200x900.Game;
import game_1200x900.object.Ball;
import game_1200x900.object.Floor;
import game_1200x900.object.FloorManager;
import game_1200x900.object.Floors;
import game_1200x900.object.Ball.Direct;
import game_1200x900.object.FloorManager.MainMode;
import game_1200x900.pane.menu.Menu;
import game_1200x900.pane.over.GameOver;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Display {
	public static boolean status = false;
	public static Pane pane = new Pane();
	private static Ball ball = new Ball();
	private static Timeline moveAnimation;
	private static Timeline processTimeline;
	private static Timeline resetAnimation;
	private static Text scoreText = new Text(null);
	private static float speed;

	public static void load() {
		initText();
		initAnimation();
		
		// 板块阴影
		pane.getChildren().add(Floors.floorsShadow);
		// 板块
		pane.getChildren().add(Floors.group);
		// 计分板
		pane.getChildren().add(scoreText);
		// 小球
		pane.getChildren().add(ball);
	}
	
	private static void initText() {
		scoreText.setLayoutX(Game.width / 2 - 20);
		scoreText.setLayoutY(120);
		scoreText.setFill(Color.WHITE);
		scoreText.setFont(Font.font("Gill Sans MT Condensed",FontWeight.BOLD, 83));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		scoreText.setEffect(dropShadow);
	}

	// 开始游戏
	public static void play() {
		moveAnimation.play();
		processTimeline.play();
		status = true;
	}
	
	// 结束游戏
	public static void stop() {
		moveAnimation.stop();
		processTimeline.stop();
		status = false;
	}
	
	// 重置游戏动画
	public static void playResetAnimation() {
		Floors.hideAll();
		Floors.setOnHideAllFinished(e -> initGame());
		
		double stepX = -Floors.moveX / 70;
		double stepY = -Floors.moveY / 70;
		resetAnimation = new Timeline(new KeyFrame(Duration.millis(7), e -> {
				Floors.move(stepX, stepY);
		}));
		resetAnimation.setCycleCount(70);
		resetAnimation.play();
	}
	
	// 设置重置游戏动画结束时的动作
	public static void setOnResetFinished(EventHandler<ActionEvent> e) {
		resetAnimation.setOnFinished(e);
	}

	public static void initGame() {
		// 得分清零
		Game.score = 0;
		// 速度初始化
		speed = 0.6f;
		// 清空板块
		Floors.clear();
		// 显示小球
		ball.show();

		// 创建头板块
		Floor headFloor = FloorManager.getHeadFloor();
		// 将板块设为头板块
		Floors.setHeadFloor(headFloor);
		// 设置小球当前板块
		ball.setOn(headFloor);
		
		// 创建板块动画
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(35), e -> addFloor()));
		// 创建 18 块板块
		tl.setCycleCount(18);
		// 设置小球初始方向
		tl.setOnFinished(e -> ball.initDirect(Floors.get(1)));
		tl.play();
	}

	private static void initAnimation() {
		// 降低面板质量，提高动画流畅性
		pane.setCache(true);
		pane.setCacheHint(CacheHint.SPEED);
		
		// 板块移动动画
		moveAnimation = new Timeline(new KeyFrame(Duration.millis(5), e -> move()));
		// 动画无限循环
		moveAnimation.setCycleCount(-1);
		
		processTimeline = new Timeline(new KeyFrame(Duration.millis(15), e -> {
			// 小球超出当前板块范围，进入下一块
			if (!ball.isOn()) {
				// 小球出界游戏结束
				if (Floors.isOutOfFloors()) {
					gameOver();
				} else {
					// 小球进入下一板块
					ball.setOn(Floors.get(Floors.indexOf(ball.getOn()) + 1));
					// 添加板块
					addFloor();
					// 板块数量超过 20 删除尾板块
					if (Floors.size() > 19) {
						FloorManager.hide(Floors.get(Floors.size() - 20), 200);
					}
					// 加分
					Game.score++;
					scoreText.setText(Game.score + "");
					// 游戏加速
					speed = (float)((-1.667e-6 * Game.score + 0.0013) * Game.score + 0.6);
				}
			}
		}));
		processTimeline.setCycleCount(-1);
	}

	private static void move() {
		switch (ball.direct) {
		case DOWN:
			Floors.move(0, -2 * speed);
			break;
		case LEFT_DOWN:
			Floors.move(1.732 * speed, -1 * speed);
			break;
		case LEFT_UP:
			Floors.move(1.732 * speed, 1 * speed);
			break;
		case RIGHT_DOWN:
			Floors.move(-1.732 * speed, -1 * speed);
			break;
		case RIGHT_UP:
			Floors.move(-1.732 * speed, 1 * speed);
			break;
		case UP:
			Floors.move(0, 2 * speed);
			break;
		}
	}

	private static void addFloor() {
		// 创建一个新板块
		FloorManager.show(Floors.hideFloors.get(0), status ? 300 : 200);
		Floors.hideFloors.remove(0);
		Floors.addHideFloor();
		// 出错时强行停止游戏
		if (Floors.hideFloors.size() != 10) {
			gameOver();
			JOptionPane.showMessageDialog(null, "  游戏出错！", "警告", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public static void gameOver() {
		// 停止游戏
		stop();
		// 设置最高分
		Game.setBestScore(Game.score);
		Menu.setBestScore(Game.getBestScore());
		GameOver.setScore(Game.score, Game.getBestScore());
		// 清空计分板
		scoreText.setText(null);
		// 小球消失动画结束时，显示 GameOver 面板
		ball.setOnHideFinished(b -> {
			Game.toFront(GameOver.pane);
			GameOver.pane.requestFocus();
			GameOver.status = true;
		});
		ball.hide();
	}

	public static void changeDirect() {
		if (ball.direct == ball.getOn().direct) {
			for (int i = Floors.indexOf(ball.getOn()) + 1; i < Floors.size() - 1; i++) {
				if (Floors.get(i).direct != ball.direct) {
					if (ball.getOn().mainMode == Floors.get(i).mainMode) {
						ball.direct = Floors.get(i).direct;
					}
					else if (ball.getOn().mainMode == MainMode.F1) {
						if (Floors.get(i).mainMode == MainMode.F3) {
							switch (Floors.get(i).direct) {
							case UP:
								ball.direct = Direct.RIGHT_UP;
								break;
							case DOWN:
								ball.direct = Direct.LEFT_DOWN;
								break;
							default:
								break;
							}
						} else if (Floors.get(i).mainMode == MainMode.F2) {
							switch (Floors.get(i).direct) {
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
						if (Floors.get(i).mainMode == MainMode.F1) {
							switch (Floors.get(i).direct) {
							case RIGHT_UP:
								ball.direct = Direct.RIGHT_DOWN;
								break;
							case LEFT_DOWN:
								ball.direct = Direct.LEFT_UP;
								break;
							default:
								break;
							}
						} else if (Floors.get(i).mainMode == MainMode.F3) {
							switch (Floors.get(i).direct) {
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
						if (Floors.get(i).mainMode == MainMode.F2) {
							switch (Floors.get(i).direct) {
							case RIGHT_DOWN:
								ball.direct = Direct.DOWN;
								break;
							case LEFT_UP:
								ball.direct = Direct.UP;
								break;
							default:
								break;
							}
						} else if (Floors.get(i).mainMode == MainMode.F1) {
							switch (Floors.get(i).direct) {
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
	
	public static void changeColor() {
		Floors.setFloorColor();
	}

}