package game_800x600.object;

import game_800x600.object.Ball.Direct;
import game_800x600.object.FloorManager.MainMode;
import game_800x600.object.FloorManager.Mode;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Polygon;

public class Floor extends Polygon {
	public Mode mode;			// ���������
	public MainMode mainMode;	// �������
	public Direct direct;		// ��鷽�򣨷����޸�С����
	public Polygon shadow = new Polygon();	// ��Ӱ
	public double[] range;		// �����жϰ�鷶Χ����������
	private DoubleProperty x = new SimpleDoubleProperty(0);
	private DoubleProperty y = new SimpleDoubleProperty(0);
	

	public Floor(Mode mode, MainMode mainMode, Direct direct, double x, double y) {
		this.mode = mode;
		this.mainMode = mainMode;
		this.direct = direct;
		this.setCenter(x, y);
		
		// �������İ��� (x, y)
		this.layoutXProperty().bind(this.x);
		this.layoutYProperty().bind(this.y);
	}
	
	/*
	 * ���ð����������
	 */
	public void setCenter(double x, double y) {
		this.x.set(x);
		this.y.set(y);
	}
	
	/*
	 * �õ�������� X ����
	 */
	public double getX() {
		return this.x.doubleValue();
	}
	
	/*
	 * �õ�������� Y ����
	 */
	public double getY() {
		return this.y.doubleValue();
	}
	
	/*
	 * �жϵ�(x, y)�Ƿ��ڰ���ڣ��� Javadoc��
	 * @see javafx.scene.Node#contains(double, double)
	 */
	@Override
	public boolean contains(double x, double y) {
		return super.contains(x - this.x.get(), y - this.y.get());
	}
	
	/*
	 * �жϰ��λ���Ƿ����������ͻ
	 */
	public boolean isCollisionWith(Floor f) {
		for (int i = 0; i < 16; i += 2) {
			if (this.contains(f.x.get() + f.range[i], f.y.get() + f.range[i + 1])) {
				return true;
			}
		}
		return false;
	}

}
