package game.pane;

import game.Game;
import game.button.CloseButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HowToPlayPane extends Pane{
	public boolean status = true;
	private CloseButton closeButton = new CloseButton();
	private Rectangle background1 = new Rectangle(0, 0, Game.width, Game.height * 0.25);
	private Rectangle background2 = new Rectangle(0, Game.height * 0.25, Game.width, Game.height);
	private Text howToPlayTitle = new Text("How To Play");
	private Rectangle space = new Rectangle(140, 550, 500, 125);
	private Hand hand = new Hand();
	private Group light = new Group();
	private Circle ball = new Circle(1000, 700 - 55 * 4, 25);
	private Polygon arrow = new Polygon();
	private Text howToPlayText;
	
	public HowToPlayPane() {
		initBackground();
		init();
		
		this.setCache(true);
		this.setCacheHint(CacheHint.SPEED);
	}
	
	private void initBackground() {
		background1.setFill(Color.rgb(30, 56, 113, 0.8));
		background2.setFill(Color.rgb(32, 45, 98, 0.9));
		this.getChildren().addAll(background1, background2);
	}
	
	private void init() {
		closeButton.setLayoutX(110);
		closeButton.setLayoutY(110);
		this.getChildren().add(closeButton);
		
		howToPlayTitle.setLayoutX(Game.width / 2 - 180);
		howToPlayTitle.setLayoutY(130);
		howToPlayTitle.setFill(Color.hsb(0, 0.0, 1.0, 0.90));
		howToPlayTitle.setFont(Font.font("Œ¢»Ì—≈∫⁄",FontWeight.BOLD, 55));
		DropShadow dropShadow = new DropShadow(5, 3, 3, Color.hsb(0, 0.0, 0.2, 0.3));
		dropShadow.setInput(new BoxBlur(2, 2, 1));
		howToPlayTitle.setEffect(dropShadow);
		this.getChildren().add(howToPlayTitle);
		
		howToPlayText = new Text("Press Space to make a turn");
		howToPlayText.setLayoutX(110);
		howToPlayText.setLayoutY(400);
		howToPlayText.setFill(Color.WHITE);
		howToPlayText.setFont(Font.font("Œ¢»Ì—≈∫⁄",FontWeight.BOLD, 45));
		howToPlayText.setEffect(dropShadow);
		this.getChildren().add(howToPlayText);
		
		space.setArcWidth(40);
		space.setArcHeight(40);
		space.setStroke(Color.hsb(0, 0.0, 1.0, 0.90));
		space.setStrokeWidth(5);
		space.setFill(null);
		space.setEffect(dropShadow);
		this.getChildren().add(space);
		
		hand.setLayoutX(450);
		hand.setLayoutY(600);
		hand.setEffect(dropShadow);
		this.getChildren().add(hand);
		
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
		this.getChildren().add(ball);
		
		arrow.getPoints().addAll(
				0.0, 0.0, 95.0, -55.0, 
				77.7, -65.0, 167.5, -82.5, 
				132.7, -32.2, 127.7 - 5 * 1.732, -36.2 - 5, 
				50.0, 0.0, 150.0, 57.7, 150.0 - 25, 57.7 + 25 / 1.73);
		arrow.setLayoutX(1000 - 210);
		arrow.setLayoutY(700 - 120);
		arrow.setFill(Color.hsb(0, 0.0, 1.0, 0.5));
		arrow.setEffect(dropShadow);
		this.getChildren().add(arrow);
		
		Timeline tl = new Timeline(new KeyFrame(Duration.millis(500), e -> {
			if (hand.getScaleX() == 1.0) {
				hand.setScaleX(0.8);
				hand.setScaleY(0.8);
				this.getChildren().add(light);
			} else {
				hand.setScaleX(1.0);
				hand.setScaleY(1.0);
				this.getChildren().remove(light);
			}
		}));
		tl.setCycleCount(-1);
		tl.play();
	}
	
	public void setBtCloseOnMouseClicked(EventHandler<? super MouseEvent> e) {
		this.closeButton.setOnMouseClicked(e);
	}
	
	private void createSixFloor() {
		this.getChildren().add(new Floor(1000, 700));
		this.getChildren().add(new Floor(1000 - 95, 700 - 55));
		this.getChildren().add(new Floor(1000 - 95 * 2, 700 - 55 * 2));
		this.getChildren().add(new Floor(1000 - 95, 700 - 55 * 3));
		this.getChildren().add(new Floor(1000, 700 - 55 * 4));
		this.getChildren().add(new Floor(1000 + 95, 700 - 55 * 5));
	}
	
	private class Floor extends Polygon{
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

	private class Hand extends Group {
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
