package comp50gd;

import java.awt.Color;

/*
 * Contains global constants--'rules' for the game.
 */
public class Settings {
	public static final double MIN_DIFFICULTY = 1.0, MAX_DIFFICULTY = 5.0;
	public static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 600;
	public static String TITLEBAR = "CORE";
	public static final double BOUND_UP = 0.2 * WINDOW_HEIGHT, BOUND_DN = 0.03 * WINDOW_HEIGHT;
	public static final int FRAME_RATE_PERIOD_MILLIS = 5;
	public static boolean DEBUG = true, HACKS = false;	
	public static Color DEFAULT_BACKGROUND_COLOR = Color.black;
	public static Color DEFAULT_PLAYER_COLOR = Color.red;
	public static Color DEFAULT_ENEMY_COLOR = Color.blue;
	public static Color DEFAULT_BULLET_COLOR = Color.green;
	public static final int BEGIN_LIVES_REMAINING = 3;
	public static final double POWER_UP_DROP_RATE = 0.25;
	
	public static void print(String s)
	{
		if(DEBUG)
			System.out.print(s);
	}
	public static void println(String s)
	{
		if(DEBUG)
			System.out.println(s);
	}	

	
}
