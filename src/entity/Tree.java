package entity;

import entity.item.Item;
import graphics.Screen;
import graphics.Sprite;
import map.Map;
import sound.Sound;

public class Tree extends WorkableEntity {
	private float chopped = 100;

	public Tree(int x, int y) {
		super(x, y);
		sprite = Sprite.treebottom;
		extraSprite = Sprite.treetop;
		setVisible(true);
	}

	public void render(Screen screen) {
		screen.renderEntity(x, y, sprite);
		screen.renderEntity(x, y - 16, extraSprite);
	}

	public boolean work(Map level) {
		if (chopped > 0) {
			if (chopped%20==0) Sound.speelGeluid(Sound.woodChopping);
			chopped--;
			return false;
		} else {
			remove();
			level.entities.remove(this);
			level.addItem(new Item("Logs", this.x, this.y, Sprite.logs, "Wooden logs", true, 5));
			level.getTile(x>>4, y>>4).setSolid(false);
			return true;
		}

	}

}
