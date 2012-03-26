package comp50gd;

public class FigureEightAlien extends EnemyShip{

	private double dummy_x, dummy_y, theta, radius;
	private int turnRate; 
	private boolean rotateClockwise, moveRight;
	

	
	public FigureEightAlien(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/SeaEnemy2.gif");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 250;
		dummy_x = x;
		dummy_y = y;
		theta = 180;
		radius = 50;
		dx = 1;
		this.score_value = 50;
		this.rotateClockwise = false;
		this.score_value = 80;
		hull = 50;
		turnRate = 1;
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.75) {
				this.the_game.add(new ExplodedBullet(
						this.the_game, 
						this.px+this.width/2, this.py+this.height,
						-0.3,8));
				this.the_game.add(new ExplodedBullet(
						this.the_game, 
						this.px+this.width/2, this.py+this.height,
						0,8));
				this.the_game.add(new ExplodedBullet(
						this.the_game, 
						this.px+this.width/2, this.py+this.height,
						0.3,8));
			}
			this.time_last_shot = this.the_game.getTimeElapsed();
		}
	}

	@Override
	public void move() {
		if (moveRight)
		{
			dummy_x += dx;
			if (dummy_x > Settings.WINDOW_WIDTH -100) moveRight = false;
		}
		else
		{
			dummy_x -= dx;
			if (dummy_x < 50) moveRight = true;
		}
		if(rotateClockwise)
			{theta -= turnRate;}
		else
			{theta += turnRate;}
		theta %= 360;
		
		px = dummy_x; //+ (radius + Math.random() *0) * Math.sin(Math.toRadians(theta));
		py = dummy_y + (radius + Math.random() *0) * Math.cos(Math.toRadians(theta));
		
		

	}
	
	
}
