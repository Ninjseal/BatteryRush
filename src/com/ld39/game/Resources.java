package com.ld39.game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import com.ld39.game.graphics.Animation;
import com.ld39.game.graphics.Frame;

public class Resources {

	public static BufferedImage mainmenu, icon, duck, selector, jump, ground, battery1,
					run1, run2, run3, run4, run5, cloud1, cloud2;;
	public static BufferedImage battery[], block[];
	public static AudioClip hit, onJump, onCollect, discharged;
	public static Color skyBlue;
	public static Animation runAnim;
	
	public static void init() {
		// Load the assets
		mainmenu = loadImage("mainmenu.png");
		
		icon = loadImage("battery-icon.png");
		selector = loadImage("selector.png");
		
		run1 = loadImage("run_anim1.png");
		run2 = loadImage("run_anim2.png");
		run3 = loadImage("run_anim3.png");
		run4 = loadImage("run_anim4.png");
		run5 = loadImage("run_anim5.png");
		duck = loadImage("duck.png");
		jump = loadImage("jump.png");
		
		ground = loadImage("ground.png");
		cloud1 = loadImage("cloud1.png");
		cloud2 = loadImage("cloud2.png");
		
		block = new BufferedImage[2];
		battery = new BufferedImage[2];
		block[0] = loadImage("block1.png");
		block[1] = loadImage("block2.png");
		battery[0] = loadImage("battery1.png");
		battery[1] = loadImage("battery2.png");
		
		Frame[] runFrames = {new Frame(run1, .1f), new Frame(run2, .1f), new Frame(run3, .1f),
				new Frame(run4, .1f), new Frame(run5, .1f)};
		
		runAnim = new Animation(runFrames);
		
		skyBlue = new Color(208, 244, 247);
		
		hit = loadSound("hit.wav");
		onJump = loadSound("onJump.wav");
		onCollect = loadSound("onCollect.wav");
		discharged = loadSound("discharged.wav");
	}
	
	private static AudioClip loadSound(String filename) {
		URL fileURL = Resources.class.getResource("/resources/" + filename);
		return Applet.newAudioClip(fileURL);
	}
	
	private static BufferedImage loadImage(String filename) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(Resource.class.getResource("/resources/" + filename));
		} catch(IOException e) {
			System.out.println("Error while reading: " + filename);
			e.printStackTrace();
		}
		return img;
	}
	
}
