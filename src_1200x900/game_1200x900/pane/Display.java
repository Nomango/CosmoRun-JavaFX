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
		
		// �����Ӱ
		pane.getChildren().add(Floors.floorsShadow);
		// ���
		pane.getChildren().add(Floors.group);
		// �Ʒְ�
		pane.getChildren().add(scoreText);
		// С��
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

	// ��ʼ��Ϸ
	public static void play() {
		moveAnimation.play();
		processTimeline.play();
		status = true;
	}
	
	// ������Ϸ
	public static void stop() {
		moveAnimation.stop();
		processTimeline.stop();
		status = false;
	}
	
	// ������Ϸ����
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
	
	// ����������Ϸ��������ʱ�Ķ���
	public static void setOnResetFinished(EventHandler<ActionEvent> e) {
		resetAnimation.setOnFinished(e);
	}

	public static void initGame() {
		// �÷�����
		Game.score = 0;
		// �ٶȳ�ʼ��
		speed = 0.6f;
		// ��հ��
		Floors.clear();
		// ��ʾС��
		ball.show();

		// ����ͷ���
		Floor headFloor = FloorManager.getHeadFloor();
		// �������Ϊͷ���
		Floors.setHeadFloor(headFloor);
		// ����С��ǰ���
		ball.setOn(headFloor);
		
		// ������鶯��
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(35), e -> addFloor()));
		// ���� 18 ����
		tl.setCycleCount(18);
		// ����С���ʼ����
		tl.setOnFinished(e -> ball.initDirect(Floors.get(1)));
		tl.play();
	}

	private static void initAnimation() {
		// ���������������߶���������
		pane.setCache(true);
		pane.setCacheHint(CacheHint.SPEED);
		
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
					Game.score++;
					scoreText.setText(Game.score + "");
					// ��Ϸ����
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
		// ����һ���°��
		FloorManager.show(Floors.hideFloors.get(0), status ? 300 : 200);
		Floors.hideFloors.remove(0);
		Floors.addHideFloor();
		// ����ʱǿ��ֹͣ��Ϸ
		if (Floors.hideFloors.size() != 10) {
			gameOver();
			JOptionPane.showMessageDialog(null, "  ��Ϸ����", "����", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	public static void gameOver() {
		// ֹͣ��Ϸ
		stop();
		// ������߷�
		Game.setBestScore(Game.score);
		Menu.setBestScore(Game.getBestScore());
		GameOver.setScore(Game.score, Game.getBestScore());
		// ��ռƷְ�
		scoreText.setText(null);
		// С����ʧ��������ʱ����ʾ GameOver ���
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