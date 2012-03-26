package comp50gd;

public class ExplodedBullet extends EnemyBullet {

	public ExplodedBullet(MainGame game, double x, double y, double dx, double dy) {
		super(game, x, y,"comp50gd/img/bullet2.GIF");
		this.dx = dx*1.5;
		this.dy = dy*1.5;
		this.width = 6;
		this.height = 6;
		this.shield_inc = -10;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void move() {
		py += dy;
		px += dx;
		if(py > Settings.WINDOW_HEIGHT || py < 0 || px > Settings.WINDOW_WIDTH || px < 0)
			this.the_game.notifyDeath(this);
	}
	
	public void notifyCollision(Collidable other) {
		if(other instanceof PlayerShip)
		{
			this.the_game.notifyDeath(this);
		}
	}
	
}

