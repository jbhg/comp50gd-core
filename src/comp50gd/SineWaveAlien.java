package comp50gd;

public class SineWaveAlien extends EnemyShip{

	private double dummy_x, dummy_y, theta, radius;
	private boolean rotateClockwise;
	
	public SineWaveAlien(MainGame game, double x, double y, boolean rotateClockwise)
	{
		this(game, x, y);
		this.rotateClockwise = rotateClockwise;
	}
	
	public SineWaveAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/AirEnemy2.gif");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
		dummy_x = x;
		dummy_y = y;
		theta = 180;
		radius = 250;
		dx = 0.5;
		dy = 3;
		this.score_value = 50;
		this.rotateClockwise = false;
		hull = 50;
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.75) {
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) + 4, (int) this.py
								+ (int) this.height()));
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) - 4, (int) this.py
								+ (int) this.height()));

				this.time_last_shot = this.the_game.getTimeElapsed();
			}
		}
	}

	@Override
	public void move() {
		dummy_y += dy;
//		dummy_x += dx;
		if(rotateClockwise)
			{theta -= 1;}
		else
			{theta += 1;}
		theta %= 360;
		
		px = dummy_x + (radius + Math.random() *0) * Math.sin(Math.toRadians(theta));
		py = dummy_y;// + (radius + Math.random() *0) * Math.cos(Math.toRadians(theta));
		
		if(dummy_y + 20 > Settings.WINDOW_HEIGHT)
			the_game.notifyDeath(this);

	}
	
	
}
