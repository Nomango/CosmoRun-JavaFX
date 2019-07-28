package game.pane.howtoplay;

import game.Game;
import game.animation.Fade;
import game.baseButton.CloseButton;
import game.pane.background.MenuBackground;
import game.pane.menu.Menu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HowToPlay {
	public static Pane pane = new Pane();
	public static boolean status = false;
	private static CloseButton closeButton = new CloseButton();
	private static Text howToPlayTitle = new Text("How To Play");
	private static Rectangle space = new Rectangle(140, 550, 500, 125);
	private static Hand hand = new Hand();
	private static Group light = new Group();
	private static Circle ball = new Circle(1000, 700 - 55 * 4, 25);
	private static Polygon arrow = new Polygon();
	private static Text howToPlayText;
	private static Timeline tl;
	
	public static void load() {
		init();
	}
	
	private static void init() {
		pane.getChildren().add(new MenuBackground(Color.rgb(30, 56, 113, 0.8), 
				Color.rgb(32, 45, 98, 0.9)));
		
		closeButton.setLayoutX(110);
		closeButton.setLayoutY(110);
		closeButton.setOnMouseClicked(e -> {
			if (status) {
				status = false;
				Fade fade = new Fade(pane);
				fade.setOnFinished(f -> {
					Game.showPane(Menu.pane);
					Menu.pane.requestFocus();
					Menu.status = true;
				});
				tl.stop();
			}
		});
		pane.getChildren().add(closeButton);
		
		howToPlayTitle.setLayoutX(Game.width / 2 - 180);
		howToPlayTitle.setLayoutY(130);
		howToPlayTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		howToPlayTitle.setFont(Font.font("Œ¢»Ì—≈∫⁄",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		howToPlayTitle.setEffect(dropShadow);
		pane.getChildren().add(howToPlayTitle);
		
		howToPlayText = new Text("Press Space to make a turn");
		howToPlayText.setLayoutX(110);
		howToPlayText.setLayoutY(400);
		howToPlayText.setFill(Color.WHITE);
		howToPlayText.setFont(Font.font("Œ¢»Ì—≈∫⁄",FontWeight.BOLD, 45));
		howToPlayText.setEffect(dropShadow);
		pane.getChildren().add(howToPlayText);
		
		space.setArcWidth(40);
		space.setArcHeight(40);
		space.setStroke(Color.hsb(0, 0.0, 1.0, 0.90));
		space.setStrokeWidth(5);
		space.setFill(null);
		space.setEffect(dropShadow);
		pane.getChildren().add(space);
		
		hand.setLayoutX(450);
		hand.setLayoutY(600);
		hand.setEffect(dropShadow);
		pane.getChildren().add(hand);
		
		Rectangle r1 = new Rectangle(350, 580, 15, 50);
		Rectangle r2 = new Rectangle(420, 530, 15, 50);
		Rectangle r3 = new Rectangle(500, 530, 15, 50);
		r1.setFill(Color.WHITE);
		r2.setFill(Color.WHITE);
		r3.setFill(Color.WHITE);
		r1.setRotate(-55);
		r2.setRotate(-20);
		r3.setRotate(20);
		light.setEffect(dropShadow);
		light.getChildren().addAll(r1, r2, r3);
		
		createSixFloor();
		
		ball.setFill(Color.WHITE);
		ball.setEffect(new BoxBlur(3, 3, 2));
		pane.getChildren().add(ball);
		
		arrow.getPoints().addAll(
				0.0, 0.0, 95.0, -55.0, 
				77.7, -65.0, 167.5, -82.5, 
				132.7, -32.2, 127.7 - 5 * 1.732, -36.2 - 5, 
				50.0, 0.0, 150.0, 57.7, 150.0 - 25, 57.7 + 25 / 1.73);
		arrow.setLayoutX(1000 - 210);
		arrow.setLayoutY(700 - 120);
		arrow.setFill(Color.hsb(0, 0.0, 1.0, 0.5));
		arrow.setEffect(dropShadow);
		pane.getChildren().add(arrow);
		
		tl = new Timeline(new KeyFrame(Duration.millis(500), e -> {
			if (hand.getScaleX() == 1.0) {
				hand.setScaleX(0.8);
				hand.setScaleY(0.8);
				pane.getChildren().add(light);
			} else {
				hand.setScaleX(1.0);
				hand.setScaleY(1.0);
				pane.getChildren().remove(light);
			}
		}));
		tl.setCycleCount(-1);
	}
	
	public static void play() {
		tl.play();
	}
	
	private static void createSixFloor() {
		pane.getChildren().add(new Floor(1000, 700));
		pane.getChildren().add(new Floor(1000 - 95, 700 - 55));
		pane.getChildren().add(new Floor(1000 - 95 * 2, 700 - 55 * 2));
		pane.getChildren().add(new Floor(1000 - 95, 700 - 55 * 3));
		pane.getChildren().add(new Floor(1000, 700 - 55 * 4));
		pane.getChildren().add(new Floor(1000 + 95, 700 - 55 * 5));
	}
	
	private static class Floor extends Polygon{
		private final double radiusY = 55;			 // ∂Ã∞Î÷·≥§
		private final double radiusX = radiusY * 1.732;// ≥§∞Î÷·≥§
		
		public Floor(double x, double y) {
			this.getPoints().addAll(0.0, -radiusY, radiusX, 0.0, 0.0, radiusY, -radiusX, 0.0);
			this.setFill(Color.rgb(65, 89, 173));
			this.setLayoutX(x);
			this.setLayoutY(y);
			this.setEffect(new BoxBlur(3, 3, 2));
		}
	}

	private static class Hand extends Group {
		private Polygon hand = new Polygon();
		private Rectangle r = new Rectangle(10, 140, 80, 20);
		
		public Hand() {
			hand.getPoints().addAll(0.0, 0.0, 30.0, 0.0, 30.0, 60.0, 100.0, 60.0, 100.0, 130.0, 0.0, 130.0);
			hand.setFill(Color.WHITE);
			r.setFill(Color.WHITE);
			this.getChildren().addAll(hand, r);
			this.setRotate(-20);
		}
	}

}
