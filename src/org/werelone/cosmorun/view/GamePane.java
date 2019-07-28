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
	// ��Ϸ���ֵĵ���
	private static GamePane pane;
	// С��
	private Ball ball = new Ball();
	// ��Ϸ�ٶ�
	private float speed;
	// С���ƶ�����
	private Timeline moveAnimation;
	// ��Ϸ���̶���
	private Timeline processTimeline;
	// ������Ϸʱ�İ�鶯��
	private Timeline resetAnimation;
	
	public static GamePane create() {
		pane = new GamePane();
		pane.init();
		return pane;
	}
	
	public static GamePane getInstance() {
		return pane;
	}

	// ��ʼ����Ϸ��������
	public void init() {
		// ��ʼ���������
		FloorManager.init();
		// ��ʼ����Ϸ����
		initTimeline();
		// �����Ӱ
		this.getChildren().add(Floors.floorsShadow);
		// ���
		this.getChildren().add(Floors.group);
		// С��
		this.getChildren().add(ball);
	}

	// ��ʼ��Ϸ
	public void play() {
		this.moveAnimation.play();
		this.processTimeline.play();
	}
	
	// ������Ϸ
	public void stop() {
		this.moveAnimation.stop();
		this.processTimeline.stop();
	}
	
	// ��ʾ������Ϸʱ�İ�鶯��
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

	// ������Ϸ
	public void initGame() {
		// �÷�����
		GameData.flushScore();
		// �ٶȳ�ʼ��
		speed = GameData.getInitialSpeed();
		// ��հ��
		Floors.clear();
		// ��ʾС��
		ball.show();

		// ����ͷ���
		Floor headFloor = FloorManager.createHeadFloor();
		// �������Ϊͷ���
		Floors.setHeadFloor(headFloor);
		// ����С��ǰ���
		ball.setOn(headFloor);
		ball.setDirect(headFloor.direct);
		
		// ������鶯��
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(40), e -> addFloor()));
		// ���� 18 ����
		tl.setCycleCount(18);
		// ����С���ʼ����
		tl.setOnFinished(e -> RootLayout.changeFrontPane(FrontPane.startMenu));
		tl.play();
	}

	private void initTimeline() {
		// ����ƶ�����
		moveAnimation = new Timeline(new KeyFrame(Duration.millis(5), e -> move()));
		// ��������ѭ��
		moveAnimation.setCycleCount(-1);
		
		processTimeline = new Timeline(new KeyFrame(Duration.millis(15), e -> {
			// С�򳬳���ǰ��鷶Χ��������һ��
			if (!ball.isOn()) {
				// С�������Ϸ����
				if (Floors.isOutOfFloors()) {
					gameOver();
				} else {
					// С�������һ���
					ball.setOn(Floors.get(Floors.indexOf(ball.getOn()) + 1));
					// ��Ӱ��
					addFloor();
					// ����������� 20 ɾ��β���
					if (Floors.size() > 19) {
						FloorManager.hide(Floors.get(Floors.size() - 20), 200);
					}
					// �ӷ�
					TouchPane.getInstance().bonusPoint();
					// ��Ϸ����
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
		// ����һ���°��
		FloorManager.show(Floors.hideFloors.get(0), 200);
		Floors.hideFloors.remove(0);
		Floors.addHideFloor();
		// ����ʱǿ��ֹͣ��Ϸ
		if (Floors.hideFloors.size() != 10) {
			gameOver();
		}
	}
	
	public void gameOver() {
		// ֹͣ��Ϸ
		stop();
		// ˢ����߷�
		boolean newBest = GameData.flushBestScore();
		StartMenuPane.getInstance().flushBestScore();
		GameOverPane.getInstance().flushScore(newBest);
		// ��ռƷְ�
		TouchPane.getInstance().resetScoreText();
		// С����ʧ��������ʱ����ʾ GameOver ���
		ball.setOnHideFinished(b -> RootLayout.changeFrontPane(FrontPane.gameOver));
		ball.hide();
	}

	public void changeDirect() {
		// С�����뵱ǰ��鷽����ͬʱ
		if (ball.direct == ball.getOn().direct) {
			for (int i = Floors.indexOf(ball.getOn()) + 1; i < Floors.size() - 1; i++) {
				// �ҵ���һ����С����ͬ�İ��
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
