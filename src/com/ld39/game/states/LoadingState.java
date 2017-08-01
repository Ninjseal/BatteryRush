package com.ld39.game.states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.ld39.game.Resources;

public class LoadingState extends State {
	
	@Override
	public void init() {
		Resources.init();
	}

	@Override
	public void update(float delta) {
		setCurrentState(new MenuState());
		
	}

	@Override
	public void render(Graphics g) {
		// Draw splash screen
	}

	@Override
	public void onClick(MouseEvent e) {
	}

	@Override
	public void onKeyPress(KeyEvent e) {
	}

	@Override
	public void onKeyRelease(KeyEvent e) {
	}

}
