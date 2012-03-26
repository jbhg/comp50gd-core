package comp50gd;

public class SpiralAlien extends EnemyShip{

	private double dummy_x, dummy_y, theta, radius;
	private int turnRate;
	private boolean rotateClockwise, moveRight;
	
	public SpiralAlien(MainGame game, double x, double y, boolean rotateClockwise, boolean xDirection)
	{
		this(game, x, y);
		this.rotateClockwise = rotateClockwise;
		this.moveRight = xDirection;
		this.power_up_ID =2;
	}
	
	public SpiralAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/Enemy2.GIF");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
		dummy_x = x;
		dummy_y = y;
		theta = 180;
		radius = 150;
		dx = 1.5;
		dy = 1.5;
		this.score_value = 150;
		hull = 50;
		turnRate = 2;
		this.rotateClockwise = false;
		this.moveRight = false;
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.75) {
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) + 6, (int) this.py
								+ (int) this.height()));
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) - 6, (int) this.py
								+ (int) this.height()));
			}
			this.time_last_shot = this.the_game.getTimeElapsed();
		}
	}

	@Override
	public void move() {
		dummy_y += dy;
		if (moveRight)
		{
			dummy_x += dx;
		}
		else
		{	
			dummy_x -= dx;
		}
		if(rotateClockwise)
			{theta -= turnRate;}
		else
			{theta += turnRate;}
		theta %= 360;
		
		px = dummy_x + (radius + Math.random() *0) * Math.sin(Math.toRadians(theta));
		py = dummy_y + (radius + Math.random() *0) * Math.cos(Math.toRadians(theta));
		
		if(dummy_y - 50 > Settings.WINDOW_HEIGHT)
			the_game.notifyDeath(this);

	}
	
	
}
