package com.ld39.game.world;

import com.ld39.game.util.Randomizer;

public class Cloud {

	private float x, y;
	private static final int VEL_X = -15;
	
	public Cloud(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void update(float delta) {
		x += VEL_X * delta;
		
		if(x <= -200) {
			// Reset to the right
			x += 1000;
			y = Randomizer.getInt(20, 100);
		}
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
}
