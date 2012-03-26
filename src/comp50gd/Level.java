package comp50gd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

/*
 * The elements in a global concept of a Level.
 */
public abstract class Level {

	protected MainGame maingame;
	protected long duration_in_seconds;
	protected TreeMap<Double, GameSprite> sprites;
	protected double player_begin_x, player_begin_y;
	protected Background background;
	protected boolean twoLayer;
	protected Background background2;
	protected int height1, height2;

	// TODO: Object backgroundImage;

	/**
	 * Constructs a new object of type Level.
	 * 
	 * @param difficulty
	 *            a
	 **/
	public Level(MainGame game, double difficulty) {
		this.maingame = game;
		sprites = new TreeMap<Double, GameSprite>();
		initialize();
	}

	protected void initialize() {
		loadSprites();
	}

	protected abstract void loadSprites();
	
	/**
	 * Returns an instance of the specific sprite that this level specifies as its quintessential enemy.
	 * 
	 * @return A specified enemy, or null if none is specified.
	 */
	public abstract GameSprite unloadLevelCelebrityGameSprite();
	
	public abstract String title();
	
	public abstract String beginLevelMessage();
	
	public abstract String endLevelSuccessMessage();

	public abstract String endLevelFailureMessage();
	
	public void resetLevel()
	{
		loadSprites();
	}
	
	public int shipsLeft()
	{
		return sprites.size();
	}

	public GameSprite unloadFirstSprite(double timestamp) {
		if (sprites.headMap(timestamp).size() > 0) {
			return sprites.remove(sprites.headMap(timestamp).firstKey());
		} else {
			return null;
		}
	}

	/**
	 * Returns all GameSprite objects to be introduced by the specified
	 * timestep.
	 * 
	 * @param timestamp
	 *            The specified timestep.
	 * @return a Collection of GameSprite objects if one exists; null otherwise.
	 */
	public synchronized Collection<GameSprite> unloadAllSprites(double timestamp) {
		Collection<GameSprite> unloadedSprites = new ArrayList<GameSprite>();
		int iterations = sprites.headMap(timestamp).size();
		if (iterations == 0) {
			return null;
		} else {
			for (int i = 0; i < iterations; i++) {
				unloadedSprites.add(unloadFirstSprite(timestamp));
			}
			return unloadedSprites;
		}
	}

	/**
	 * Calculates the duration of the level, based on the specified length, or
	 * the entirety of the sprites Map, whichever happens last.
	 * 
	 * @return the actual duration of the level.
	 */
	public double timeOfLastAddition() {
		return sprites.lastKey();
	}

	/**
	 * Returns the top node in the heap--the time at which the next object in 'sprites' will be added to the requesting context.
	 * 
	 * @return the key of the next object.
	 */
	public double timeOfNextAddition() {
		return sprites.firstKey();
	}
	
	/**
	 * Returns the field given to the constructor as the duration of the level.
	 * 
	 * @post The duration may not allow for the entire Map of GameSprite objects
	 *       to be introduced into the game.
	 * @return the specified duration of the level.
	 */
	public long getDuration() {
		return duration_in_seconds;
	}
	
	protected abstract void setPlayerOrigin();

}
