package comp50gd;

public class Level_03 extends Level {

	
	public Level_03(MainGame game, long difficulty) {
		super(game, difficulty);
		duration_in_seconds = 90;
		player_begin_x = Settings.WINDOW_WIDTH / 2;
		player_begin_y = Settings.WINDOW_HEIGHT - 50;
		height1 = 784;
		background = new Background(this.maingame,"comp50gd/img/background3.JPG",height1,1);
		height2 = 800;
		background2 = new Background(this.maingame,"comp50gd/img/seaweed.GIF",height2,1.25);
		
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

		sprites.put(1001d, new MineAlien(maingame, 700, -20));
		sprites.put(2001d, new MineAlien(maingame, 300, -20));
		sprites.put(3001d, new MineAlien(maingame, 500, -20));
		sprites.put(4001d, new MineAlien(maingame, 800, -20));
		sprites.put(5001d, new MineAlien(maingame, 200, -20));
		sprites.put(6001d, new MineAlien(maingame, 700, -20));
		sprites.put(7001d, new MineAlien(maingame, 300, -20));
		sprites.put(8001d, new MineAlien(maingame, 500, -20));
		sprites.put(9001d, new MineAlien(maingame, 800, -20));
		sprites.put(10001d, new MineAlien(maingame, 200, -20));
		
		sprites.put(102d, new SeekerAlien(maingame, 400, -20));
		sprites.put(2102d, new BombDropAlien(maingame, 200, 800));
		sprites.put(4102d, new SeekerAlien(maingame, 100, -20));
		sprites.put(8102d, new BombDropAlien(maingame, 800, 800));
		sprites.put(10102d, new SeekerAlien(maingame, 900, -20));
		sprites.put(12102d, new BombDropAlien(maingame, 200, 800));
		
		sprites.put(4100d, new DartAlien(maingame, 800, -20));
		sprites.put(4200d, new DartAlien(maingame, 300, -20));
		sprites.put(4300d, new DartAlien(maingame, 600, -20));
		sprites.put(4400d, new DartAlien(maingame, 100, -20));
		sprites.put(4500d, new DartAlien(maingame, 500, -20));
		sprites.put(4600d, new DartAlien(maingame, 700, -20));
		sprites.put(4700d, new DartAlien(maingame, 200, -20));
		sprites.put(4800d, new DartAlien(maingame, 800, -20));
		sprites.put(4900d, new DartAlien(maingame, 400, -20));
		
		sprites.put(6000d, new FigureEightAlien(maingame, -20, 50));
		sprites.put(6300d, new FigureEightAlien(maingame, -20, 50));
		sprites.put(6600d, new FigureEightAlien(maingame, -20, 50));
		sprites.put(6900d, new FigureEightAlien(maingame, -20, 50));
		sprites.put(7200d, new FigureEightAlien(maingame, -20, 50));
		sprites.put(7500d, new FigureEightAlien(maingame, -20, 50));
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
		return "Under Water";
	}

	@Override
	protected void setPlayerOrigin() {
		player_begin_x = Settings.WINDOW_WIDTH / 2;
		player_begin_y = Settings.WINDOW_HEIGHT - 50;		
	}

}
