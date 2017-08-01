package com.ld39.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.ld39.game.GameMain;
import com.ld39.game.Resources;
import com.ld39.game.world.Battery;
import com.ld39.game.world.Block;
import com.ld39.game.world.Cloud;
import com.ld39.game.world.Player;

public class PlayState extends State {

	private Player player;
	private ArrayList<Block> blocks;
	private ArrayList<Battery> batteries;
	private Cloud cloud1, cloud2;
	
	private Font scoreFont;
	private int playerScore = 0;
	
	private static final int BLOCK_HEIGHT = 50;
	private static final int BLOCK_WIDTH = 20;
	private static final int PLAYER_WIDTH = 66;
	private static final int PLAYER_HEIGHT = 92;
	private static final int BATTERY_HEIGHT = 30;
	private static final int BATTERY_WIDTH = 20;
	private int blockSpeed = -200;
	
	@Override
	public void init() {
		player = new Player(160, GameMain.GAME_HEIGHT - 45 - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		blocks = new ArrayList<Block>();
		batteries = new ArrayList<Battery>();
		cloud1 = new Cloud(100, 100);
		cloud2 = new Cloud(500, 50);
		scoreFont = new Font("SansSerif", Font.BOLD, 25);
		
		for(int i = 0; i < 4; i++) {
			Block b = new Block(i * 270, GameMain.GAME_HEIGHT - 95, BLOCK_WIDTH, BLOCK_HEIGHT);
			blocks.add(b);
		}
		
		int counter = 0;
		for(int i = 0; i < 6; i++) {
			Battery b = new Battery(counter + (i+1) * 100, GameMain.GAME_HEIGHT - 95, BATTERY_WIDTH, BATTERY_HEIGHT);
			counter += i * 150;
			batteries.add(b);
		}
	}

	@Override
	public void update(float delta) {
		if(!player.isAlive()) {
			setCurrentState(new GameOverState(playerScore / 100));
		}
		playerScore += 1;
		if(playerScore % 500 == 0 && blockSpeed > -300) {
			blockSpeed -= 10;
		}
		
		if(playerScore % 100 == 0) {
			player.losePower(10);
		}
		
		cloud1.update(delta);
		cloud2.update(delta);
		Resources.runAnim.update(delta);
		player.update(delta);
		updateBlocks(delta);
		updateBatteries(delta);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Resources.skyBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		renderPlayer(g);
		renderBlocks(g);
		renderBatteries(g);
		renderSun(g);
		renderClouds(g);
		g.drawImage(Resources.ground, 0, 405, null);
		renderGUI(g);
	}

	private void updateBlocks(float delta) {
		for(Block b : blocks) {
			b.update(delta, blockSpeed);
			
				if(b.isVisible()) {
					if(player.isDucked() && b.getBoundBox().intersects(player.getDuckBoundBox())) {
						b.onCollide(player);
					} else if(!player.isDucked() && b.getBoundBox().intersects(player.getBoundBox())) {
						b.onCollide(player);
					}
				}
		}
	}
	
	private void updateBatteries(float delta) {
		for(Battery b : batteries) {
			b.update(delta, blockSpeed);
			
				if(b.isVisible()) {
					if(player.isDucked() && b.getBoundBox().intersects(player.getDuckBoundBox())) {
						playerScore += 100;
						b.onCollide(player);
					} else if(!player.isDucked() && b.getBoundBox().intersects(player.getBoundBox())) {
						playerScore += 100;
						b.onCollide(player);
					}
				}
		}
	}
	
	private void renderGUI(Graphics g) {
		g.setFont(scoreFont);
		g.setColor(Color.GRAY);
		g.drawString("SCORE: " + playerScore / 100, 20, 30);
		g.drawString("BATTERY: " + player.getBatteryPower() + " / " + 100, 20, 60);
	}
	
	private void renderPlayer(Graphics g) {
		if(player.isGrounded()) {
			if(player.isDucked()) {
				g.drawImage(Resources.duck, (int) player.getX(), (int) player.getY(), null);
			} else {
				Resources.runAnim.render(g, (int) player.getX(), (int) player.getY(),
						player.getWidth(), player.getHeight());
			}
		} else {
			g.drawImage(Resources.jump, (int) player.getX(), (int) player.getY(), player.getWidth(),
					player.getHeight(), null);
		}
	}
	
	private void renderBlocks(Graphics g) {
		for(Block b : blocks) {
			if(b.isVisible()) {
				g.drawImage(Resources.block[b.getTexIndex()], (int) b.getX(), (int) b.getY(), BLOCK_WIDTH, BLOCK_HEIGHT, null);
			}
		}
	}
	
	private void renderBatteries(Graphics g) {
		for(Battery b : batteries) {
			if(b.isVisible()) {
				g.drawImage(Resources.battery[b.getTexIndex()], (int) b.getX(), (int) b.getY(), BATTERY_WIDTH, BATTERY_HEIGHT, null);
			}
		}
	}
	
	private void renderSun(Graphics g) {
		g.setColor(Color.orange);
		g.fillOval(715, -85, 170, 170);
		g.setColor(Color.yellow);
		g.fillOval(725, -75, 150, 150);
	}
	
	private void renderClouds(Graphics g) {
		g.drawImage(Resources.cloud1, (int) cloud1.getX(), (int) cloud2.getY(), 100, 60, null);
		g.drawImage(Resources.cloud2, (int) cloud2.getX(), (int) cloud2.getY(), 120, 80, null);
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			player.jump();
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN || 
				e.getKeyCode() == KeyEvent.VK_S) {
			player.duck();
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
