package comp50gd;

public class Bullet360 extends EnemyBullet{
	protected double angle;
	protected double speed;
	
	public Bullet360(double x, double y, MainGame game) {
		super(game, x, y,"comp50gd/img/bullet1.GIF");
		speed = 4;
		angle=Math.atan((the_game.panel().player_x()+20-x)/(the_game.panel().player_y()+20-y));
		this.dx = speed*Math.sin(angle);
		this.dy = speed*Math.cos(angle);
		
		if (the_game.panel().player_y() < y)
		{
			dx=-dx;
			dy=-dy;
		}
		
		this.width = 6;
		this.height = 6;
		this.shield_inc = -40;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		py += dy;
		px += dx;
		if(py > Settings.WINDOW_HEIGHT || py < 0 || px > Settings.WINDOW_WIDTH || px <0)
			this.the_game.notifyDeath(this);
	}
	
	public void notifyCollision(Collidable other)
	{
		if(other instanceof PlayerShip)
			this.the_game.notifyDeath(this);
	}
	

}