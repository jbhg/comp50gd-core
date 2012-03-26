package comp50gd;

public class DartAlien extends EnemyShip{


	private int currentPath;
	private int countDown;
	private double dya;
	
	
	public DartAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/SeaEnemy1.gif");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
	
		dx = 0;
		dy = 5;
		dya = 1.05;
		currentPath = 0;
		this.score_value = 150;
		hull = 80;
	}

	@Override
	public void shoot() {
		
	}

	@Override
	public void move() {
		if (currentPath == 0)
		{
			py+=dy;
			if (py > 20)
				{
					countDown = 50;
					currentPath = 1;
				}
		}
		else if (currentPath == 1)
		{
			countDown--;
			if (countDown <= 0) currentPath =2;
		}
		
		else if (currentPath == 2)
		{
			py+=dy;
			dy=dy*dya;
		}
		
		if(py > Settings.WINDOW_HEIGHT)
			the_game.notifyDeath(this);

	}
	
	
}

