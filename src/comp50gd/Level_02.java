package comp50gd;

public class Level_02 extends Level {

	
	
	public Level_02(MainGame game, long difficulty) {
		super(game, difficulty);
		duration_in_seconds = 70;
		player_begin_x = Settings.WINDOW_WIDTH / 2;
		player_begin_y = Settings.WINDOW_HEIGHT - 50;
		height1 = 1160;
		background = new Background(this.maingame,"comp50gd/img/background2.JPG",height1,1);
		height2 = 800;
		background2 = new Background(this.maingame,"comp50gd/img/clouds.gif",height2,2);
		twoLayer = true;
	}

	/**
	 * The array of GameSprite objects which will be used in this level.
	 * 
	 * No two may have the same entry time; if they do, the data structure will
	 * only maintain the last of these.
	 */
	@Override
	protected void loadSprites() {
		sprites.put(100d, new AndreTheGiantAlien(maingame, 400, -20));
		sprites.put(200d, new AndreTheGiantAlien(maingame, 800, -20));
		sprites.put(400d, new AndreTheGiantAlien(maingame, 200, -20));
		sprites.put(600d, new AndreTheGiantAlien(maingame, 500, -20));
		sprites.put(800d, new AndreTheGiantAlien(maingame, 500, -20));
		sprites.put(1100d, new AndreTheGiantAlien(maingame, 900, -20));
		sprites.put(1200d, new AndreTheGiantAlien(maingame, 200, -20));
		sprites.put(1400d, new AndreTheGiantAlien(maingame, 300, -20));
		sprites.put(1600d, new AndreTheGiantAlien(maingame, 700, -20));
		sprites.put(1800d, new AndreTheGiantAlien(maingame, 400, -20));
		
		
		sprites.put(2500d, new SineWaveAlien(maingame, 500, -20));
		sprites.put(2700d, new SineWaveAlien(maingame, 500, -20));
		sprites.put(2900d, new SineWaveAlien(maingame, 500, -20));
		sprites.put(3100d, new SineWaveAlien(maingame, 500, -20));
		sprites.put(3300d, new SineWaveAlien(maingame, 500, -20));
		
	
		sprites.put(4302d, new SineWaveAlien(maingame, 700, -20));
		sprites.put(4502d, new SineWaveAlien(maingame, 700, -20));
		sprites.put(4702d, new SineWaveAlien(maingame, 700, -20));
		sprites.put(4902d, new SineWaveAlien(maingame, 700, -20));
		sprites.put(5102d, new SineWaveAlien(maingame, 700, -20));
		
		
		sprites.put(6103d, new SineWaveAlien(maingame, 400, -20));
		sprites.put(6303d, new SineWaveAlien(maingame, 400, -20));
		sprites.put(6503d, new SineWaveAlien(maingame, 400, -20));
		sprites.put(6703d, new SineWaveAlien(maingame, 400, -20));
		sprites.put(6903d, new SineWaveAlien(maingame, 400, -20));
		
		
		sprites.put(3001d, new SimpleShootingAlien(maingame, 100, -20, true));
		sprites.put(4001d, new SimpleShootingAlien(maingame, 700, -20, false));
		sprites.put(5001d, new SimpleShootingAlien(maingame, 300, -20, true));
		sprites.put(6001d, new SimpleShootingAlien(maingame, 800, -20, false));
		sprites.put(7001d, new SimpleShootingAlien(maingame, 200, -20, true));
		
		
		sprites.put(7500d, new LAlien(maingame, 1000, 200));
		sprites.put(8500d, new RAlien(maingame, 0, 150));
		sprites.put(9500d, new LAlien(maingame, 1000, 100));
		sprites.put(10500d, new RAlien(maingame, 1000, 200));
		sprites.put(11500d, new LAlien(maingame, 0, 150));
		sprites.put(12500d, new RAlien(maingame, 1000, 100));
	}

	@Override
	public GameSprite unloadLevelCelebrityGameSprite() {
		return new SimpleShootingAlien(maingame, 400, 300, true);
	}

	@Override
	public String beginLevelMessage() {
		return "Goal: Kill all the alien ships!";
	}

	@Override
	public String endLevelSuccessMessage() {
		return "You have killed all of the aliens!";
	}

	@Override
	public String endLevelFailureMessage() {
		return "Valiant effort, Captain. Better luck next time.";
	}

	@Override
	public String title() {
		return "Earth Atmosphere";
	}

	@Override
	protected void setPlayerOrigin() {
		player_begin_x = Settings.WINDOW_WIDTH / 2;
		player_begin_y = Settings.WINDOW_HEIGHT - 50;		
	}

}
