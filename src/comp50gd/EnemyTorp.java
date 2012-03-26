package comp50gd;

public class EnemyTorp extends EnemyBullet{

	protected double dya;
	
	public EnemyTorp(double x, double y, MainGame game) {
		super(game, x, y, "comp50gd/img/torp.GIF");
		this.dy = 0.1;
		this.dya = 0.25;
		this.dx = 1;
		this.width = 8;
		this.height = 20;
		this.shield_inc = -50;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		if (the_game.panel().player_x() > this.px) 
		{
			this.px = Math.min(the_game.panel().player_x(), px + dx);			
		} 
		else
		{
			this.px = Math.max(the_game.panel().player_x(), px - dx);
		}
		dy += dya;
		py += dy;
		if(py > Settings.WINDOW_HEIGHT || py < 0)
			this.the_game.notifyDeath(this);
	}
	
	public void notifyCollision(Collidable other)
	{
		if(other instanceof PlayerShip)
			this.the_game.notifyDeath(this);
	}

}
