package game.object;

import game.Game;
import game.pane.BackgroundPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;

public class FloorsGroup extends Group{
	private Floor hide;
	
	public Floor getHide() {
		return hide;
	}
	
	public void resetHide() {
		hide = new Floor(this.get(this.size() - 1));
	}
	
	public void showHide() {
		hide.show(this);
	}
	
	public void setHide(Floor f) {
		hide = f;
	}
	
	public boolean add(Floor f) {
		return this.getChildren().add(f);
	}
	
	public Floor get(int i) {
		return (Floor)this.getChildren().get(i);
	}
	
	public int indexOf(Floor f) {
		return this.getChildren().indexOf(f);
	}
	
	public boolean remove(Floor f) {
		return this.getChildren().remove(f);
	}
	
	public int size() {
		return this.getChildren().size();
	}
	
	public boolean isOutOfFloors() {
		return !this.contains(Game.width / 2 - this.getTranslateX(), Game.height / 2 - this.getTranslateY());
	}
	
	public void setFloorColor(BackgroundPane.BkColor bkColor) {
		for (Node f: this.getChildren()) {
			((Floor)f).setColor(bkColor);
		}
		hide.setColor(bkColor);
	}
	
	public void setOnHideAllFinished(EventHandler<ActionEvent> e) {
		this.get(0).setOnHideFinished(e);
	}
	
	public void hideAll() {
		for (Node f: this.getChildren()) {
			((Floor)f).hide(this);
		}
	}

}
