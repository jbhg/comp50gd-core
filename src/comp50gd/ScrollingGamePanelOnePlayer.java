package comp50gd;

/**
 * DrawPanel class: The 'canvas' of the execution of this game.
 * 
 * Based on the tutorial by 'farrell2k', accessed 3/29/2010: http://forum.codecall.net/java-tutorials/15969-double-buffering-movement-collision-detection.html
 * 
 * @author Joel B. Greenberg
 **/
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScrollingGamePanelOnePlayer extends JPanel implements KeyListener {

	private MainGame maingame;

	private PlayerShip player;
	private ArrayList<Ship> enemy, enemy_dead;
	private ArrayList<BattleStuff> battlestuff, battlestuff_dead;
	private ArrayList<Level> level;

	private int current_score;
	private int current_level;
	private int current_lives_remaining;
	private int aliens_total;
	private int aliens_killed;

	private BufferedImage buffer;

	private boolean restartMusic;
	
	private long time_elapsed_millis, time_duration_millis;
	private long beforeTime, timeDiff, sleepTime, pausedTime;
	private volatile boolean isPaused, isInProgress, levelEnded;
	
	Sound soundFile_level1 = new Sound("comp50gd/sound/bg.aif");
	Sound soundFile_level2 = new Sound("comp50gd/sound/sea.aif");
	//Sound soundFile_level3 = new Sound("comp50gd/sound/bg.aif");

	protected Color bulletColor[];
		
	public ScrollingGamePanelOnePlayer() {
		setIgnoreRepaint(true);
		addKeyListener(this);
		setFocusable(true);
		bulletColor = new Color[9];
		//level1
		bulletColor[0]=(Color.red);
		bulletColor[1]=(Color.yellow);
		bulletColor[2]=(Color.orange);
		//level2
		bulletColor[3]=(Color.black);
		bulletColor[4]=(Color.blue);
		bulletColor[5]=(Color.black);
		//level3
		bulletColor[6]=(Color.red);
		bulletColor[7]=(Color.yellow);
		bulletColor[8]=(Color.orange);
		
	}

	public double getTimeElapsed() {
		return time_elapsed_millis;
	}

	public void initialize() {

		/*
		 * Set up visual stuff.
		 */
		buffer = new BufferedImage(Settings.WINDOW_WIDTH,
				Settings.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

		/*
		 * Set up logic stuff.
		 */
		player = new PlayerShip(this.maingame, 100, 100);
		player.setGame(this.maingame);
		
		enemy = new ArrayList<Ship>();
		enemy_dead = new ArrayList<Ship>();

		level = new ArrayList<Level>();
		this.populateLevels();

		battlestuff = new ArrayList<BattleStuff>();
		battlestuff_dead = new ArrayList<BattleStuff>();

		current_level = 0;

		restartMusic = false;
		
		/*
		 * Set up audio stuff.
		 */
	}

	private void startmusic(int level) {

		System.out.println("Music level:" + level);
		
		soundFile_level1.stop();
		soundFile_level2.stop();
		//soundFile_level3.stop();

//		System.out.println("Debug: sound attempt");
		switch(level){
			case 1:
				try {
					soundFile_level1.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					soundFile_level2.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 3:
				try {
					soundFile_level1.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				break;
		}
		

//		System.out.println("Debug: sound attempt");
	}

	public void startGame(MainGame m) {
		this.maingame = m;
		initialize();
		current_level = 0;
		current_score = 0;
		current_lives_remaining = Settings.BEGIN_LIVES_REMAINING - 1;

		run();
	}

	private void run() {

		isInProgress = true;
		isPaused = false;
		levelEnded = false;

		current_level = 0;
		current_lives_remaining++;
		startmusic(current_level % 3 + 1);

		/*
		 * Game loop - continues unless:
		 * 
		 * 1. The game is stopped via the isInProgress flag (DIFFERENT from a
		 * pause) 2. The end of all levels has been reached. 3. The user has run
		 * out of lives.
		 */
		while (isInProgress && current_level < level.size() && current_lives_remaining >= 0) {
						
			player.shield_strength_current = player.shield_max;
			player.hull_strength_current = player.hull_max;
			player.gun_cooling_rate = player.gun_cooling_slow;
			player.shield_recovery_rate = player.shield_recovery_slow;
			aliens_killed = 0;
			
			/*
			 * We need to set up the information for this level.
			 */
			aliens_total = level.get(current_level).shipsLeft();
			enemy.clear();
			battlestuff.clear();
			
			player.time_last_shot = 0;
			
			time_elapsed_millis = 0;
			time_duration_millis = level.get(current_level).getDuration();

			player.setPosition(level.get(current_level).player_begin_x, level
					.get(current_level).player_begin_y);

			//isPaused = false;
			levelEnded = false;

			beforeTime = System.currentTimeMillis();

			this.setTitle(Settings.TITLEBAR + " :: "
					+ this.level.get(current_level).title());

			do {
				
				if (!isPaused) {
					if (restartMusic)
					{
						restartMusic = false;
						startmusic(current_level % 3 + 1);
					}
					try{
					//	System.out.println("Ships left (before update): " + this.level.get(current_level).shipsLeft());
					update();
					}
					catch(java.util.NoSuchElementException e)
					{
						System.out.println("There appears to be an error in the update() method.");
						System.out.println("Ships left (after update): " + this.level.get(current_level).shipsLeft());
						e.printStackTrace();
					}
					purge();
					checkCollisions();
					purge(); // Clears the collided members.
					drawBuffer("");
					drawScreen();
					timeDiff = System.currentTimeMillis() - beforeTime;
					sleepTime = Settings.FRAME_RATE_PERIOD_MILLIS - timeDiff;
					if (sleepTime <= 0)
						sleepTime = 5;
					try {
						Thread.sleep(sleepTime);
						time_elapsed_millis += sleepTime;
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

			} 
			while (!levelEnded && (this.time_duration_millis - ((System.currentTimeMillis() - this.beforeTime) / 1000)) > 0 && player.hull_strength_current > 0);
			Settings.println("Post-second loop. Level ended: " 
					+ levelEnded + ", time = " + (this.time_duration_millis - (System.currentTimeMillis() - this.beforeTime)));
			
			
			if(player.hull_strength_current > 0)
				
			//Level completed, show breakdown
			{ 
				Graphics2D b = buffer.createGraphics();
				b.setColor(Color.white);
			    b.setFont(new Font("Arial", Font.PLAIN, 32));

				b.drawString("Level " + (int) (this.current_level + 1) + " Completed",
						380, 200);
				b.drawString("You killed " + this.aliens_killed + " of " + this.aliens_total + " aliens",
						335, 300);
				b.setFont(new Font("Arial", Font.PLAIN, 24));
				b.drawString("(Press ENTER to continue)",
						365, 500);
				drawScreen();
				b.dispose();
				this.pause("");
				current_level++;	
				restartMusic = true;
				
			}
			else
			{				
				Graphics2D b = buffer.createGraphics();
				b.setColor(Color.white);
			    b.setFont(new Font("Arial", Font.PLAIN, 32));

				b.drawString("Your ship was destroyed",
						335, 200);
				b.setFont(new Font("Arial", Font.PLAIN, 24));
				b.drawString("(Press ENTER to continue)",
						365, 500);
				drawScreen();
				b.dispose();
				this.pause("");
				level.get(current_level).resetLevel();
				current_lives_remaining--;
			}
		}
		/*
		 * End-game condition goes here...
		 */
		System.exit(0);
	}

	private void populateLevels() {
		level.add(new Level_01(maingame, 1));
		level.add(new Level_02(maingame, 1));
		level.add(new Level_03(maingame, 1));
	}

	/**
	 * Loads new sprites from the level class.
	 * 
	 * FIX: Based on problem 6a in the README file, this class now reads the
	 * time of the next addition instead of creating a list and seeing if it is
	 * null.
	 */
	private void loadNewSprites() {

		/*
		 * if statement ensures that there is one or more ships left before
		 * checking the key of the next ship, as to avoid throwing an exception
		 * that could be triggered by the firstKey method in TreeMap.
		 */

		//System.out.println("Size:" + level.get(current_level).shipsLeft());
		if (level.get(current_level).shipsLeft() > 0)
		// && this.time_elapsed_millis > level.get(current_level)
		// .timeOfNextAddition())
		{

			Collection<GameSprite> unloaded = level.get(current_level)
					.unloadAllSprites(this.time_elapsed_millis);
			if (unloaded != null) {
//				System.out.println("Ships left: "
//						+ level.get(current_level).shipsLeft()
//						+ ", time elapsed: " + this.time_elapsed_millis
//						+ ", time of next add: "
//						+ level.get(current_level).timeOfNextAddition()
//						+ " unloaded list " + unloaded);

				if (unloaded != null) {
//					System.out.println("Unloaded " + unloaded.size()
//							+ " sprites at timestep "
//							+ this.time_elapsed_millis + " "
//							+ (System.currentTimeMillis() - this.beforeTime));
					
					Iterator<GameSprite> newsprites = unloaded.iterator();
					while (newsprites.hasNext()) {
						GameSprite current = newsprites.next();
						if (current instanceof EnemyShip)
							this.enemy.add((Ship) current);
						else if (current instanceof BattleStuff)
							this.battlestuff.add((BattleStuff) current);
					}
				}
			}
		}
		// else -- no gamesprites unloaded.
	}

	/**
	 * update simulates a time-click for the entire model. - calls 'move' on all
	 * objects. - adds new objects from the current level based on the current
	 * timestep.
	 */
	public void update() {
		// Loads new objects <GameSprite> from the current Level, if any.
		loadNewSprites();

		// Move the player.
		player.move();
		
		// Update player details (shield re-charge etc.)
		player.update();
		 
		// Move background
		level.get(current_level).background.move();
		if (level.get(current_level).twoLayer)
		{
			level.get(current_level).background2.move();
		}

		// Move and shoot each enemy.
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).move();
			enemy.get(i).shoot();
		}

		// Move each item.
		for (int i = 0; i < battlestuff.size(); i++) {
			battlestuff.get(i).move();
		}
		Settings.println("Enemies: " + enemy.size() + ", BattleStuff: "
				+ battlestuff.size());
	}

	/**
	 * Compares all objects to each other, and notifies them of collisions via
	 * calls to the notifyCollision() method as specified in Collidable.
	 * 
	 * @post No side-effects, which would include calls to purge().
	 */
	public void checkCollisions() {

		// Compare enemies to each other.

		// for (int i = 0; i < enemy.size(); i++) {
		// for (int j = i + 1; j < enemy.size(); j++) {
		// }
		// }

		// Compare battlestuff to battlestuff.
		// for (int i = 0; i < battlestuff.size(); i++) {
		// for (int j = i + 1; j < battlestuff.size(); j++) {
		// }
		// }

		// Compare battlestuff to enemies.
		for (int i = 0; i < enemy.size(); i++) {
			for (int j = 0; j < battlestuff.size(); j++) {
				if (battlestuff.get(j).collidesWith(enemy.get(i))) {
					battlestuff.get(j).notifyCollision(enemy.get(i));
					enemy.get(i).notifyCollision(battlestuff.get(j));
				}
			}
		}
		// Compare player to battlestuff
		for (int i = 0; i < battlestuff.size(); i++) {
			if (battlestuff.get(i).collidesWith(player)) {
				player.notifyCollision(battlestuff.get(i));
				battlestuff.get(i).notifyCollision(player);
			}
		}

		// Compare player to enemies
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).collidesWith(player)) {
				enemy.get(i).notifyCollision(player);
				player.notifyCollision(enemy.get(i));
			}
		}
	}

	/**
	 * Modifies the current score.
	 * 
	 * @param modif
	 *            An integer to be added to the current score (if negative, the
	 *            value is subtracted).
	 */
	public void modifyScore(int modif) {
		this.current_score += modif;
	}

	public void incrementKills() {
		this.aliens_killed ++;
	}
	
	public void drawBuffer(String msg) {
		Graphics2D b = buffer.createGraphics();
		
		/*
		 * Paint the background of the screen.
		 */
		b.setColor(Settings.DEFAULT_BACKGROUND_COLOR);
		//b.fillRect(0, 0, Settings.WINDOW_WIDTH, Settings.WINDOW_HEIGHT);
		 
		// draw primary background
		level.get(current_level).background.sprite().draw((Graphics) b, (int)level.get(current_level).background.x, (int)level.get(current_level).background.y);
		
		// draw enemies that are below cover
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).belowCover == true)
			{
				if (enemy.get(i).sprite == null) {
					b.fillRect((int) enemy.get(i).px(), (int) enemy.get(i)
									.py(), (int) enemy.get(i).width(), (int) enemy
									.get(i).height());
				} else {
					enemy.get(i).sprite(0)
							.draw((Graphics) b, (int) enemy.get(i).px(),
									(int) enemy.get(i).py(),
									(int) enemy.get(i).width(),
									(int) enemy.get(i).height());
				}
			}
		}
		
		// draw secondary background
		if (level.get(current_level).twoLayer)
		{
			level.get(current_level).background2.sprite().draw((Graphics) b, (int)level.get(current_level).background2.x, (int)level.get(current_level).background2.y);
		}
		
		// draw battle stuff (bullets, power-ups, explosions)
		for (int i = 0; i < battlestuff.size(); i++) {
			b.setColor(bulletColor[(int)(Math.random()*3)+(current_level*3)]);
			if (battlestuff.get(i).sprite(0) == null) {
				b.fillRect((int) battlestuff.get(i).px(), (int) battlestuff
						.get(i).py(), (int) battlestuff.get(i).width(),
						(int) battlestuff.get(i).height());
			} 
			else 
			{
				battlestuff.get(i).sprite(0)
				.draw((Graphics) b, (int) battlestuff.get(i).px(),
						(int) battlestuff.get(i).py(),
						(int) battlestuff.get(i).width(),
						(int) battlestuff.get(i).height());
			}
		}
		
		// draw player ship
		if (player.sprite(0) == null) {
			b.setColor(Settings.DEFAULT_PLAYER_COLOR);
			b.fillRect((int) player.px(), (int) player.py(), (int) player
					.width(), (int) player.height());
		} else {
			if (player.damaged > 0)
			{
				player.sprite(1).draw((Graphics) b, (int) player.px(),
					(int) player.py(), (int) player.width(),
					(int) player.height());
			}
			else if (player.shielded > 0)
			{
				player.sprite(2).draw((Graphics) b, (int) player.px(),
						(int) player.py(), (int) player.width(),
						(int) player.height());
			}
			else
			{
				player.sprite(0).draw((Graphics) b, (int) player.px(),
						(int) player.py(), (int) player.width(),
						(int) player.height());
			}
		}

		// draw enemies that are above cover
		b.setColor(Settings.DEFAULT_ENEMY_COLOR);
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).belowCover == false)
			{
				if (enemy.get(i).sprite == null) {
					b
							.fillRect((int) enemy.get(i).px(), (int) enemy.get(i)
									.py(), (int) enemy.get(i).width(), (int) enemy
									.get(i).height());
				} 
				else 
				{
					enemy.get(i).sprite(0)
							.draw((Graphics) b, (int) enemy.get(i).px(),
									(int) enemy.get(i).py(),
									(int) enemy.get(i).width(),
									(int) enemy.get(i).height());
				}
			}
		}
				
		// Print the game statistics.
		for (int i=0; i<2; i++)
		{
			if (i==0) b.setColor(Color.black);
			else b.setColor(Color.white);
			
			b.drawString("Shield" , 4+i, 14+i);
			b.drawString("Hull" , 4+i, 29+i);
			b.drawString("Gun" , 4+i,	44+i);
		
			b.drawString("Score: " + this.current_score,
				Settings.WINDOW_WIDTH - 151 +i, 14+i);
			b.drawString("Level: " + (int) (this.current_level + 1),
				Settings.WINDOW_WIDTH - 151+i, 29+i);
			b.drawString("Lives Remaining: " + this.current_lives_remaining,
				Settings.WINDOW_WIDTH - 151+i, 44+i);

			
			b.drawRect(48+i, 3+i, (int)player.getShieldMax()+1, 11);
			b.drawRect(48+i, 18+i, (int)player.getHullMax()+1, 11);
			b.drawRect(48+i, 33+i, 101, 11);
			b.drawRect(Settings.WINDOW_WIDTH/2-200+i,8+i,401,25);
		}
		FontMetrics fm = b.getFontMetrics();
		java.awt.geom.Rectangle2D rect = fm.getStringBounds(msg, b);

		int textHeight = (int) (rect.getHeight());
		int textWidth = (int) (rect.getWidth());
		int panelHeight = this.getHeight();
		int panelWidth = this.getWidth();

		// Center text horizontally and vertically
		int x = (panelWidth - textWidth) / 2;
		int y = (panelHeight - textHeight) / 2 + fm.getAscent();

		b.drawString(msg, x, y); // Draw the string.
		
		// draw status bars
		b.setColor(Color.blue);
		b.fillRect(50, 5, (int)Math.max(0, player.getCurrentShield()), 10);
		b.setColor(Color.green);
		b.fillRect(50, 20, (int)Math.max(0,player.getCurrentHull()), 10);
		b.setColor(Color.red);
		b.fillRect(50, 35, (int)(player.gunCooled()/player.gun_cooling_rate*100), 10);
		b.setColor(Color.gray);
		b.fillRect(Settings.WINDOW_WIDTH/2-198,10,
				Math.min(400,(int)(((double)((System.currentTimeMillis()-this.beforeTime)/1000+1)/this.time_duration_millis)*400))
				,24);
		
		b.dispose();

	}

	public void drawScreen() {
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.drawImage(buffer, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void setTitle(String title) {
		this.maingame.setTitle(title);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (!isPaused) {
			if (key == KeyEvent.VK_LEFT) {
				player.right = false;
				player.left = true;
			}
			if (key == KeyEvent.VK_RIGHT) {
				player.left = false;
				player.right = true;
			}
			if (key == KeyEvent.VK_UP) {
				player.down = false;
				player.up = true;
			}
			if (key == KeyEvent.VK_DOWN) {
				player.up = false;
				player.down = true;
			}
			if (key == KeyEvent.VK_SPACE) {
				player.shoot();
			}
			if (key == KeyEvent.VK_E && Settings.HACKS) {
				if (this.enemy.size() > 0) {
					enemy.remove(0);
				}
			}
			if (key == KeyEvent.VK_K && Settings.HACKS) {
				this.battlestuff.clear();
			}
			if (key == KeyEvent.VK_L
					&& level.get(current_level)
							.unloadLevelCelebrityGameSprite() != null && Settings.HACKS) {
				enemy.add((Ship) this.level.get(current_level)
						.unloadLevelCelebrityGameSprite());
			}
		}
		if (key == KeyEvent.VK_D) {
			System.out.println("Debugging statements about to be changed to: "
					+ !Settings.DEBUG);
			Settings.DEBUG = !Settings.DEBUG;
		}
		if (key == KeyEvent.VK_P && this.isPaused==false ) {
			this.pause("");
		}
		else if ((key == KeyEvent.VK_P || key == KeyEvent.VK_ENTER)&& this.isPaused ) {
			this.unPause();
		}
		
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT)
			player.left = false;
		if (key == KeyEvent.VK_RIGHT)
			player.right = false;
		if (key == KeyEvent.VK_UP)
			player.up = false;
		if (key == KeyEvent.VK_DOWN)
			player.down = false;
	}

	/**
	 * Adds an object of type BattleStuff to the active loop.
	 * 
	 * Public accessibility for firing purposes that cannot be predicted.
	 * 
	 * @param bs
	 *            BattleStuff to be added to the loop.
	 */
	public void add(BattleStuff bs) {
		this.battlestuff.add(bs);
	}

	/**
	 * Enqueues an object of type BattleStuff for deletion from the active loop.
	 * 
	 * @param bs
	 *            BattleStuff to be destroyed.
	 */
	public void notifyDeath(BattleStuff bs) {
		battlestuff_dead.add(bs);
	}

	/**
	 * Enqueues an object of type EnemyShip for deletion from the active loop.
	 * 
	 * @param es
	 *            EnemyShip to be destroyed.
	 */
	public void notifyDeath(EnemyShip es) {
		enemy_dead.add(es);
	}

	/**
	 * Clears the ArrayLists 'battlestuff' and 'enemy' of GameSprites which no
	 * longer have use.
	 * 
	 * @post If the enemy ArrayList is stored, the game will be paused via a
	 *       call to pause(String).
	 */
	public void purge() {
		for (BattleStuff b : battlestuff_dead)
			battlestuff.remove(b);
		battlestuff_dead.clear();
		for (Ship s : enemy_dead)
			enemy.remove(s);
		enemy_dead.clear();
	}

	/**
	 * Pauses the game.
	 * 
	 * @param s
	 *            Message printed to the console of the reason for pause, if
	 *            any.
	 */
	public void pause(String s) {
		Settings.println(s);
		this.pausedTime = System.currentTimeMillis();
		this.isPaused = true;
	}

	/**
	 * unPause ensures that no game-time is lost during the pause.
	 */
	public void unPause() {
		this.beforeTime += (System.currentTimeMillis() - this.pausedTime);
		this.pausedTime = 0;
		this.isPaused = false;
	}

	public double player_x() {
		return player.px();
	}

	public double player_y() {
		return player.py();
	}

}