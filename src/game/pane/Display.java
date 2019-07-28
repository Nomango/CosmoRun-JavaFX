package game.pane;

import game.Game;
import game.object.Ball;
import game.object.Floor;
import game.object.FloorManager;
import game.object.FloorManager.MainMode;
import game.object.Floors;
import game.object.Ball.Direct;
import game.pane.over.GameOver;
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
		scoreText.setFont(Font.font("΢���ź�",FontWeight.BOLD, 55));
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
		
		double stepX = Floors.group.getTranslateX() / 70;
		double stepY = Floors.group.getTranslateY() / 70;
		resetAnimation = new Timeline(new KeyFrame(Duration.millis(10), e -> {
				Floors.setTranslateX(Floors.getTranslateX() - stepX);
				Floors.setTranslateY(Floors.getTranslateY() - stepY);
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
		// ��հ��
		Floors.clear();
		// ��ʾС��
		ball.show();

		// ����ͷ���
		Floor firstFloor = FloorManager.getFirstFloor();
		// ��ͷ�����Ϊ����
		Floors.setHide(firstFloor);
		// ����С��ǰ���
		ball.setOn(firstFloor);
		
		// ������鶯��
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(40), e -> addFloor()));
		// ���� 18 ����
		tl.setCycleCount(15);
		// ����С���ʼ����
		tl.setOnFinished(e -> ball.initDirect(Floors.get(1)));
		tl.play();
	}

	private static void initAnimation() {
		// ���������������߶���������
		pane.setCache(true);
		pane.setCacheHint(CacheHint.SPEED);
		
		// ����ƶ�����
		moveAnimation = new Timeline(new KeyFrame(Duration.millis(2), e -> move()));
		// ��������ѭ��
		moveAnimation.setCycleCount(-1);
		
		processTimeline = new Timeline(new KeyFrame(Duration.millis(15), e -> {
			// С�򳬳���ǰ��鷶Χ��������һ��
			if (!ball.isOn(Floors.getTranslateX(), Floors.getTranslateY())) {
				// С�������Ϸ����
				if (Floors.isOutOfFloors()) {
					// ֹͣ��Ϸ
					stop();
					// ������߷�
					Game.setBestScore(Game.score);
					// ��ռƷְ�
					scoreText.setText(null);
					// С����ʧ��������ʱ����ʾ GameOver ���
					ball.setOnHideFinished(b -> {
						Game.showPane(GameOver.pane);
						GameOver.pane.requestFocus();
						GameOver.status = true;
					});
					ball.hide();
				} else {
					// С�������һ���
					ball.setOn(Floors.get(Floors.indexOf(ball.getOn()) + 1));
					// ��Ӱ��
					addFloor();
					// ����������� 20 ɾ��β���
					if (Floors.size() > 16) {
						FloorManager.hide(Floors.get(Floors.size() - 17));
					}
					// �ӷ�
					Game.score++;
					scoreText.setText(Game.score + "");
				}
			}
		}));
		processTimeline.setCycleCount(-1);
	}

	private static void move() {
		switch (ball.direct) {
		case DOWN:
			Floors.move(0, -2);
			break;
		case LEFT_DOWN:
			Floors.move(1.732, -1);
			break;
		case LEFT_UP:
			Floors.move(1.732, 1);
			break;
		case RIGHT_DOWN:
			Floors.move(-1.732, -1);
			break;
		case RIGHT_UP:
			Floors.move(-1.732, 1);
			break;
		case UP:
			Floors.move(0, 2);
			break;
		}
	}

	private static void addFloor() {
		// ����һ���°��
		Floor f = FloorManager.getNewFloor(Floors.getHide());

		// �ж�λ���Ƿ����������ͻ
		if (Floors.isCollisionWith(f)) {
			// �������ذ��
			Floors.resetHide();
			// ������Ӱ��
			addFloor();
		} else {
			// �������뵽�����
			Floors.showHide();
			Floors.setHide(f);
		}
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