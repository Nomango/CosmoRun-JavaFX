package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;

public class MyBlogButton extends BaseButton{
	private Text text;
	
	public MyBlogButton() {
		super.initCustom(Property.get("about-myblogbtn-w"), Property.get("about-myblogbtn-h"));
		this.init();
	}
	
	private void init() {
		text = new Text("", Property.get("about-myblogbtn-text-x"), Property.get("about-myblogbtn-text-y"));
		changeLang();
		this.getChildren().add(text);
	}
	
	// «–ªª”Ô—‘
	public void changeLang() {
		if (GameData.language == 0) {
			text.setFont(TextFont.GillSans, 45, false);
		}
		else {
			text.setFont(TextFont.MsYaHei, 30, false);
		}
		text.setText(Language.getAutoText("about-blog"));
	}
}
