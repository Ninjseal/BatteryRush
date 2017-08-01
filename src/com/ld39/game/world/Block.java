package com.ld39.game.world;

import java.awt.Rectangle;

import com.ld39.game.util.Randomizer;

public class Block {

	private float x, y;
	private int width, height;
	private Rectangle boundBox;
	private boolean visible;
	private int textureIndex;
	
	private static final int UPPER_Y = 275;
	private static final int LOWER_Y = 355;
	
	public Block(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		boundBox = new Rectangle((int)x, (int) y, width, height);
		visible = false;
		if(Randomizer.getInt(1) == 0) {
			textureIndex = 0;
		} else {
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
		// 1 in 3 chance of becoming an Upper Block
		if(Randomizer.getInt(2) == 0) {
			y = UPPER_Y;
		} else {
			y = LOWER_Y;
		}
		
		x += 1000;
	}
	
	public void onCollide(Player p) {
		visible = false;
		p.pushBack(30);
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
