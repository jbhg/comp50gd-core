package comp50gd;

public class Level_01 extends Level {

	public Level_01(MainGame game, long difficulty) {
		super(game, difficulty);
		duration_in_seconds = 50;
		player_begin_x = Settings.WINDOW_WIDTH / 2;
		player_begin_y = Settings.WINDOW_HEIGHT - 50;
		height1 = 800;
		background = new Background(this.maingame,"comp50gd/img/background.JPG",height1,1);
		twoLayer = false;
	}

	/**
	 * The array of GameSprite objects which will be used in this level.
	 * 
	 * No two may have the same entry time; if they do, the data structure will
	 * only maintain the last of these.
	 */
	@Override
	protected void loadSprites() {
		sprites.put(0100d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(0250d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(0400d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(0550d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(0700d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(0850d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(1000d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(1150d, new SineWaveAlien2(maingame,0, 300, true));
		sprites.put(0101d, new SineWaveAlien2(maingame,1000, 150, false));
		sprites.put(0251d, new SineWaveAlien2(maingame,1000, 150, false));
		sprites.put(0401d, new SineWaveAlien2(maingame,1000, 150, false));
		sprites.put(0551d, new SineWaveAlien2(maingame,1000, 150, false));
		sprites.put(0701d, new SineWaveAlien2(maingame,1000, 150, false));
		sprites.put(0851d, new SineWaveAlien2(maingame,1000, 150, false));
		sprites.put(1001d, new SineWaveAlien2(maingame,1000, 150, false));
		sprites.put(1151d, new SineWaveAlien2(maingame,1000, 150, false));
		
		sprites.put(2000d, new SpiralAlien(maingame, 120, 0, false, true));
		sprites.put(2100d, new SpiralAlien(maingame, 120, 0, false, true));
		sprites.put(2200d, new SpiralAlien(maingame, 120, 0, false, true));
		sprites.put(2300d, new SpiralAlien(maingame, 120, 0, false, true));
		sprites.put(2400d, new SpiralAlien(maingame, 120, 0, false, true));
		sprites.put(2500d, new SpiralAlien(maingame, 120, 0, false, true));
		
		sprites.put(3000d, new SpiralAlien(maingame, 800, 0, true, false));
		sprites.put(3100d, new SpiralAlien(maingame, 800, 0, true, false));
		sprites.put(3200d, new SpiralAlien(maingame, 800, 0, true, false));
		sprites.put(3300d, new SpiralAlien(maingame, 800, 0, true, false));
		sprites.put(3400d, new SpiralAlien(maingame, 800, 0, true, false));
		sprites.put(3500d, new SpiralAlien(maingame, 800, 0, true, false));
		
		sprites.put(6000d, new ComplexPathAlien1(maingame, 400, 0, false));
		sprites.put(6050d, new ComplexPathAlien1(maingame, 600, 0, true));
		sprites.put(6100d, new ComplexPathAlien1(maingame, 400, 0, false));
		sprites.put(6150d, new ComplexPathAlien1(maingame, 600, 0, true));
		sprites.put(6200d, new ComplexPathAlien1(maingame, 400, 0, false));
		sprites.put(6250d, new ComplexPathAlien1(maingame, 600, 0, true));
		sprites.put(6300d, new ComplexPathAlien1(maingame, 400, 0, false));
		sprites.put(6350d, new ComplexPathAlien1(maingame, 600, 0, true));
		sprites.put(6400d, new ComplexPathAlien1(maingame, 400, 0, false));
		sprites.put(6450d, new ComplexPathAlien1(maingame, 600, 0, true));
		sprites.put(6500d, new ComplexPathAlien1(maingame, 400, 0, false));
		sprites.put(6550d, new ComplexPathAlien1(maingame, 600, 0, true));
		sprites.put(6600d, new ComplexPathAlien1(maingame, 400, 0, false));
		sprites.put(6650d, new ComplexPathAlien1(maingame, 600, 0, true));
		
		
		//sprites.put(501d, new SimpleVerticalDropAlien(maingame, 400, 0));
		//sprites.put(700d, new AndreTheGiantAlien(maingame, 400, 0));
		//sprites.put(900d, new SimpleShootingAlien(maingame, 400, 0));
		//sprites.put(1030d, new SimpleShootingAlien(maingame, 400, 0));
		//sprites.put(1160d, new SimpleShootingAlien(maingame, 400, 0));
		//sprites.put(1201d, new SimpleVerticalDropAlien(maingame, 550, 0));
		//sprites.put(1501d, new SimpleVerticalDropAlien(maingame, 400, 0));
		//sprites.put(1700d, new AndreTheGiantAlien(maingame, 400, 0));
		//sprites.put(1900d, new SimpleShootingAlien(maingame, 400, 0));
		//sprites.put(2030d, new SimpleShootingAlien(maingame, 400, 0));
		//sprites.put(2160d, new SimpleShootingAlien(maingame, 400, 0));
		//sprites.put(2201d, new SimpleVerticalDropAlien(maingame, 550, 0));
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
		return "Deep Space";
	}

	@Override
	protected void setPlayerOrigin() {
		player_begin_x = Settings.WINDOW_WIDTH / 2;
		player_begin_y = Settings.WINDOW_HEIGHT - 50;		
	}


}
