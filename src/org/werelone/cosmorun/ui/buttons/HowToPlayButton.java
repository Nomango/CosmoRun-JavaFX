package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;

public class HowToPlayButton extends BaseButton {
	private Text text;
	private Text text_cn;
	
	public HowToPlayButton() {
		super.initLarge();
		this.init();
	}
	
	private void init() {
		text = new Text(Language.getText("en-option-howtoplay"), Property.get("option-howtoplaybtn-text-x"), Property.get("option-howtoplaybtn-text-y"), TextFont.GillSans, 65, true);
		text_cn = new Text(Language.getText("cn-option-howtoplay"), Property.get("option-howtoplaybtn-text-cn-x"), Property.get("option-howtoplaybtn-text-cn-y"), TextFont.MsYaHei, 40, false);
		changeLang();
		this.getChildren().add(text);
		this.getChildren().add(text_cn);
	}
	
	// «–ªª”Ô—‘
	public void changeLang() {
		if (GameData.language == 0) {
			text.setOpacity(1);
			text_cn.setOpacity(0);
		}
		else {
			text.setOpacity(0);
			text_cn.setOpacity(1);
		}
	}
	
}
