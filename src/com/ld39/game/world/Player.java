package com.ld39.game.world;

import java.awt.Rectangle;

import com.ld39.game.Resources;
import com.ld39.game.util.MathUtils;

public class Player {
	
	private float x, y;
	private int width, height, velY;
	private Rectangle boundBox, duckBoundBox, ground;
	
	private boolean isAlive;
	private boolean isDucked;
	private int playerLife;
	private float duckDuration = .6f;
	
	private static final int JUMP_VELOCITY = -600;
	private static final int ACCEL_GRAVITY = 1800;
	
	public Player(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		ground = new Rectangle(0, 405, 800, 45);
		boundBox = new Rectangle();
		duckBoundBox = new Rectangle();
		isAlive = true;
		isDucked = false;
		playerLife = 100;
		updateBoundBoxes();
	}
	
	public void update(float delta) {
		if(duckDuration > 0 && isDucked) {
			duckDuration -= delta;
		} else {
			isDucked = false;
			duckDuration = .6f;
		}
		
		if(!isGrounded()) {
			velY += ACCEL_GRAVITY * delta;
		} else {
			y = 406 - height;
			velY = 0;
		}
		
		y += velY * delta;
		updateBoundBoxes();
	}

	public void updateBoundBoxes() {
		boundBox.setBounds((int) x + 10, (int) y, width - 20, height);
		duckBoundBox.setBounds((int) x, (int) y + 20, width, height - 20);
	}
	
	public void jump() {
		if(isGrounded()) {
			Resources.onJump.play();
			isDucked = false;
			duckDuration = .6f;
			y -= 10;
			velY = JUMP_VELOCITY;
			updateBoundBoxes();
		}
	}
	
	public void duck() {
		if(isGrounded()) {
			isDucked = true;
		}
	}
	
	public void onCharge(int chargePower) {
		Resources.onCollect.play();
		playerLife = MathUtils.clamp(playerLife + chargePower, 0, 100);
		
		if(playerLife == 0) {
			isAlive = false;
			Resources.discharged.play();
		}
	}
	
	public void losePower(int power) {
		playerLife = MathUtils.clamp(playerLife - power, 0, 100);
		
		if(playerLife == 0) {
			isAlive = false;
			Resources.discharged.play();
		}
	}
	
	public void pushBack(int dX) {
		Resources.hit.play();
		x -= dX;
		if(x < -width / 2) {
			isAlive = false;
		}
		boundBox.setBounds((int) x, (int) y, width, height);
	}
	
	public boolean isGrounded() {
		return boundBox.intersects(ground);
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getVelY() {
		return velY;
	}

	public Rectangle getBoundBox() {
		return boundBox;
	}

	public Rectangle getDuckBoundBox() {
		return duckBoundBox;
	}

	public Rectangle getGround() {
		return ground;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isDucked() {
		return isDucked;
	}

	public float getDuckDuration() {
		return duckDuration;
	}
	
	public int getBatteryPower() {
		return playerLife;
	}
	
}
