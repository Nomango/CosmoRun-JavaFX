package game.object;

import game.object.Ball.Direct;
import game.object.FloorManager.MainMode;
import game.object.FloorManager.Mode;
import javafx.scene.shape.Polygon;

public class Floor extends Polygon {
	public Mode mode;			// 板块主类型
	public MainMode mainMode;	// 板块类型
	public Direct direct;		// 板块方向（方便修改小球方向）
	public Polygon shadow = new Polygon();	// 阴影
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
	
	// 判断点(x, y)是否在板块内
	@Override
	public boolean contains(double x, double y) {
		return super.contains(x - this.getLayoutX(), y - this.getLayoutY());
	}

}
