package com.ld39.game.util;

public class Randomizer {

	public static int getInt(int min, int max) {
		return (int) (min + Math.round((max - min) * Math.random()));
	}
	
	public static int getInt(int max) {
		return (int) Math.round((max * Math.random()));
	}
	
	public static long getLong(long min, long max) {
		return (min + Math.round((max - min) * Math.random()));
	}
	
	public static long getLong(long max) {
		return Math.round((max * Math.random()));
	}
	
	public static float getFloat(float min, float max) {
		return (float) (min + (max - min) * Math.random());
	}
	
	public static float getFloat(float max) {
		return (float) (max * Math.random());
	}
	
	public static double getDouble(double min, double max) {
		return (min + (max - min) * Math.random());
	}
	
	public static double getDouble(double max) {
		return (max * Math.random());
	}
	
}
