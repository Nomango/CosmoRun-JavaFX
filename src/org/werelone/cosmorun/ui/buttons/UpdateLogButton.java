package org.werelone.cosmorun.ui.buttons;

import org.werelone.cosmorun.ui.Text;
import org.werelone.cosmorun.ui.Text.TextFont;
import org.werelone.cosmorun.util.GameData;
import org.werelone.cosmorun.util.Language;
import org.werelone.cosmorun.util.Property;

public class UpdateLogButton extends BaseButton{
	private Text text;
	private Text text_cn;
	
	public UpdateLogButton() {
		super.initCustom(Property.get("about-updatelogbtn-w"), Property.get("about-updatelogbtn-h"));
		this.init();
	}
	
	private void init() {
		text = new Text(Language.getText("en-about-updatelog"), Property.get("about-updatelogbtn-text-x"), Property.get("about-updatelogbtn-text-y"), TextFont.GillSans, 45, false);
		text_cn = new Text(Language.getText("cn-about-updatelog"), Property.get("about-updatelogbtn-text-cn-x"), Property.get("about-updatelogbtn-text-cn-y"), TextFont.MsYaHei, 30, false);
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
