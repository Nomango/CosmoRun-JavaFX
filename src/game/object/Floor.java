package game.object;

import game.object.Ball.Direct;
import game.object.FloorManager.MainMode;
import game.object.FloorManager.Mode;
import javafx.scene.shape.Polygon;

public class Floor extends Polygon {
	public Mode mode;			// ���������
	public MainMode mainMode;	// �������
	public Direct direct;		// ��鷽�򣨷����޸�С����
	public Polygon shadow = new Polygon();	// ��Ӱ
	public double[] range;
	
	public Floor() {
	}

	public Floor(Mode mode, MainMode mainMode, Direct direct, double x, double y) {
		this.mode = mode;
		this.mainMode = mainMode;
		this.direct = direct;
		this.setLayoutX(x);
		this.setLayoutY(y);
	}
	
	// �жϵ�(x, y)�Ƿ��ڰ����
	@Override
	public boolean contains(double x, double y) {
		return super.contains(x - this.getLayoutX(), y - this.getLayoutY());
	}

}
