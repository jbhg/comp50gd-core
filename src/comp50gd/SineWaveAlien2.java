package comp50gd;

public class SineWaveAlien2 extends EnemyShip{

	private double dummy_x, dummy_y, theta, radius;
	private int turnRate; 
	private boolean rotateClockwise, moveRight;
	
	//public SineWaveAlien2(MainGame game, double x, double y, boolean rotateClockwise)
	//{
	//	this(game, x, y);
	//	this.rotateClockwise = rotateClockwise;
	//}
	
	public SineWaveAlien2(MainGame game, double x, double y, boolean xDirection) {
		super(game, x, y, "comp50gd/img/Enemy1.GIF");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
		dummy_x = x;
		dummy_y = y;
		theta = 180;
		radius = 100;
		dx = 4;
		this.score_value = 50;
		this.rotateClockwise = false;
		this.moveRight = xDirection;
		hull = 50;
		turnRate = 2;
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.75) {
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2), (int) this.py
								+ (int) this.height()));
			}
			this.time_last_shot = this.the_game.getTimeElapsed();
		}
	}

	@Override
	public void move() {
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
		
		px = dummy_x; //+ (radius + Math.random() *0) * Math.sin(Math.toRadians(theta));
		py = dummy_y + (radius + Math.random() *0) * Math.cos(Math.toRadians(theta));
		
		if(dummy_x > Settings.WINDOW_WIDTH || dummy_x + 20 < 0)
			the_game.notifyDeath(this);

	}
	
	
}
