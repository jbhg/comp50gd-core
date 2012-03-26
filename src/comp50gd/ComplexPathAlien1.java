package comp50gd;

public class ComplexPathAlien1 extends EnemyShip{

	private double dummy_x, dummy_y, theta, radius, turnRate;
	private int currentPath;
	private boolean rotateClockwise;
	
	public ComplexPathAlien1(MainGame game, double x, double y, boolean rotateClockwise)
	{
		this(game, x, y);
		this.rotateClockwise = rotateClockwise;
	}
	
	public ComplexPathAlien1(MainGame game, double x, double y) {
		super(game, x, y, "comp50gd/img/Enemy4.GIF");
		this.coll_shape = new RectEntity(this);
		this.gun_cooling_rate = 150;
		dummy_x = x;
		dummy_y = y;
		theta = 0;
		radius = 150;
		dx = 5;
		dy = 5;
		this.rotateClockwise = false;
		currentPath = 0;
		turnRate = 1.5;
		this.score_value = 50;
		this.hull = 50;
		this.power_up_ID = 3;
	}

	@Override
	public void shoot() {
		if (this.the_game.getTimeElapsed() - this.time_last_shot > this.gun_cooling_rate) {
			if (Math.random() > 0.75) {
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) + 13, (int) this.py
								+ (int) this.height()));
				this.the_game.add(new EnemyBullet(this.the_game,
						(int) (this.px + this.width / 2) - 14, (int) this.py
								+ (int) this.height()));
			}
			this.time_last_shot = this.the_game.getTimeElapsed();
		}
	}

	@Override
	public void move() {
		if (currentPath == 0)
		{
			py+=dy;
			if (py > 400 || py > 300 && rotateClockwise)
				{
					dummy_y = py-radius;
					dummy_x = px;
					currentPath = 1;
				}
		}
		else if (currentPath == 1)
		{
			if(rotateClockwise)
				{theta -= turnRate;}
			else
				{theta += turnRate;}
			theta %= 360;
			
			px = dummy_x + (radius + Math.random() *0) * Math.sin(Math.toRadians(theta));
			py = dummy_y + (radius + Math.random() *0) * Math.cos(Math.toRadians(theta));
			if (Math.abs(theta) > 330 && Math.abs(theta) < 360){
				currentPath = 2;
			}
		}
		else if (currentPath == 2)
		{
			if(rotateClockwise)
			{
				px-=dx;
			}
			else
			{
				px+=dx;
			}
			py+=dy/10;
		}
		
		if(px > Settings.WINDOW_WIDTH || px+20 < 0)
			the_game.notifyDeath(this);

	}
	
	
}

