package entity;

import entity.item.Item;
import graphics.Sprite;
import map.Map;
import sound.Sound;

public class Ore extends Entity {
	private float mined = 100;
	private Sprite itemSprite;
	private OreType type;

	public Ore(int x, int y, OreType type) {
		super(x, y);
		this.type = type;
		getSprite();
	}

	private void getSprite() {
		switch (type) {
		case IRON:
			sprite1 = Sprite.ironOre;
			itemSprite = Sprite.ironChunk;
			break;
		case GOLD:
			sprite1 = Sprite.goldOre;
			itemSprite = Sprite.goldChunk;
			break;
		case COAL:
			sprite1 = Sprite.coalOre;
			break;
		default:
			sprite1 = null;
		}
	}

	public boolean mine(Map level) {
		if (mined > 0){
			if (mined%20==0) Sound.speelGeluid(Sound.stoneMining);
			mined--;
			return false;
		} else {
			remove();
			level.entities.remove(this);
			level.getTile(x, y).setSolid(false);
			level.entities.add(new Item(type.name().toLowerCase() + " ore", this.x, this.y, itemSprite, true));
			return true;
		}
	}
}