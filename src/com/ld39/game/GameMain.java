package com.ld39.game;

import javax.swing.JFrame;

public class GameMain {

	private static final String GAME_TITLE = "Battery Rush";
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 450;
	public static Game game;
	
	public static void main(String [] args) {
		JFrame frame = new JFrame(GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		game = new Game(GAME_WIDTH, GAME_HEIGHT);
		frame.add(game);
		frame.pack();
		frame.setVisible(true);
		frame.setIconImage(Resources.icon);
	}
	
}
