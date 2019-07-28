package game.pane.log;

import game.Game;
import game.animation.Fade;
import game.baseButton.CloseButton;
import game.pane.background.MenuBackground;
import game.pane.option.Option;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Log extends Pane{
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static Text logTitle = new Text("������־");
	private static LogText logText = new LogText();
	
	public static void load() {
		pane.getChildren().add(new MenuBackground());
		initButton();
		initText();
	}

	private static void initButton() {
		closeButton.setLayoutX(110);
		closeButton.setLayoutY(110);
		closeButton.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.showPane(Option.pane);
					Option.status = true;
					logText.setTextY(0);
				});
			}
		});
		pane.getChildren().add(closeButton);
	}
	
	private static void initText() {
		logTitle.setLayoutX(240);
		logTitle.setLayoutY(130);
		logTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		logTitle.setFont(Font.font("΢���ź�",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		logTitle.setEffect(dropShadow);
		pane.getChildren().add(logTitle);
		
		logText.setLayoutY(Game.height * 0.35);
		pane.getChildren().add(logText);
	}

}

class LogText extends Pane {
	private Group logText = new Group();
	
	public LogText() {
		this.setClip(new Rectangle(Game.width, Game.height * 0.55));
		
		this.getChildren().add(logText);
		this.setOnScroll();
		
		Text logText1 = new Text(
				"v1.1  2016.6.1����\n" + 
				"     �Ż�����Ϸ������\n" + 
				"     �޸��˲���BUG\n" + 
				"     ��������Ϸ����\n" + 
				"     �����˿�ʼ��ť��ʽ\n" + 
				"     ���ڰ�ť�������ý���\n");
		Text logText2 = new Text(
				"v1.2  2016.6.10����\n" + 
				"     �޸���ĳЩ�������Ϸ�޷�������BUG\n" + 
				"     �����˱�������\n" + 
				"     �����˿�ѡ�ı�����ɫ\n" + 
				"     ���������������Ϳ�ʼ��Ϸ����\n");
		Text logText3 = new Text(
				"v1.3  2016.6.11����\n" + 
				"     �������Զ��浵����\n" + 
				"     �����˸�����־����\n" + 
				"     ��������Ϸ˵��\n");
		Text logText4 = new Text(
				"v1.4  2016.6.18����\n" + 
				"     �޸���ĳЩ����¿�ʼ��Ϸ�󱳾��������˵��Ĳ�Ӱ��BUG\n" + 
				"     �Ż����ڴ�ռ��\n" + 
				"     �����˰����Ӱ\n" + 
				"     �������µı�����ɫ\n");
		Text logTextProblem = new Text(
				"��֪������:\n" + 
				"     �ڴ��CPUռ����Ȼ�ܴ�\n" + 
				"     С���ʳ����½�����̱߳���������Ϸ����\n" + 
				"     С���ʳ�����ͬ���͵��ĸ��������һ��\n");
		
		logText1.setLayoutY(50);
		logText2.setLayoutY(430);
		logText3.setLayoutY(750);
		logText4.setLayoutY(1020);
		logTextProblem.setLayoutY(1400);
		logText.getChildren().addAll(logText1, logText2, logText3, logText4, logTextProblem);
		
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		for (int i = 0; i < logText.getChildren().size(); i++) {
			Text t = (Text)logText.getChildren().get(i);
			t.setLayoutX(270);
			t.setFill(Color.WHITE);
			t.setFont(Font.font("΢���ź�",FontWeight.SEMI_BOLD, 40));
			t.setEffect(dropShadow);
			t.setWrappingWidth(780);
		}
	}
	
	private void setOnScroll() {
		this.setOnScroll(e -> {
			logText.setLayoutY(logText.getLayoutY() + e.getDeltaY() / 1.5);
			if (logText.getLayoutY() > 0)
				logText.setLayoutY(0);
			if (logText.getLayoutY() < -1260)
				logText.setLayoutY(-1260);
		});
	}
	
	public void setTextY(double value) {
		logText.setLayoutY(value);
	}
}
