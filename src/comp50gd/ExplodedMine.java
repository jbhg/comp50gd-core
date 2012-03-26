package comp50gd;

public class ExplodedMine extends EnemyBullet {
	
	protected int timeToDie;
	protected double dxa,dya;
	public ExplodedMine(MainGame game, double x, double y, double dx, double dy) {
		super(game, x, y,"comp50gd/img/bullet3.GIF");
		this.dx = 3*dx;
		this.dy = 3*dy;
		this.dxa = 0.95;
		this.dya = 0.95;
		this.width = 8;
		this.height = 8;
		this.shield_inc = -10;
		timeToDie = 50;
		this.coll_shape = new RectEntity(this);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void move() {
		dx=dx*dxa;
		dy=dy*dya;
		py += dy;
		px += dx;
		timeToDie --;
		if(timeToDie<0)
			this.the_game.notifyDeath(this);
	}
	
	public void notifyCollision(Collidable other) {
		if(other instanceof PlayerShip)
		{
			this.the_game.notifyDeath(this);
		}
	}
	
}
