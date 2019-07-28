package game.object;

import game.Game;
import game.object.Ball.Direct;
import javafx.animation.ScaleTransition;
import javafx.scene.CacheHint;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

public class FloorManager {
	public static enum MainMode {
		F1, F2, F3
	};

	public static enum Mode {
		F1_LEFT_UP, F1_LEFT_DOWN, F1_RIGHT_UP, F1_RIGHT_DOWN, F2_LEFT_UP, F2_DOWN, F2_UP, F2_RIGHT_DOWN, F3_UP, F3_RIGHT_UP, F3_LEFT_DOWN, F3_DOWN
	};

	public final static double radiusY = 45; // �̰��᳤
	public final static double radiusX = radiusY * 1.732;// �����᳤
	public final static double ex = 3.2; 	// �����жϷ�Χ�ı�׼��
	public static ScaleTransition hide;

	private static Mode mode;
	private static MainMode mainMode;
	private static Direct direct;
	private static double x, y;

	public static Floor getFirstFloor() {
		Floor f = new Floor();
		randomFirstMode(f);
		setCoordinate(f);
		setColor(f);
		return f;
	}

	public static Floor getNewFloor(Floor prevFloor) {
		// ������ģʽ
		randomMode(prevFloor);
		// �����°��
		Floor f = new Floor(mode, mainMode, direct, x, y);
		// ���ð������
		setCoordinate(f);
		// ���ð����ɫ
		setColor(f);
		return f;
	}
	
	public static void show(Floor f) {
		f.shadow.setScaleX(0.0);
		f.shadow.setScaleY(0.0);
		ScaleTransition st = new ScaleTransition(Duration.millis(170), f.shadow);
		st.setByX(1.0f);
		st.setByY(1.0f);
		st.play();
		
		f.setScaleX(0.0);
		f.setScaleY(0.0);
		st = new ScaleTransition(Duration.millis(200), f);
		st.setByX(1.0f);
		st.setByY(1.0f);
		st.play();
		
		Floors.add(f);
	}
	
	public static void hide(Floor f) {
		hide = new ScaleTransition(Duration.millis(100), f.shadow);
		hide.setByX(-1.0f);
		hide.setByY(-1.0f);
		hide.play();
		
		hide = new ScaleTransition(Duration.millis(200), f);
		hide.setByX(-1.0f);
		hide.setByY(-1.0f);
		hide.setOnFinished(e -> Floors.remove(f));
		hide.play();
	}

	private static void randomFirstMode(Floor f) {
		// ������״����
		f.setLayoutX(Game.width / 2);
		f.setLayoutY(Game.height / 2);

		f.mainMode = MainMode.F1;
		// ������ģʽ
		switch ((int) (Math.random() * 4)) {
		case 0:
			f.direct = Direct.LEFT_DOWN;
			f.mode = Mode.F1_LEFT_DOWN;
			break;
		case 1:
			f.direct = Direct.LEFT_UP;
			f.mode = Mode.F1_LEFT_UP;
			break;
		case 2:
			f.direct = Direct.RIGHT_DOWN;
			f.mode = Mode.F1_RIGHT_DOWN;
			break;
		case 3:
			f.direct = Direct.RIGHT_UP;
			f.mode = Mode.F1_RIGHT_UP;
			break;
		}
	}

	private static void randomMode(Floor prevFloor) {
		// ������ģʽ
		int rand = (int) (Math.random() * 4);

		switch (prevFloor.mode) {
		case F1_LEFT_UP:
			if (rand == 0) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_UP;
				x = prevFloor.getLayoutX() - radiusX / 2;
				y = prevFloor.getLayoutY() - radiusY * 1.5;
			}
			break;
		case F1_LEFT_DOWN:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getLayoutX() - radiusX / 2;
				y = prevFloor.getLayoutY() + radiusY * 1.5;
			}
			break;
		case F1_RIGHT_DOWN:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getLayoutX() + radiusX / 2;
				y = prevFloor.getLayoutY() + radiusY * 1.5;
			}
			break;
		case F1_RIGHT_UP:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 1) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 2) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_UP;
				x = prevFloor.getLayoutX() + radiusX / 2;
				y = prevFloor.getLayoutY() - radiusY * 1.5;
			}
			break;
		case F2_UP:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX / 2;
				y = prevFloor.getLayoutY() - radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F2_UP;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			}
			break;
		case F2_DOWN:
			if (rand == 0) {
				mode = Mode.F1_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX / 2;
				y = prevFloor.getLayoutY() + radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			}
			break;
		case F2_LEFT_UP:
			if (rand == 0) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY();
			} else if (rand == 1) {
				mode = Mode.F2_UP;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() + radiusY * 2;
			}
			break;
		case F2_RIGHT_DOWN:
			if (rand == 0) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY();
			} else if (rand == 1) {
				mode = Mode.F2_UP;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_DOWN;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() + radiusY * 2;
			}
			break;
		case F3_UP:
			if (rand == 0) {
				mode = Mode.F1_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX / 2;
				y = prevFloor.getLayoutY() - radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F3_UP;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() - radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			}
			break;
		case F3_DOWN:
			if (rand == 0) {
				mode = Mode.F1_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX / 2;
				y = prevFloor.getLayoutY() + radiusY * 1.5;
			} else if (rand == 1) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			}
			break;
		case F3_LEFT_DOWN:
			if (rand == 0) {
				mode = Mode.F3_UP;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() - radiusY * 2;
			} else if (rand == 1) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_LEFT_DOWN;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY() + radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_LEFT_UP;
				x = prevFloor.getLayoutX() - radiusX;
				y = prevFloor.getLayoutY();
			}
			break;
		case F3_RIGHT_UP:
			if (rand == 0) {
				mode = Mode.F3_UP;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() - radiusY * 2;
			} else if (rand == 1) {
				mode = Mode.F3_DOWN;
				x = prevFloor.getLayoutX();
				y = prevFloor.getLayoutY() + radiusY * 2;
			} else if (rand == 2) {
				mode = Mode.F3_RIGHT_UP;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY() - radiusY;
			} else if (rand == 3) {
				mode = Mode.F2_RIGHT_DOWN;
				x = prevFloor.getLayoutX() + radiusX;
				y = prevFloor.getLayoutY();
			}
			break;
		}

		switch (mode) {
		case F1_LEFT_DOWN:
		case F1_LEFT_UP:
		case F1_RIGHT_DOWN:
		case F1_RIGHT_UP:
			mainMode = MainMode.F1;
			break;
		case F2_DOWN:
		case F2_LEFT_UP:
		case F2_RIGHT_DOWN:
		case F2_UP:
			mainMode = MainMode.F2;
			break;
		case F3_DOWN:
		case F3_LEFT_DOWN:
		case F3_RIGHT_UP:
		case F3_UP:
			mainMode = MainMode.F3;
			break;
		}

		switch (mode) {
		case F1_LEFT_UP:
		case F2_LEFT_UP:
			direct = Direct.LEFT_UP;
			break;
		case F1_LEFT_DOWN:
		case F3_LEFT_DOWN:
			direct = Direct.LEFT_DOWN;
			break;
		case F1_RIGHT_UP:
		case F3_RIGHT_UP:
			direct = Direct.RIGHT_UP;
			break;
		case F1_RIGHT_DOWN:
		case F2_RIGHT_DOWN:
			direct = Direct.RIGHT_DOWN;
			break;
		case F2_DOWN:
		case F3_DOWN:
			direct = Direct.DOWN;
			break;
		case F2_UP:
		case F3_UP:
			direct = Direct.UP;
			break;
		}
	}

	private static void setCoordinate(Floor newFloor) {
		// ���ݰ�����ʹ�����״����
		switch (newFloor.mainMode) {
		case F1:
			newFloor.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			newFloor.shadow.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			newFloor.range = new double[]{0.0, -radiusY - ex, radiusX / 2 + 1.732 * ex / 2, -radiusY / 2 - ex / 2,
					radiusX + ex * 1.732, 0.0, radiusX / 2 + 1.732 * ex / 2, radiusY / 2 + ex / 2, 0.0, radiusY + ex,
					-radiusX / 2 - 1.732 * ex / 2, radiusY / 2 + ex / 2, -radiusX - ex * 1.732, 0.0,
					-radiusX / 2 - 1.732 * ex / 2, -radiusY / 2 - ex / 2};
			break;
		case F2:
			newFloor.getPoints().addAll(-radiusX / 2, -radiusY * 1.5, radiusX / 2, -radiusY * 0.5, radiusX / 2,
					radiusY * 1.5, -radiusX / 2, radiusY * 0.5);
			newFloor.shadow.getPoints().addAll(-radiusX / 2, -radiusY * 1.5, radiusX / 2, -radiusY * 0.5, radiusX / 2,
					radiusY * 1.5, -radiusX / 2, radiusY * 0.5);
			newFloor.range = new double[]{-(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 1.5, 0.0, -(radiusY + ex),
					(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 0.5, (radiusX + 1.732 * ex) / 2, 0.0,
					(radiusX + 1.732 * ex) / 2, (radiusY + ex) * 1.5, 0.0, (radiusY + ex), -(radiusX + 1.732 * ex) / 2,
					(radiusY + ex) * 0.5, -(radiusX + 1.732 * ex) / 2, 0.0};
			break;
		case F3:
			newFloor.getPoints().addAll(-radiusX / 2, -radiusY * 0.5, radiusX / 2, -radiusY * 1.5, radiusX / 2,
					radiusY * 0.5, -radiusX / 2, radiusY * 1.5);
			newFloor.shadow.getPoints().addAll(-radiusX / 2, -radiusY * 0.5, radiusX / 2, -radiusY * 1.5, radiusX / 2,
					radiusY * 0.5, -radiusX / 2, radiusY * 1.5);
			newFloor.range = new double[]{-(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 0.5, 0.0, -(radiusY + ex),
					(radiusX + 1.732 * ex) / 2, -(radiusY + ex) * 1.5, (radiusX + 1.732 * ex) / 2, 0.0,
					(radiusX + 1.732 * ex) / 2, (radiusY + ex) * 0.5, 0.0, (radiusY + ex), -(radiusX + 1.732 * ex) / 2,
					(radiusY + ex) * 1.5, -(radiusX + 1.732 * ex) / 2, 0.0};
			break;
		}
		newFloor.shadow.setLayoutX(newFloor.getLayoutX());
		newFloor.shadow.setLayoutY(newFloor.getLayoutY() + 15);
	}

	private static void setColor(Floor f) {
		// ������ͼ
		f.setCache(true);
		f.setCacheHint(CacheHint.QUALITY);
		// ��״�߿�
		f.setStrokeWidth(1.5);
		// ��״ģ����
		f.setEffect(new BoxBlur(2, 2, 1));
		// ������ɫ
		FloorManager.setColor(f, Game.bkMode);
		// ��߶���������
		f.setCacheHint(CacheHint.SPEED);

		f.shadow.setCache(true);
		f.shadow.setCacheHint(CacheHint.QUALITY);
		f.shadow.setFill(Color.hsb(0, 0.0, 0.1, 0.7));
		f.shadow.setEffect(new BoxBlur(5, 5, 5));
		f.shadow.setCacheHint(CacheHint.SPEED);
	}

	public static void setColor(Floor f, int mode) {
		// ���ݰ������������ɫ
		switch (mode) {
		case 0:
			f.setStroke(Color.rgb(0, 140, 70));
			switch (f.mode) {
			case F1_LEFT_UP:
				// �����ݶȽ���ɫ
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 211, 108)), new Stop(1, Color.rgb(0, 199, 101))));
				break;
			case F1_LEFT_DOWN:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 199, 101)), new Stop(0, Color.rgb(0, 211, 108))));
				break;
			case F1_RIGHT_DOWN:
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 199, 101)), new Stop(1, Color.rgb(0, 211, 108))));
				break;
			case F1_RIGHT_UP:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 211, 108)), new Stop(0, Color.rgb(0, 199, 101))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				f.setFill(Color.rgb(0, 160, 83));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				f.setStroke(Color.rgb(0, 91, 46));
				f.setFill(Color.rgb(0, 121, 62));
				break;
			}
			break;
		case 1:
			f.setStroke(Color.rgb(0, 114, 181));
			switch (f.mode) {
			case F1_LEFT_UP:
				// �����ݶȽ���ɫ
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 160, 254)), new Stop(1, Color.rgb(0, 145, 235))));
				break;
			case F1_LEFT_DOWN:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 145, 235)), new Stop(0, Color.rgb(0, 160, 254))));
				break;
			case F1_RIGHT_DOWN:
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(0, 145, 235)), new Stop(1, Color.rgb(0, 160, 254))));
				break;
			case F1_RIGHT_UP:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(0, 160, 254)), new Stop(0, Color.rgb(0, 145, 235))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				f.setFill(Color.rgb(56, 134, 195));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				f.setStroke(Color.rgb(0, 69, 109));
				f.setFill(Color.rgb(0, 91, 144));
				break;
			}
			break;
		case 2:
			f.setStroke(Color.rgb(173, 96, 33));
			switch (f.mode) {
			case F1_LEFT_UP:
				// �����ݶȽ���ɫ
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(243, 141, 60)), new Stop(1, Color.rgb(229, 133, 56))));
				break;
			case F1_LEFT_DOWN:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(229, 133, 56)), new Stop(0, Color.rgb(243, 141, 60))));
				break;
			case F1_RIGHT_DOWN:
				f.setFill(new LinearGradient(0.33, 0.0, 0.66, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(0, Color.rgb(229, 133, 56)), new Stop(1, Color.rgb(243, 141, 60))));
				break;
			case F1_RIGHT_UP:
				f.setFill(new LinearGradient(0.66, 0.0, 0.33, 1.0, true, CycleMethod.NO_CYCLE,
						new Stop(1, Color.rgb(243, 141, 60)), new Stop(0, Color.rgb(229, 133, 56))));
				break;
			case F2_DOWN:
			case F2_LEFT_UP:
			case F2_RIGHT_DOWN:
			case F2_UP:
				f.setFill(Color.rgb(186, 109, 46));
				break;
			case F3_DOWN:
			case F3_LEFT_DOWN:
			case F3_RIGHT_UP:
			case F3_UP:
				f.setStroke(Color.rgb(111, 65, 27));
				f.setFill(Color.rgb(151, 88, 37));
				break;
			}
			break;
		}
	}

}