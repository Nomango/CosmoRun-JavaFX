package org.werelone.cosmorun.view;

import org.werelone.cosmorun.object.Ball;
import org.werelone.cosmorun.object.Floor;
import org.werelone.cosmorun.object.FloorManager;
import org.werelone.cosmorun.object.Floors;
import org.werelone.cosmorun.object.Ball.Direct;
import org.werelone.cosmorun.object.FloorManager.MainMode;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.view.RootLayout.FrontPane;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class GamePane extends Pane{
	// 游戏布局的单例
	private static GamePane pane;
	// 小球
	private Ball ball = new Ball();
	// 游戏速度
	private float speed;
	// 小球移动动画
	private Timeline moveAnimation;
	// 游戏进程动作
	private Timeline processTimeline;
	// 重置游戏时的板块动画
	private Timeline resetAnimation;
	
	public static GamePane create() {
		pane = new GamePane();
		pane.init();
		return pane;
	}
	
	public static GamePane getInstance() {
		return pane;
	}

	// 初始化游戏布局属性
	public void init() {
		// 初始化板块属性
		FloorManager.init();
		// 初始化游戏动作
		initTimeline();
		// 板块阴影
		this.getChildren().add(Floors.floorsShadow);
		// 板块
		this.getChildren().add(Floors.group);
		// 小球
		this.getChildren().add(ball);
	}

	// 开始游戏
	public void play() {
		this.moveAnimation.play();
		this.processTimeline.play();
	}
	
	// 结束游戏
	public void stop() {
		this.moveAnimation.stop();
		this.processTimeline.stop();
	}
	
	// 显示重置游戏时的板块动画
	public void playResetAnimation() {
		Floors.hideAll();
		
		double stepX = -Floors.moveX / 70;
		double stepY = -Floors.moveY / 70;
		resetAnimation = new Timeline(new KeyFrame(Duration.millis(5), e -> {
			Floors.move(stepX, stepY);
			BackgroundPane.getInstance().move(stepX, stepY);
		}));
		resetAnimation.setCycleCount(70);
		resetAnimation.setOnFinished(e -> pane.initGame());
		resetAnimation.play();
	}

	// 加载游戏
	public void initGame() {
		// 得分清零
		GameData.flushScore();
		// 速度初始化
		speed = GameData.getInitialSpeed();
		// 清空板块
		Floors.clear();
		// 显示小球
		ball.show();

		// 创建头板块
		Floor headFloor = FloorManager.createHeadFloor();
		// 将板块设为头板块
		Floors.setHeadFloor(headFloor);
		// 设置小球当前板块
		ball.setOn(headFloor);
		ball.setDirect(headFloor.direct);
		
		// 创建板块动画
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(40), e -> addFloor()));
		// 创建 18 块板块
		tl.setCycleCount(18);
		// 设置小球初始方向
		tl.setOnFinished(e -> RootLayout.changeFrontPane(FrontPane.startMenu));
		tl.play();
	}

	private void initTimeline() {
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
					TouchPane.getInstance().bonusPoint();
					// 游戏加速
					speed = GameData.speedUp();
				}
			}
		}));
		processTimeline.setCycleCount(-1);
	}

	private void move() {
		double x = 0, y = 0;
		switch (ball.direct) {
		case DOWN:
			y = -2 * speed;
			break;
		case LEFT_DOWN:
			x = 1.73 * speed;
			y = -1 * speed;
			break;
		case LEFT_UP:
			x = 1.73 * speed;
			y = 1 * speed;
			break;
		case RIGHT_DOWN:
			x = -1.73 * speed;
			y = -1 * speed;
			break;
		case RIGHT_UP:
			x = -1.73 * speed;
			y = 1 * speed;
			break;
		case UP:
			y = 2 * speed;
			break;
		}
		Floors.move(x, y);
		BackgroundPane.getInstance().move(x, y);
	}

	private void addFloor() {
		// 创建一个新板块
		FloorManager.show(Floors.hideFloors.get(0), 200);
		Floors.hideFloors.remove(0);
		Floors.addHideFloor();
		// 出错时强行停止游戏
		if (Floors.hideFloors.size() != 10) {
			gameOver();
		}
	}
	
	public void gameOver() {
		// 停止游戏
		stop();
		// 刷新最高分
		boolean newBest = GameData.flushBestScore();
		StartMenuPane.getInstance().flushBestScore();
		GameOverPane.getInstance().flushScore(newBest);
		// 清空计分板
		TouchPane.getInstance().resetScoreText();
		// 小球消失动画结束时，显示 GameOver 面板
		ball.setOnHideFinished(b -> RootLayout.changeFrontPane(FrontPane.gameOver));
		ball.hide();
	}

	public void changeDirect() {
		// 小球方向与当前板块方向相同时
		if (ball.direct == ball.getOn().direct) {
			for (int i = Floors.indexOf(ball.getOn()) + 1; i < Floors.size() - 1; i++) {
				// 找到第一块与小球方向不同的板块
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

}
