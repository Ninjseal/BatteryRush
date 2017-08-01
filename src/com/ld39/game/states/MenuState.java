package com.ld39.game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.ld39.game.GameMain;
import com.ld39.game.Resources;

public class MenuState extends State {

	private int currentSelection;
	
	@Override
	public void init() {
		currentSelection = 0;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Resources.mainmenu, 0, 0, null);
		if(currentSelection == 0) {
			g.drawImage(Resources.selector, 335, 241, null);
		} else {
			g.drawImage(Resources.selector, 335, 291, null);
		}
	}

	@Override
	public void onClick(MouseEvent e) {
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER) {
			if(currentSelection == 0) {
				setCurrentState(new PlayState());
			} else if(currentSelection == 1) {
				GameMain.game.exit();
			}
		} else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			currentSelection = 0;
		} else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			currentSelection = 1;
		}
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
