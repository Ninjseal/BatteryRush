package com.ld39.game.world;

import java.awt.Rectangle;

import com.ld39.game.util.Randomizer;

public class Battery {
	
	private float x, y;
	private int width, height;
	private Rectangle boundBox;
	private boolean visible;
	private int chargePower;
	private int textureIndex;
	
	private static final int UPPER_Y = 275;
	private static final int LOWER_Y = 355;
	
	public Battery(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		boundBox = new Rectangle((int) x, (int) y, width, height);
		visible = false;
		// Red Battery -> lose power
		if(Randomizer.getInt(2) == 0) {
			chargePower = -Randomizer.getInt(5, 15);
			textureIndex = 0;
		// Green Battery -> gain power
		} else {
			chargePower = Randomizer.getInt(10, 20);
			textureIndex = 1;
		}
	}
	
	public void update(float delta, float velX) {
		x += velX * delta;
		if(x <= -50) {
			reset();
		}
		updateBoundBox();
	}
	
	public void updateBoundBox() {
		boundBox.setBounds((int) x, (int) y, width, height);
	}
	
	public void reset() {
		visible = true;
		// 2 in 3 chance of becoming an Upper Battery
		if(Randomizer.getInt(3) == 0) {
			y = LOWER_Y;
		} else {
			y = UPPER_Y;
		}
		
		x += 1000;
	}
	
	public void onCollide(Player p) {
		visible = false;
		p.onCharge(chargePower);
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public Rectangle getBoundBox() {
		return boundBox;
	}
	
	public int getTexIndex() {
		return textureIndex;
	}
	
}
